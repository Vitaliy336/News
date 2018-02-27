package com.example.vitaliy.news.modules;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.ArrayList;
import java.util.List;

public class GoodDataSource {
    private List<Article> articles = new ArrayList<>();

    public GoodDataSource() {
        articles.add(new Article());
    }

    public NewsDataSource dataSource = new NewsDataSource() {
        @Override
        public void getHotNews(getListCallback callback, String category, String source, int page, String country) {
            callback.onListReceived(articles);
        }

        @Override
        public void getEverything(getListCallback callback, String query, int page, String order) {
            callback.onListReceived(articles);
        }

        @Override
        public void getSources(getListCallback callback, String category) {
            callback.onListReceived(articles);
        }
    };
}
