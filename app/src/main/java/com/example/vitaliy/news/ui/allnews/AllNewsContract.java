package com.example.vitaliy.news.ui.allnews;

import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface AllNewsContract {
    interface IAllNewsView extends BaseView {
        void displayCategories(List<String> categories);

        void ShowToastMessage();
    }

    interface IAllNewsPresenter extends BasePresenter<IAllNewsView> {
        void onTextClickListener();

        void prepareCategories();
    }
}
