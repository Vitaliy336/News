package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;

import java.util.List;


@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll (List<Article> articles);

    @Query("DELETE FROM NEWS")
    public void nukeTable();

    @Query("Select * FROM news WHERE _category LIKE :category")
    List<Article> getSourtedNews(String category);

    @Query("SELECT * FROM NEWS")
    List<Article> getAllArticles();

    @Query("SELECT * FROM NEWS WHERE _title LIKE :title")
    List<Article> searchNews(String title);
}
