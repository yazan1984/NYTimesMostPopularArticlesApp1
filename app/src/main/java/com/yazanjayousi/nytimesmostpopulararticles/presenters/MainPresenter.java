package com.yazanjayousi.nytimesmostpopulararticles.presenters;

import android.util.Log;

import com.yazanjayousi.nytimesmostpopulararticles.App;
import com.yazanjayousi.nytimesmostpopulararticles.activities.MainActivity;
import com.yazanjayousi.nytimesmostpopulararticles.api.Api;
import com.yazanjayousi.nytimesmostpopulararticles.articles.ArticlesResults;
import com.yazanjayousi.nytimesmostpopulararticles.articles.Result;
import com.yazanjayousi.nytimesmostpopulararticles.models.Article;
import com.yazanjayousi.nytimesmostpopulararticles.utils.Constants;
import com.yazanjayousi.nytimesmostpopulararticles.utils.Utils;
import com.yazanjayousi.nytimesmostpopulararticles.views.MainView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainView {
    ArrayList<Article> articlesList;
    private onArticlesListReady listReady;

    public MainPresenter(onArticlesListReady listReady) {
        this.listReady = listReady;
    }

    @Override
    public void getArticlesData(int days) {
        Api api = App.getApi();
        articlesList = new ArrayList<>();
        switch (days) {
            case 1: {
                api.getArticlesDay(Constants.apiKey).enqueue(new Callback<ArticlesResults>() {
                    @Override
                    public void onResponse(Call<ArticlesResults> call, Response<ArticlesResults> response) {
                        arrangeArticlesResults(response);
                    }

                    @Override
                    public void onFailure(Call<ArticlesResults> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        Utils.snackBar(MainActivity.view, t.getMessage());

                    }
                });
                break;
            }

            case 7: {
                api.getArticleWeek(Constants.apiKey).enqueue(new Callback<ArticlesResults>() {
                    @Override
                    public void onResponse(Call<ArticlesResults> call, Response<ArticlesResults> response) {
                        arrangeArticlesResults(response);
                    }

                    @Override
                    public void onFailure(Call<ArticlesResults> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        Utils.snackBar(MainActivity.view, t.getMessage());

                    }
                });
                break;
            }

            case 30: {
                api.getArticleMonth(Constants.apiKey).enqueue(new Callback<ArticlesResults>() {
                    @Override
                    public void onResponse(Call<ArticlesResults> call, Response<ArticlesResults> response) {
                        arrangeArticlesResults(response);
                    }

                    @Override
                    public void onFailure(Call<ArticlesResults> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        Utils.snackBar(MainActivity.view, t.getMessage());

                    }
                });
                break;
            }
        }
    }

    private void arrangeArticlesResults(Response<ArticlesResults> response) {
        try {

            if (response.body().getNumResults() > 0) {
                Article article;
                List<Result> result = response.body().getResults();
                int i = 0;
                for (Result row : result) {
                    i++;
                    Log.e("PositionKey result", row.toString());
                    Log.e("PositionKey p", i + "");

                    article = new Article(i, row.getUrl(), row.getAdxKeywords(), row.getSection(), row.getId(), row.getByline(), row.getTitle(), row.getAbstract(),
                            row.getPublishedDate(), row.getMedia().get(0).getType(), row.getMedia().get(0).getCaption(),
                            row.getMedia().get(0).getMediaMetadata().get(0).getUrl(), row.getMedia().get(0).getMediaMetadata().get(2).getUrl());
                    articlesList.add(article);
                }
                listReady.sendArticlesToMainActivity(articlesList);
            } else
                Utils.snackBar(MainActivity.view, response.body().getStatus());
        } catch (Exception ignored) {
            Utils.snackBar(MainActivity.view, ignored.getMessage());

        }

    }

    public interface onArticlesListReady {
        void sendArticlesToMainActivity(ArrayList<Article> articleArrayList);
    }
}
