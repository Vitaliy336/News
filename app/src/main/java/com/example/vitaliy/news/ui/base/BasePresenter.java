package com.example.vitaliy.news.ui.base;


public interface BasePresenter<T> {
    void attachView(T view);

    void detachView();
}
