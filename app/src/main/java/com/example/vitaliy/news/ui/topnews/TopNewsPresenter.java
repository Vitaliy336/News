package com.example.vitaliy.news.ui.topnews;

import android.widget.Toast;

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
        List<NewsModel> news = new ArrayList<>();
        NewsModel nn = new NewsModel("News1", "bla bla bla bla bla");
        NewsModel nn1 = new NewsModel("News2", "bla bla bla bla bla");
        NewsModel nn2 = new NewsModel("News3", "bla bla bla bla bla");
        NewsModel nn3 = new NewsModel("News4", "bla bla bla bla bla");
        NewsModel nn4 = new NewsModel("News5", "bla bla bla bla bla");
        NewsModel nn9 = new NewsModel("News6", "bla bla bla bla bla");
        NewsModel nn8 = new NewsModel("News7", "bla bla bla bla bla");
        NewsModel nn7 = new NewsModel("News8", "bla bla bla bla bla");
        NewsModel nn6 = new NewsModel("News9", "bla bla bla bla bla");
        NewsModel nn5 = new NewsModel("News10", "bla bla bla bla bla");
        NewsModel nn10 = new NewsModel("News11", "bla bla bla bla bla");
        news.add(nn);
        news.add(nn1);
        news.add(nn2);
        news.add(nn3);
        news.add(nn4);
        news.add(nn5);
        news.add(nn6);
        news.add(nn7);
        news.add(nn8);
        news.add(nn9);
        news.add(nn10);
        view.displayNews(news);
    }
}
