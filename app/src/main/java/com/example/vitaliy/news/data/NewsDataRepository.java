package com.example.vitaliy.news.data;

import android.text.TextUtils;

import com.example.vitaliy.news.data.local.LocalNewsDataSource;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsDataRepository implements NewsDataSource {
    private LocalNewsDataSource localNewsDataSource;
    private RemoteNewsDataSource remoteNewsDataSource;


    public NewsDataRepository(){
        this.localNewsDataSource = new LocalNewsDataSource();
        this.remoteNewsDataSource = new RemoteNewsDataSource();
    }

    @Override
    public void getHotNews(final getListCallback callback, final String category, final String source, int page) {
        remoteNewsDataSource.getHotNews(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveNewsToCache((List<Article>) article, category, source);
            }

            @Override
            public void onFailure() {
                int thispage = 1;
                localNewsDataSource.getHotNews(callback, category, source, thispage);
            }
        }, category, source, page);
    }

    @Override
    public void getEverything(final getListCallback callback, final String query, final int page) {
        remoteNewsDataSource.getEverything(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveNewsToCache((List<Article>) article, null, null);
            }

            @Override
            public void onFailure() {
                localNewsDataSource.getEverything(callback, query, page);
            }
        }, query, page);
    }

    @Override
    public void getSources(final getListCallback callback, final String category) {
        remoteNewsDataSource.getSources(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveSourcesToCache((List<Source>) article);
            }

            @Override
            public void onFailure() {
                localNewsDataSource.getSources(callback, category);
            }
        }, category);
    }
}
