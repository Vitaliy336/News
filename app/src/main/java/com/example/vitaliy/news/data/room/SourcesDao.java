package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.data.model.source.Source;

import java.util.List;

@Dao
public interface SourcesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll (List<Source> sources);

    @Delete
    void delete (Source source);

    @Query("DELETE FROM sources")
    void nukeTable();

    @Query("Select * FROM SOURCES WHERE _category LIKE :category")
    List<Source> getSourtedSources(String category);

    @Query("SELECT * FROM SOURCES")
    List<Source> getAllSources();
}
