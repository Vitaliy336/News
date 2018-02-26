package com.example.vitaliy.news.modules;

import com.example.vitaliy.news.data.source.NewsDataSource;

/**
 * Created by v_shevchyk on 26.02.18.
 */

public class BadDataSource {

    public NewsDataSource badDatasource = new NewsDataSource() {
        @Override
        public void getHotNews(getListCallback callback, String category, String source, int page, String country) {
            callback.onFailure();
        }

        @Override
        public void getEverything(getListCallback callback, String query, int page, String order) {
            callback.onFailure();
        }

        @Override
        public void getSources(getListCallback callback, String category) {
            callback.onFailure();
        }
    };
}
