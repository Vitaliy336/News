package com.example.vitaliy.news.data.source;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.room.NewsDb;

import java.util.ArrayList;
import java.util.List;

public class MySourceTask {
    private NewsDb db;

    public void loadsources(NewsDataSource.getListCallback callback, String category) {
        LoadSourceTask loadSourceTask = new LoadSourceTask(callback, category);
        loadSourceTask.execute();
    }

    public void addSources(List<Source> sources) {
        AddSourceTask addSourceTask = new AddSourceTask(sources);
        addSourceTask.execute();
    }

    class LoadSourceTask extends AsyncTask<Void, Void, List<Source>> {
        private NewsDataSource.getListCallback callback;
        private String category;

        public LoadSourceTask(NewsDataSource.getListCallback callback, String category) {
            this.callback = callback;
            this.category = category;
        }

        @Override
        protected List<Source> doInBackground(Void... voids) {
            db = App.getInstance().getDatabaseInstance();
            if (!TextUtils.isEmpty(category)) {
                return db.getSourcesDao().getSourtedSources(category);
            } else {
                return db.getSourcesDao().getAllSources();
            }
        }

        @Override
        protected void onPostExecute(List<Source> sources) {
            super.onPostExecute(sources);
            callback.onListReceived(sources);
        }
    }

    class AddSourceTask extends AsyncTask<Void, Void, Void> {
        List<Source> sources = new ArrayList<>();

        public AddSourceTask(List<Source> sources) {
            this.sources = sources;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db = App.getInstance().getDatabaseInstance();
            db.getSourcesDao().insertAll(sources);
            return null;
        }
    }
}
