package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.sources.SourcesContract.ISourcesPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesPresenter implements ISourcesPresenter{

    private RemoteNewsDataSource dataSource;
    private SourcesContract.ISourcesView view;

    public SourcesPresenter(RemoteNewsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void attachView(SourcesContract.ISourcesView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view= null;
    }

    @Override
    public void onTextClick() {

    }

    @Override
    public void prepareCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Sports");
        categories.add("Technology");
        categories.add("Business");
        categories.add("Entertainment");
        categories.add("General");
        categories.add("Health");
        categories.add("Science");
        view.displayCategories(categories);
    }

    @Override
    public void prepareNews() {

    }


    @Override
    public void prepareNewsWithFilter(String category) {

    }

    @Override
    public void goToFullNews(String url) {

    }
}
