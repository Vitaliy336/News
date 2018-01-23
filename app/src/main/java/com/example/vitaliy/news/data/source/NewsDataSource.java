package com.example.vitaliy.news.data.source;

import android.widget.ListView;

import com.example.vitaliy.news.data.newsModel.Article;

import java.util.List;

/**
 * Created by v_shevchyk on 16.01.18.
 */

public interface NewsDataSource {

    interface getListCallback {
        void onListReceived(List<?> article);

        void onFailure();
    }

    void topDataFromApi(getListCallback callback);
    void allDataFromApi(getListCallback callback);
    void sourcesDataFromApi(getListCallback callback);

    void hotNewsWithFilter(getListCallback callback, String category);
    void allNewsWithSearchQuery(getListCallback callback, String query);
    void allSourcesDataWithFilter(getListCallback callback, String category);
    void topNewsWithSource(getListCallback callback, String source);


}
