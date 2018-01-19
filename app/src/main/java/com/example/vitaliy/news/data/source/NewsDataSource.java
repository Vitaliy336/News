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
    void AllDataFromApi(getListCallback callback);
    void SourcesDataFromApi(getListCallback callback);

    void hotNewsWithFilter(getListCallback callback, String category);
    void AllNewsWithSearchQuery(getListCallback callback, String query);


}
