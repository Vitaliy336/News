package com.example.vitaliy.news.ui.topnews;

import com.example.vitaliy.news.ui.Model.NewsData;
import com.example.vitaliy.news.ui.Model.newsModel.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {

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
        ArrayList<NewsModel> news = new ArrayList<>();
        NewsData nd = new NewsData();
        nd.NewsDataFromApi();
        view.displayNews(news);
    }
}
