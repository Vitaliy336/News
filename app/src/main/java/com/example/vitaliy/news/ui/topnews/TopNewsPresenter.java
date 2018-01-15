package com.example.vitaliy.news.ui.topnews;

import android.widget.Toast;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {

    private TopNewsContract.ITopNewsView view;

    @Override
    public void attachView(TopNewsContract.ITopNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onTextClick() {
        view.displayToastMessage();
    }

    @Override
    public void prepareCategories(List<String> categories) {
        view.displayCategories(categories);
    }
}
