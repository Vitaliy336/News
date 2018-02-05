package com.example.vitaliy.news.pack.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.pack.data.model.news.Article;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll (List<Article> articles);

    @Delete
    void delete (Article article);

    @Query("DELETE FROM NEWS")
    public void nukeTable();

    @Query("SELECT * FROM NEWS WHERE _category LIKE :category")
    List<Article> getAllArticles(String category);

    @Query("SELECT * FROM NEWS")
    List<Article> getAllArticles();
}
