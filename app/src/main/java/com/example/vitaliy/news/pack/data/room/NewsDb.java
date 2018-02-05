package com.example.vitaliy.news.pack.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vitaliy.news.pack.data.model.news.Article;
import com.example.vitaliy.news.pack.data.model.source.Source;

/**
 * Created by v_shevchyk on 29.01.18.
 */

@Database(entities = {Article.class, Source.class}, version = 5, exportSchema = false)
public abstract class NewsDb extends RoomDatabase {

    public abstract NewsDao getDataDao();
    public abstract SourcesDao getSourcesDao();
}
