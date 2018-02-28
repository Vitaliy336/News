package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.data.model.source.Source;

import java.util.List;

/**Sql queries for sources table
 *
 * @author Vitaliy
 * @version 1.2
 */
@Dao
public interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Source> sources); //checked

    @Query("DELETE FROM SOURCES")
    void nukeTable(); //checked

    @Query("Select * FROM SOURCES WHERE _category LIKE :category")
    List<Source> getSourtedSources(String category);

    @Query("SELECT * FROM SOURCES")
    List<Source> getAllSources(); //checked
}
