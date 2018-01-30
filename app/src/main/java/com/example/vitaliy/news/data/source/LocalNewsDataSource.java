package com.example.vitaliy.news.data.source;

import android.util.Log;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.room.NewsDb;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class LocalNewsDataSource implements NewsDataSource {
    private NewsDb db = App.getInstance().getDbInstance();

    @Override
    public void getHotNews(getListCallback callback, String category, String source) {
        Log.e("LocalNDS", "get?");
        callback.onListReceived(db.getDataDao().getAllArticles());
    }

    @Override
    public void getEverything(getListCallback callback, String query) {
    }

    @Override
    public void getSources(getListCallback callback, String category) {
      //  callback.onListReceived(db.sourcesDao().getAllSources());
    }

    public void saveToCashe(List<?> article) {
        Log.e("LocalNDS", "isnert?");
        db.getDataDao().insertAll((List<Article>) article);
    }
}
