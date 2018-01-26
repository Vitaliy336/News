
package com.example.vitaliy.news.ui.topnews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {
    private String category = "";
    private String source = "";
    private RemoteNewsDataSource dataSource;
    private TopNewsContract.ITopNewsView view;
    private Context mContext;

    public TopNewsPresenter(RemoteNewsDataSource dataSource, Activity activity) {
        this.mContext = activity;
        this.dataSource = dataSource;
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
        prepareCategories();
    }

    @Override
    public void prepareCategories() {
        view.displayCategories(Arrays.asList(mContext.getResources().getStringArray(R.array.ctList)));
    }

    @Override
    public void prepareNews() {
        dataSource.getHotNews(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> list) {
                view.displayNews((List<Article>) list);
                Log.e("presenter", "dispaly news");
            }

            @Override
            public void onFailure() {
            }
        },category, source);
    }

    @Override
    public void goToFullNews(String url) {
        view.ShowFullNews(url);
    }

    @Override
    public void setCategoryName(String category) {
        this.category = category;
    }

    @Override
    public void setSourceID(String source) {
        view.hideSourceFilter();
        this.source = source;
        if (!TextUtils.isEmpty(this.source)){
            view.hideCategories();
            view.showSourceFilter();
            view.viewSetText(source);
        }else{
            view.hideSourceFilter();
            view.showCategories();
            prepareNews();
        }
    }


}