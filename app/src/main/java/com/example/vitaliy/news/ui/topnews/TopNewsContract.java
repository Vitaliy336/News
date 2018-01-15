package com.example.vitaliy.news.ui.topnews;

import com.example.vitaliy.news.ui.Model.newsModel.NewsModel;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface TopNewsContract {
    interface ITopNewsView extends BaseView {
        void displayCategories(List<String> categories);
        void displayNews(List<NewsModel> news);
        void displayToastMessage();
    }

    interface ITopNewsPresenter extends BasePresenter<ITopNewsView> {
        void onTextClick();
        void prepareCategories();
        void prepareNews();
    }
}
