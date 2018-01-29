package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.news.NewsSource;
import com.example.vitaliy.news.data.model.source.Source;

/**
 * Created by v_shevchyk on 29.01.18.
 */

@Database(entities = {Article.class, Source.class}, version = 1, exportSchema = false)
public abstract class NewsDb extends RoomDatabase {
    public abstract NewsDao getDataDao();
    public abstract SourcesDao sourcesDao();
}
