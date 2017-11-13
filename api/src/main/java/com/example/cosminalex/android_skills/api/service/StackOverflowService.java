package com.example.cosminalex.android_skills.api.service;

import com.example.cosminalex.android_skills.commons.models.UserResponse;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by cosminalex on 13.11.2017.
 */

public interface StackOverflowService {
@GET("users")
    Single<UserResponse> getUsers(@QueryMap Map<String,String> parameter);

}
