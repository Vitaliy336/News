package com.example.vitaliy.news.ui.base;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public interface BasePresenter<T> {
    void attachView(T view);
    void detachView();
}
