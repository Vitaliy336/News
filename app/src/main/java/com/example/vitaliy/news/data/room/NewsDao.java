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
    void insertAll(List<Article> articles); //checked

    @Query("DELETE FROM NEWS")
    void nukeTable(); //checked

    @Query("Select * FROM NEWS WHERE _category LIKE :category")
    List<Article> getNewsWithCategory(String category); //checked

    @Query("SELECT * FROM NEWS WHERE _sourceId LIKE :source")
    List<Article> getNewsWithSource(String source); //checked

    @Query("SELECT * FROM NEWS")
    List<Article> getAllArticles(); //checked

    @Query("SELECT * FROM NEWS WHERE _title LIKE :title")
    List<Article> searchNews(String title); //checked

    @Query("SELECT COUNT(*) FROM NEWS")
    int count(); //checked

    @Delete
    void deleteArticle(Article article); //checked
}
