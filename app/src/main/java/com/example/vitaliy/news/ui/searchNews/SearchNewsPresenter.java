package com.example.vitaliy.news.ui.searchNews;

import android.text.TextUtils;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.List;

public class SearchNewsPresenter implements SearchNewsContract.IAllNewsPresenter {
    private String query = null;
    private SearchNewsContract.IAllNewsView view;
    private String order = "";
    private NewsDataSource dataSource;
    private int page = 1;
    private final int startingPage = 1;

    public SearchNewsPresenter() {
    }

    @Override
    public void attachView(SearchNewsContract.IAllNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void start() {
        if (!TextUtils.isEmpty(query)) {
            view.hideMessage();
            prepareNews();
        } else {
            view.showMessage();
        }
    }

    @Override
    public void prepareNews() {
        dataSource.getEverything(new NewsDataSource.getListCallback() {
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
                view.showErrorMessage("Fail to load data");
            }
        }, query, page, order);
    }

    @Override
    public void goTofullNews(String url) {
        view.showFullNews(url);
    }

    @Override
    public void getSearchQuery(String query) {
        this.query = query;
    }

    @Override
    public void setPageNumber(int page) {
        this.page = page;
    }

    @Override
    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public void setDataSource(NewsDataSource dataSource){
        this.dataSource = dataSource;
    }

}