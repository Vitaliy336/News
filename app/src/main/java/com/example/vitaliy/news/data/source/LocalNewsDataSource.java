package com.example.vitaliy.news.data.source;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.room.NewsDb;

import java.util.List;


public class LocalNewsDataSource implements NewsDataSource {
    private NewsDb db;


    @Override
    public void getHotNews(getListCallback callback, String category, String source) {
        MyNewsTask myTask = new MyNewsTask();
        myTask.loadNews(callback, category, source);
    }

    @Override
    public void getEverything(getListCallback callback, String query) {
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.searchNews(callback, query);
    }

    @Override
    public void getSources(getListCallback callback, String category) {
        MySourceTask mySourceTask = new MySourceTask();
        mySourceTask.loadsources(callback, category);
    }

    public void saveNewsToCache(List<Article> article) {
        MyNewsTask myNewsTask = new MyNewsTask();
        myNewsTask.addNews(article);

    }

    public void saveSourcesToCache(List<Source> sources) {
      MySourceTask mySourceTask = new MySourceTask();
      mySourceTask.addSources(sources);
    }
}
