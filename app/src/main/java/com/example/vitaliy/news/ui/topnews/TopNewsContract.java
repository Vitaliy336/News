package com.example.vitaliy.news.ui.topnews;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface TopNewsContract {
    interface ITopNewsView extends BaseView {
        void displayCategories(List<String> categories);

        void ShowFullNews(String url);

        void displayNews(List<Article> article);

        void hideCategories();

        void showCategories();

        void showSourceFilter();

        void hideSourceFilter();

        void viewSetText(String text);

    }

    interface ITopNewsPresenter extends BasePresenter<ITopNewsView> {
        void start();

        void prepareCategories();

        void prepareNews();

        void goToFullNews(String url);

        void setCategoryName(String category);

        void setSourceID(String source);



    }
}
