package com.yazanjayousi.nytimesmostpopulararticles;

import android.app.Application;

import com.yazanjayousi.nytimesmostpopulararticles.api.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App extends Application {
    // Weather Api Url
    public final static String baseUrl = "https://api.nytimes.com/svc/mostpopular/v2/emailed/";

    static Api api;

    @Override
    public void onCreate() {
        super.onCreate();

        // specify data level come from weather api
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // append api key on the api url
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)

                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(client)
                .build();

        api = retrofit.create(Api.class);

    }


    public static Api getApi() {
        return api;
    }
}