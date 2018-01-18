package com.example.vitaliy.news.ui.topnews;

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
    private TopNewsContract.ITopNewsView view;


    public TopNewsPresenter(RemoteNewsDataSource dataSource) {
        this.dataSource = dataSource;
    }


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
        categories.add("Sports");
        categories.add("Technology");
        categories.add("Business");
        categories.add("Entertainment");
        categories.add("General");
        categories.add("Health");
        categories.add("Science");
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


    @Override
    public void goToFullNews(String url) {
        view.ShowFullNews(url);
    }
}
