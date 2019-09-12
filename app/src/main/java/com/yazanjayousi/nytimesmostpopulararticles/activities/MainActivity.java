package com.yazanjayousi.nytimesmostpopulararticles.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.yazanjayousi.nytimesmostpopulararticles.R;
import com.yazanjayousi.nytimesmostpopulararticles.adapters.ArticleAdapter;
import com.yazanjayousi.nytimesmostpopulararticles.models.Article;
import com.yazanjayousi.nytimesmostpopulararticles.presenters.MainPresenter;
import com.yazanjayousi.nytimesmostpopulararticles.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainPresenter.onArticlesListReady{

    RecyclerView recyclerView;
    public static View view;
    ArticleAdapter adapter;
    MainPresenter presenter;
    int days=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        presenter=new MainPresenter(this);
        if (Utils.isNetworkConnected(getApplicationContext()))
        presenter.getArticlesData(days);
        else
            Utils.snackBar(view,getString(R.string.noInternet));
    }

    private void initViews() {
        recyclerView=findViewById(R.id.articlesRecyclerView);
        view=findViewById(R.id.mainLayout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                adapter.notifyDataSetChanged();
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                adapter.notifyDataSetChanged();
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }

        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                presenter.getArticlesData(days);
                return false;
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_today:
                days=1;
                presenter.getArticlesData(days);
                break;
            case R.id.action_week:
                days=7;
                presenter.getArticlesData(days);
                break;
            case R.id.action_month:
                days=30;
                presenter.getArticlesData(days);
                break;
            case R.id.action_search:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void sendArticlesToMainActivity(ArrayList<Article> articleArrayList) {
        adapter=new ArticleAdapter(articleArrayList,getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
