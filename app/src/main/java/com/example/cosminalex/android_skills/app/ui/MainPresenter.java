package com.example.cosminalex.android_skills.app.ui;

import android.content.Context;
import android.os.SystemClock;

import com.example.cosminalex.android_skills.api.service.StackOverflowService;
import com.example.cosminalex.android_skills.app.storage.disk.SharedPrefsHelper;
import com.example.cosminalex.android_skills.app.storage.memory.ExpiringLruCache;
import com.example.cosminalex.android_skills.commons.models.User;
import com.example.cosminalex.android_skills.commons.models.UserResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cosminalex on 13.11.2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private static final String BASE_URL= "https://api.stackexchange.com/2.2/";
    private static final String USER_KEY = "users.key";

    private StackOverflowService service;
    private MainContract.View view;
    private SharedPrefsHelper sharedPref;
    private ExpiringLruCache<String,List<User>> cache;


    MainPresenter(MainContract.View view, Context context){
        this.view = view;
       service = initRetrofit().create(StackOverflowService.class);
       sharedPref = new SharedPrefsHelper(context);
       cache = new ExpiringLruCache<>(11,1000*60*10);

   }

    private Retrofit initRetrofit(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
     return new Retrofit.Builder()
             .baseUrl(BASE_URL)
             .client(
                     new OkHttpClient.Builder()
             .addInterceptor(interceptor).build())
             .addConverterFactory(GsonConverterFactory.create())
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .build();
    }

    public void getUsers(){
        List<User> users;
        users = cache.get(USER_KEY);
        if(users != null){
            view.loadUsers(users);
            return;
        }
        UserResponse userResponse = sharedPref.getDevelopers();
        if(userResponse != null && userResponse.getExpiringTime() >= SystemClock.elapsedRealtime()){
            view.loadUsers(userResponse.getItems());
            return;
        }


        Map<String, String> queries = new HashMap<>();
        queries.put("pagesize" ,"10");
        queries.put("order","desc");
        queries.put("sort","reputation");
        queries.put("site","stackoverflow");
        service.getUsers(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<UserResponse>() {
                    @Override
                    public void onSuccess(UserResponse userResponse) {
                      view.loadUsers(userResponse.getItems());
                      cache.put(USER_KEY,userResponse.getItems());
                      sharedPref.putDevs(userResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                     view.showError();
                    }
                });
    }

}
