package com.example.vitaliy.news.ui.topnews;

import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface TopNewsContract {
    interface ITopNewsView extends BaseView {
        void displayCategories(List<String> categories);
        void displayToastMessage();
    }

    interface ITopNewsPresenter extends BasePresenter<ITopNewsView> {
        void onTextClick();
        void prepareCategories(List<String> categories);
    }
}
