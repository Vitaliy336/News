package com.example.vitaliy.news.data.source;

import android.text.TextUtils;
import android.util.Log;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.room.NewsDb;

import java.util.List;


public class LocalNewsDataSource implements NewsDataSource {
    private NewsDb db;

    @Override
    public void getHotNews(getListCallback callback, String category, String source) {
        db = App.getInstance().getDatabaseInstance();
        Log.e("S", category);
        if (!TextUtils.isEmpty(category)) {
            callback.onListReceived(db.getDataDao().getSourtedNews(category));
        } else {
            callback.onListReceived(db.getDataDao().getAllArticles());
        }
    }

    @Override
    public void getEverything(getListCallback callback, String query) {
        db = App.getInstance().getDatabaseInstance();
        callback.onListReceived(db.getDataDao().searchNews('%'+query+'%'));
    }

    @Override
    public void getSources(getListCallback callback, String category) {
        db = App.getInstance().getDatabaseInstance();
        if (!TextUtils.isEmpty(category)) {
            callback.onListReceived(db.getSourcesDao().getSourtedSources(category));
        } else {
            callback.onListReceived(db.getSourcesDao().getAllSources());
        }
    }

    public void saveNewsToCache(List<Article> article) {
        db = App.getInstance().getDatabaseInstance();
        db.getDataDao().insertAll(article);
        //db.getDataDao().nukeTable();
    }

    public void saveSourcesToCache(List<Source> sources) {
        db = App.getInstance().getDatabaseInstance();
        db.getSourcesDao().insertAll(sources);
    }
}
