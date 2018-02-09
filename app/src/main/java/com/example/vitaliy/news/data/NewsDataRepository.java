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

    public NewsDataRepository(LocalNewsDataSource localNewsDataSource, RemoteNewsDataSource remoteNewsDataSource) {
        this.localNewsDataSource = localNewsDataSource;
        this.remoteNewsDataSource = remoteNewsDataSource;
    }

    public NewsDataRepository(){
        this.localNewsDataSource = new LocalNewsDataSource();
        this.remoteNewsDataSource = new RemoteNewsDataSource();
    }

    @Override
    public void getHotNews(final getListCallback callback, final String category, final String source) {
        remoteNewsDataSource.getHotNews(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveNewsToCache(setFilters((List<Article>) article, category, source));
            }

            @Override
            public void onFailure() {
                localNewsDataSource.getHotNews(callback, category, source);
            }
        }, category, source);
    }

    @Override
    public void getEverything(final getListCallback callback, final String query) {
        remoteNewsDataSource.getEverything(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveNewsToCache((List<Article>) article);
            }

            @Override
            public void onFailure() {
                localNewsDataSource.getEverything(callback, query);
            }
        }, query);
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

    public List<Article> setFilters(List<Article> articles, String category, String source) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        if (!TextUtils.isEmpty(category)) {
            for (Article article : articles) {
                article.setCategory(category);
                article.setAddTime(dateFormat.format(date));
            }
        }
        if(!TextUtils.isEmpty(source)) {
            for (Article article : articles) {
                article.setSourceId(source);
                article.setAddTime(dateFormat.format(date));
            }
        } else {
          for (Article article : articles){
              article.setAddTime(dateFormat.format(date));
          }
        }
        return articles;
    }
}
