package com.example.vitaliy.news.data.source;

import android.util.Log;

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
                localNewsDataSource.saveNewsToCache(setCategory((List<Article>) article, category));//here
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
        remoteNewsDataSource.getSources(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveSourcesToCache((List<Source>) article);
            }

            @Override
            public void onFailure() {
                Log.e("LocalNDSSources", "OnFailure");
                localNewsDataSource.getSources(callback, category);
            }
        }, category);
    }

    public List<Article> setCategory(List<Article> articles, String category){
        for(Article article : articles){
            article.setCategory(category);
        }
        return articles;
    }
}
