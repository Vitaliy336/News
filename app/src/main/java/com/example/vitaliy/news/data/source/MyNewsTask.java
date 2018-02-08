package com.example.vitaliy.news.data.source;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.room.NewsDb;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyNewsTask {
    NewsDb db;

    public void loadNews(NewsDataSource.getListCallback callback, String category, String source) {
        LoadNewsTask loadNewsTask = new LoadNewsTask(callback, category, source);
        loadNewsTask.execute();
    }

    public void deleteOld() {
        DeleteOldNews deleteOldNews = new DeleteOldNews();
        deleteOldNews.execute();
    }

    public void searchNews(NewsDataSource.getListCallback callback, String title) {
        SearchNewsTask searchNewsTask = new SearchNewsTask(callback, title);
        searchNewsTask.execute();
    }

    public void addNews(List<Article> articles) {
        AddNewsTask addNewsTask = new AddNewsTask(articles);
        addNewsTask.execute();
    }

    class AddNewsTask extends AsyncTask<Void, Void, Void> {
        List<Article> articles = new ArrayList<>();

        public AddNewsTask(List<Article> articles) {
            this.articles = articles;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db = App.getInstance().getDatabaseInstance();
            db.getDataDao().insertAll(articles);
            return null;
        }
    }

    class DeleteOldNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Date currentdate = new Date();
            Date postDate;
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            db = App.getInstance().getDatabaseInstance();
            for (Article article : db.getDataDao().getAllArticles()) {
                try {
                    postDate = dateFormat.parse(article.getAddTime());
                    if (TimeUnit.MICROSECONDS.toDays(Math.abs(currentdate.getTime() - postDate.getTime())) > 3) {
                        db.getDataDao().deleteArticle(article);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    class LoadNewsTask extends AsyncTask<Void, Void, List<Article>> {
        private final NewsDataSource.getListCallback callback;
        private String category;
        private String source;

        LoadNewsTask(NewsDataSource.getListCallback callback, String category, String source) {
            this.callback = callback;
            this.category = category;
            this.source = source;
        }

        @Override
        protected List<Article> doInBackground(Void... voids) {
            db = App.getInstance().getDatabaseInstance();
            if (!TextUtils.isEmpty(category)) {
                return db.getDataDao().getNewsWithCategory(category);
            }
            if (!TextUtils.isEmpty(source)) {
                return db.getDataDao().getNewsWithSource(source);
            }else {
                return db.getDataDao().getAllArticles();
            }
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            callback.onListReceived(articles);
        }
    }

    class SearchNewsTask extends AsyncTask<Void, Void, List<Article>> {
        private final NewsDataSource.getListCallback callback;
        private String title;

        SearchNewsTask(NewsDataSource.getListCallback callback, String title) {
            this.callback = callback;
            this.title = title;
        }

        @Override
        protected List<Article> doInBackground(Void... voids) {
            db = App.getInstance().getDatabaseInstance();
            return db.getDataDao().searchNews('%' + title + '%');
        }

        @Override
        protected void onPostExecute(List<Article> articles) {
            super.onPostExecute(articles);
            callback.onListReceived(articles);
        }
    }

}
