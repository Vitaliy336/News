package com.example.vitaliy.news.ui.sources;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesNewsPresenter implements SourccesNewsContract.ISourcesNewsPresenter {
    SourccesNewsContract.ISourcesNewsView view;

    @Override
    public void attachView(SourccesNewsContract.ISourcesNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void prepareCategoriee() {
        view.showCategories();
    }

    @Override
    public void prepareNews() {
        view.showNews();
    }

    @Override
    public void goToFullNews() {
        view.showFullNews();
    }
}
