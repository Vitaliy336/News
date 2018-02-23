
package com.example.vitaliy.news.ui.topnews;

import android.text.TextUtils;
import android.util.Log;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.ArrayList;
import java.util.List;


public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {
    private String category = "";
    private String source = "";
    private String country = "";
    private int page = 1;
    private final int startingPage = 1;
    private NewsDataSource dataSource;
    private TopNewsContract.ITopNewsView view;

    public TopNewsPresenter() {
    }

    @Override
    public void attachView(TopNewsContract.ITopNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void start() {
        prepareNews();
    }

    @Override
    public void prepareNews() {
        dataSource.getHotNews(new NewsDataRepository.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                if (page > startingPage){
                    view.pagination((List<Article>) article);
                } else {
                    view.displayNews((List<Article>) article);
                }
            }

            @Override
            public void onFailure() {

            }
        }, category, source, page, country);
    }

    @Override
    public void goToFullNews(String url) {
        view.showFullNews(url);
    }

    @Override
    public void setCategoryName(String category) {
        this.category = category;
    }

    @Override
    public void setSourceID(String source) {
        view.hideSourceFilter();
        this.source = source;
        if (!TextUtils.isEmpty(this.source)) {
            view.hideCategories();
            view.showSourceFilter();
            view.viewSetText(category);
        } else {
            view.hideSourceFilter();
            view.showCategories();
            prepareNews();
        }
    }

    @Override
    public void setPageNumber(int page) {
        this.page = page;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public void setDataSource(NewsDataSource dataSource) {
        this.dataSource = dataSource;
    }


}