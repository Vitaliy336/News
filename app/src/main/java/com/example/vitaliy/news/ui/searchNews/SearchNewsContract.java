package com.example.vitaliy.news.ui.searchNews;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

public interface SearchNewsContract {
    interface IAllNewsView extends BaseView {

        void displayNews(List<Article> news);

        void showFullNews(String url);

        void showErrorMessage(String message);

        void hideMessage();

        void showMessage();

        void pagination(List<Article> articles);
    }

    interface IAllNewsPresenter extends BasePresenter<IAllNewsView> {

        void setDataSource(NewsDataSource dataSource);

        void start();

        void prepareNews();

        void goTofullNews(String url);

        void getSearchQuery(String query);

        void setPageNumber(int page);

        void setOrder(String order);
    }
}
