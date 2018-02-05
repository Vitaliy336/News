package com.example.vitaliy.news.pack.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.pack.data.model.news.Article;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

@Dao
public interface NewsDao {
    @Insert
    void insertAll (List<Article> articles);

    @Delete
    void delete (Article article);

    @Delete
    void deleteAll (List<Article> articles);

    @Query("Select * from  news")
    List<Article> getAllArticles();
}
