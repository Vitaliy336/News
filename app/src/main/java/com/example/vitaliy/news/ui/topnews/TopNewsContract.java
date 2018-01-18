package com.example.vitaliy.news.ui.topnews;

import com.example.vitaliy.news.data.model.Article;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface TopNewsContract {
    interface ITopNewsView extends BaseView {
        void displayCategories(List<String> categories);
        void displayNews(List<Article> article);
        void displayToastMessage();
        void ShowFullNews(String url);

    }

    interface ITopNewsPresenter extends BasePresenter<ITopNewsView> {
        void onTextClick();
        void prepareCategories();
        void prepareNews();
        void goToFullNews(String url);

    }
}
