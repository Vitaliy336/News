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

@Database(entities = {Article.class, NewsSource.class, Source.class}, version = 1, exportSchema = false)
public abstract class NewsDb extends RoomDatabase {
    private static final String DB_NAME = "news.db";
    private static volatile NewsDb instance;


    static synchronized NewsDb getInstance(Context context){
        if(instance == null){
            instance = create(context);
        }
        return instance;
    }

    private static NewsDb create(final Context context){
        NewsDb db = Room.databaseBuilder(context,
                NewsDb.class, "database-name").build();
        return db;
    }

    public abstract NewsDao getNewsDao();

    public abstract SourcesDao getSourcesDao();
}
