package com.example.vitaliy.news.ui.searchNews;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface SearchNewsContract {
    interface IAllNewsView extends BaseView {

        void displayNews(List<Article> news);

        void showFullNews(String url);

        void hideMessage();

        void showMessage();
    }

    interface IAllNewsPresenter extends BasePresenter<IAllNewsView> {

        void start();

        void prepareNews();

        void goTofullNews(String url);

        void getSearchQuery(String query);
    }
}
