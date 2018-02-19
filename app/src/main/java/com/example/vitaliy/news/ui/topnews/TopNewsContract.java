package com.example.vitaliy.news.ui.topnews;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

public interface TopNewsContract {
    interface ITopNewsView extends BaseView {

        void showFullNews(String url);

        void displayNews(List<Article> article);

        void hideCategories();

        void showCategories();

        void showSourceFilter();

        void hideSourceFilter();

        void viewSetText(String text);

        void pagination(List<Article> articles);

    }

    interface ITopNewsPresenter extends BasePresenter<ITopNewsView> {
        void start();

        void prepareNews();

        void goToFullNews(String url);

        void setCategoryName(String category);

        void setSourceID(String source);

        void setPageNumber(int page);

        void setCountry(String country);

    }
}
