package com.example.vitaliy.news.pack.ui.sources;

import android.app.Activity;

import com.example.vitaliy.news.pack.data.source.NewsDataSource;
import com.example.vitaliy.news.pack.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.pack.data.model.source.Source;
import com.example.vitaliy.news.pack.ui.sources.SourcesContract.ISourcesPresenter;

import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesPresenter implements ISourcesPresenter{

    private String category = "";
    private RemoteNewsDataSource dataSource;
    private SourcesContract.ISourcesView view;

    public SourcesPresenter(RemoteNewsDataSource dataSource, Activity activity) {
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
    public void start() {
        prepareSources();
    }



    @Override
    public void prepareSources() {
        dataSource.getSources(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> list) {
                view.showSources((List<Source>) list);
            }

            @Override
            public void onFailure() {

            }
        }, category);
    }

    @Override
    public void getSourceCategory(String category) {
        this.category = category;
    }
}
