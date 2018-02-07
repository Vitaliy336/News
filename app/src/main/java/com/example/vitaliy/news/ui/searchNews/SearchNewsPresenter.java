package com.example.vitaliy.news.ui.searchNews;

import android.text.TextUtils;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.source.NewsDataRepository;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.List;


public class SearchNewsPresenter implements SearchNewsContract.IAllNewsPresenter {
    private String query = null;
    private SearchNewsContract.IAllNewsView view;
    private NewsDataRepository repository;

    public SearchNewsPresenter(NewsDataRepository repository) {
        this.repository = repository;
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
        if(!TextUtils.isEmpty(query)){
            view.hideMessage();
            prepareNews();
        }
        else {
            view.showMessage();
        }
    }

    @Override
    public void prepareNews() {
        repository.getEverything(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                view.displayNews((List<Article>) article);
            }

            @Override
            public void onFailure() {
                onFailure();
            }
        }, query);
    }

    @Override
    public void goTofullNews(String url) {
        view.showFullNews(url);
    }

    @Override
    public void getSearchQuery(String query) {
        this.query = query;
    }

}