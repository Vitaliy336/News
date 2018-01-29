package com.example.vitaliy.news.data.source;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class NewsDataRepository implements NewsDataSource {
    private LocalNewsDataSource localNewsDataSource;
    private RemoteNewsDataSource remoteNewsDataSource;

    @Override
    public void getHotNews(getListCallback callback, String category, String source) {

    }

    @Override
    public void getEverything(getListCallback callback, String query) {

    }

    @Override
    public void getSources(getListCallback callback, String category) {

    }
}
