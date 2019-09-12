package com.yazanjayousi.nytimesmostpopulararticles.api;

import com.yazanjayousi.nytimesmostpopulararticles.articles.ArticlesResults;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    // get articles data by sending api url with one day
    @GET("1.json")
    Call<ArticlesResults> getArticlesDay(@Query("api-key") String key);

    // get articles data by sending api url with one day
    @GET("7.json")
    Call<ArticlesResults> getArticleWeek(@Query("api-key") String key);

    // get articles data by sending api url with one day
    @GET("30.json")
    Call<ArticlesResults> getArticleMonth(@Query("api-key") String key);
}
