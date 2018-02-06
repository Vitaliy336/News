package com.example.vitaliy.news.ui.fullnews;

/**
 * Created by v_shevchyk on 18.01.18.
 */

public class FullNewsPresenter implements FullNewsContract.IFullNewsPresenter {

    private FullNewsContract.IFullNewsView view;

    @Override
    public void attachView(FullNewsContract.IFullNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void getUrl(String url) {
        view.showContent(url);
    }
}
