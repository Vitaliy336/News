package com.example.vitaliy.news.data.source;

import com.example.vitaliy.news.data.model.Article;

import java.util.List;

/**
 * Created by v_shevchyk on 16.01.18.
 */

public interface NewsDataSource {

    interface getListCallback {
        void onListReceived(List<Article> list);

        void onFailure();
    }

    void topDataFromApi(getListCallback callback);
}
