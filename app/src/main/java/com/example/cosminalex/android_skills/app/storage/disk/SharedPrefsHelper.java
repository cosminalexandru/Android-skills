package com.example.cosminalex.android_skills.app.storage.disk;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cosminalex.android_skills.commons.models.User;
import com.example.cosminalex.android_skills.commons.models.UserResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static com.example.cosminalex.android_skills.app.storage.disk.Preferences.PrefKeys.KEY_DEVS;


public class SharedPrefsHelper {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    public SharedPrefsHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Preferences.PrefNames.PREFS_NAME_TAG, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void putDevs(UserResponse devResponse) {
        String devs = gson.toJson(devResponse);
        sharedPreferences.edit().putString(KEY_DEVS.name(), devs).apply();
    }

    public UserResponse getDevelopers() {
        String devs = sharedPreferences.getString(KEY_DEVS.name(), null);
        if (devs == null) {
            return null;
        }
        Type listType = new TypeToken<UserResponse>() {
        }.getType();
        return new Gson().fromJson(devs, listType);
    }

}
