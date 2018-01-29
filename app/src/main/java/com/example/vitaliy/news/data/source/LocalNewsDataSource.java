package com.example.vitaliy.news.data.source;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;

import com.example.vitaliy.news.data.room.NewsDao;
import com.example.vitaliy.news.data.room.NewsDb;
import com.example.vitaliy.news.data.room.SourcesDao;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class LocalNewsDataSource implements NewsDataSource {

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
