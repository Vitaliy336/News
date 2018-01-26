package com.example.vitaliy.news.ui.sources;

import android.app.Activity;
import android.content.Context;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.data.sourceModel.Source;
import com.example.vitaliy.news.ui.sources.SourcesContract.ISourcesPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesPresenter implements ISourcesPresenter{

    private String category = "";
    private RemoteNewsDataSource dataSource;
    private SourcesContract.ISourcesView view;
    private Context mContext;

    public SourcesPresenter(RemoteNewsDataSource dataSource, Activity activity) {
        this.dataSource = dataSource;
        this.mContext = activity;
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
        prepareCategories();
    }

    @Override
    public void prepareCategories() {
        view.displayCategories(Arrays.asList(mContext.getResources().getStringArray(R.array.ctList)));
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
