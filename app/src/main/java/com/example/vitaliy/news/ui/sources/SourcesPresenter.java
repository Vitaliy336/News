package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.data.sourceModel.Source;
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
    public void prepareSources() {
        dataSource.SourcesDataFromApi(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> list) {
                view.showSources((List<Source>) list);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public void prepareSourcesWithCategory(String category) {
        dataSource.AllSourcesDataWithFilter(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> sources) {
                view.showSourcesWithCategory((List<Source>) sources);
            }

            @Override
            public void onFailure() {

            }
        }, category);
    }

}
