package com.example.vitaliy.news.pack.data.source;

import android.util.Log;

import com.example.vitaliy.news.pack.data.model.source.Source;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class NewsDataRepository implements NewsDataSource {
    private LocalNewsDataSource localNewsDataSource;
    private RemoteNewsDataSource remoteNewsDataSource;

    public NewsDataRepository(LocalNewsDataSource localNewsDataSource, RemoteNewsDataSource remoteNewsDataSource) {
        this.localNewsDataSource = localNewsDataSource;
        this.remoteNewsDataSource = remoteNewsDataSource;
    }

    @Override
    public void getHotNews(final getListCallback callback, final String category, final String source) {
        remoteNewsDataSource.getHotNews(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveToCashe(article);
            }

            @Override
            public void onFailure() {
                Log.e("NewsDR", "onFailure");
                localNewsDataSource.getHotNews(callback, category, source);
            }
        }, category, source);
    }

    @Override
    public void getEverything(getListCallback callback, String query) {

    }

    @Override
    public void getSources(final getListCallback callback, final String category) {
    }
}
