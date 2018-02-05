package com.example.vitaliy.news.pack.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.pack.data.model.news.Article;
import com.example.vitaliy.news.pack.data.model.source.Source;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

@Dao
public interface SourcesDao {
    @Insert
    void insertAll (List<Source> sources);

    @Delete
    void delete (Source source);

    @Query("DELETE FROM sources")
    void nukeTable();

    @Query("Select * FROM SOURCES")
    List<Article> getAllSources();
}
