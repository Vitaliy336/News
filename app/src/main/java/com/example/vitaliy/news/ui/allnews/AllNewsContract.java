package com.example.vitaliy.news.ui.allnews;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface AllNewsContract {
    interface IAllNewsView extends BaseView {
        void displayCategories(List<String> categories);
        void displayNews(List<Article> news);
        void displayNewsbySearch(List<Article> news);
        void showToastMessage();
        void showFullNews(String url);
    }

    interface IAllNewsPresenter extends BasePresenter<IAllNewsView> {
        void onTextClickListener();
        void prepareNews();
        void prepareCategories();
        void goTofullNews(String url);
        void searchNews(String query);
    }
}
