package com.example.vitaliy.news.data.source;

import android.text.TextUtils;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;

import java.util.List;

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
                localNewsDataSource.saveNewsToCache(setCategory((List<Article>) article, category, source));
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

    public List<Article> setCategory(List<Article> articles, String category, String source){
        if(!TextUtils.isEmpty(category)) {
            for (Article article : articles) {
                article.setCategory(category);
            }
        } else {
            for(Article article : articles){
                article.setSourceId(source);
            }
        }
        return articles;
    }
}
