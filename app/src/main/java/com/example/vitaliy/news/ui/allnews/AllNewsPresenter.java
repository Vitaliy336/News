package com.example.vitaliy.news.ui.allnews;

import android.util.Log;

import com.example.vitaliy.news.data.model.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class AllNewsPresenter implements AllNewsContract.IAllNewsPresenter {
    private AllNewsContract.IAllNewsView view;
    NewsDataSource newsDataSource;

    public AllNewsPresenter(NewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void attachView(AllNewsContract.IAllNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onTextClickListener() {
        view.ShowToastMessage();
    }

    @Override
    public void prepareNews() {
        newsDataSource.AllDataFromApi(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<Article> list) {
                view.displayNews(list);
            }

            @Override
            public void onFailure() {
                Log.e("Failure", "fail");
            }
        });
    }

    @Override
    public void prepareCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Sport");
        categories.add("Politic");
        categories.add("Woirld");
        categories.add("BBC");
        categories.add("Others");
        view.displayCategories(categories);
    }
}
