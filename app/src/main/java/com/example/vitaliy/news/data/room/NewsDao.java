package com.example.vitaliy.news.data.room;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.vitaliy.news.data.model.news.Article;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public interface NewsDao {
    @Insert
    void insertAll (List<Article> articles);

    @Delete
    void delete (Article article);

    @Query("Select * from  Article")
    List<Article> getAllArticles();
}
