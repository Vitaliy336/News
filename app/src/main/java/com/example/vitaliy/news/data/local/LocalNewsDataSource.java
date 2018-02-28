package com.example.vitaliy.news.data.local;

import android.text.TextUtils;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.room.NewsDb;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Handles data from database
 */
public class LocalNewsDataSource implements NewsDataSource {

    @Override
    public void getHotNews(getListCallback callback, String category, String source, int page, String country) {
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.loadNews(callback, category, source);
    }

    @Override
    public void getEverything(getListCallback callback, String query, int page, String order) {
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.searchNews(callback, query);
    }

    @Override
    public void getSources(getListCallback callback, String category) {
        MySourceTask mySourceTask = new MySourceTask();
        mySourceTask.loadsources(callback, category);
    }

    public void saveNewsToCache(List<Article> article, String category, String source) {
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.addNews(setFilters(article, category, source));

    }

    public void saveSourcesToCache(List<Source> sources) {
      MySourceTask mySourceTask = new MySourceTask();
      mySourceTask.addSources(sources);
    }

    public void deleteOldNews(){
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.deleteOld();
    }

    public void getItemsCount(MyNewsTask.AsyncCompleate asyncCompleate){
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.getItemsCount(asyncCompleate);
    }

    public void nukeNews(){
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.nukeNews();
    }

    public List<Article> setFilters(List<Article> articles, String category, String source) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        if (!TextUtils.isEmpty(category)) {
            for (Article article : articles) {
                article.setCategory(category);
                article.setAddTime(dateFormat.format(date));
            }
        }
        if(!TextUtils.isEmpty(source)) {
            for (Article article : articles) {
                article.setSourceId(source);
                article.setAddTime(dateFormat.format(date));
            }
        } else {
            for (Article article : articles){
                article.setAddTime(dateFormat.format(date));
            }
        }
        return articles;
    }

}
