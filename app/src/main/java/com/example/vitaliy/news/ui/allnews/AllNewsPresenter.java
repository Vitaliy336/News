package com.example.vitaliy.news.ui.allnews;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class AllNewsPresenter implements AllNewsContract.IAllNewsPresenter {
    private AllNewsContract.IAllNewsView view;

    @Override
    public void attachView(AllNewsContract.IAllNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onTextClickListener() {
        view.ShowToastMessage();
    }
}
