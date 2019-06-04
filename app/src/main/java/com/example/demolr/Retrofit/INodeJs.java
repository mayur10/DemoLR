package com.example.demolr.Retrofit;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJs {

    @POST("users/register")
    @FormUrlEncoded
    Observable<String> registerUser(
            @Field("first_name") String first,
            @Field("last_name") String last,
            @Field("uname") String username,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("snapchat_username") String snapchat_username,
            @Field("facebook_username") String facebook_username,
            @Field("instagram_username") String instagram_username,
            @Field("linkedin_username") String linkedin_username
    );

    @POST("users/login")
    @FormUrlEncoded
    Observable<String> loginUser(
            @Field("uname") String username,
            @Field("password") String password
    );
}
