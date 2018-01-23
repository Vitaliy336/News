
package com.example.vitaliy.news.ui.topnews;

import android.util.Log;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {

    private RemoteNewsDataSource dataSource;
    private TopNewsContract.ITopNewsView view;


    public TopNewsPresenter(RemoteNewsDataSource dataSource) {
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
    public void onTextClick() {
        view.displayToastMessage();
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
        dataSource.topDataFromApi(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> list) {

                view.displayNews((List<Article>) list);
                Log.e("presenter", "dispaly news");

            }

            @Override
            public void onFailure() {
            }
        });
    }

    @Override
    public void prepareNewsWithFilter(String category) {
        dataSource.hotNewsWithFilter(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> list) {
                view.showNewsWithFilter((List<Article>) list);
            }

            @Override
            public void onFailure() {

            }
        }, category);
    }


    @Override
    public void goToFullNews(String url) {
        view.ShowFullNews(url);
    }

    @Override
    public void prepareNewsWithSources(String source) {
        dataSource.topNewsWithSource(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                view.showNewsWithSource((List<Article>) article);
            }

            @Override
            public void onFailure() {

            }
        }, source);
    }
}
