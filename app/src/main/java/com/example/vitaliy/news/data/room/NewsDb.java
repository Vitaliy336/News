package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;

/**Room database class
 *
 * @author Vitaliy
 * @version 1.9
 */
@Database(entities = {Article.class, Source.class}, version = 9, exportSchema = false)
public abstract class NewsDb extends RoomDatabase {

    public abstract NewsDao getDataDao();

    public abstract SourcesDao getSourcesDao();
}
