
package com.example.vitaliy.news.ui.topnews;

import android.text.TextUtils;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.NewsDataRepository;

import java.util.List;


public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {
    private String category = "";
    private String source = "";
    private NewsDataRepository dataRepository;
    private TopNewsContract.ITopNewsView view;

    public TopNewsPresenter(NewsDataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

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
        dataRepository = App.getInstance().getDataRepositoryInstance();
        dataRepository.getHotNews(new NewsDataRepository.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                view.displayNews((List<Article>) article);
            }

            @Override
            public void onFailure() {

            }
        }, category, source);
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
            view.viewSetText(source);
        } else {
            view.hideSourceFilter();
            view.showCategories();
            prepareNews();
        }
    }
}