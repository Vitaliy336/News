package com.example.vitaliy.news.ui.topnews;

import android.util.Log;

import com.example.vitaliy.news.data.model.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {

    private RemoteNewsDataSource dataSource;

    public TopNewsPresenter(RemoteNewsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private TopNewsContract.ITopNewsView view;

    @Override
    public void attachView(TopNewsContract.ITopNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onTextClick() {
        view.displayToastMessage();
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

    @Override
    public void prepareNews() {
        dataSource.topDataFromApi(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<Article> list) {
                view.displayNews(list);
            }

            @Override
            public void onFailure() {
            }
        });
    }
}
