package com.example.vitaliy.news.ui.fullnews;

import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;


public interface FullNewsContract {
    interface IFullNewsView extends BaseView {
        void showContent(String url);
    }

    interface IFullNewsPresenter extends BasePresenter<IFullNewsView> {
        void getUrl(String url);
    }

}
