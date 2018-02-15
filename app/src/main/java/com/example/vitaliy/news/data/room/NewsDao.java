package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.data.model.news.Article;

import java.util.List;

import retrofit2.http.DELETE;


@Dao
public interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Article> articles);

    @Query("DELETE FROM NEWS")
    void nukeTable();

    @Query("Select * FROM NEWS WHERE _category LIKE :category")
    List<Article> getNewsWithCategory(String category);

    @Query("SELECT * FROM NEWS WHERE _sourceId LIKE :source")
    List<Article> getNewsWithSource(String source);

    @Query("SELECT * FROM NEWS")
    List<Article> getAllArticles();

    @Query("SELECT * FROM NEWS WHERE _title LIKE :title")
    List<Article> searchNews(String title);

    @Query("SELECT COUNT(*) FROM NEWS")
    int count();

    @Delete
    void deleteArticle(Article article);
}
