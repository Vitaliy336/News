package com.example.vitaliy.news.pack.data.source;

import android.text.TextUtils;
import android.util.Log;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.pack.data.model.news.Article;
import com.example.vitaliy.news.pack.data.model.source.Source;
import com.example.vitaliy.news.pack.data.room.NewsDb;

import java.util.List;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class LocalNewsDataSource implements NewsDataSource {
   private NewsDb db;

    @Override
    public void getHotNews(getListCallback callback, String category, String source) {
        Log.e("LocalNDS", "get?");
        db = App.getInstance().getDatabaseInstance();
        if(!TextUtils.isEmpty(category)) {
            callback.onListReceived(db.getDataDao().getAllArticles(category));

        } else{
            callback.onListReceived(db.getDataDao().getAllArticles());
        }
    }

    @Override
    public void getEverything(getListCallback callback, String query) {
    }

    @Override
    public void getSources(getListCallback callback, String category) {
        db = App.getInstance().getDatabaseInstance();
    }

    public void saveToCashe(List<?> article) {
        Log.e("LocalNDS", "isnert?");
        db = App.getInstance().getDatabaseInstance();
        db.getDataDao().insertAll((List<Article>) article);
        //db.getDataDao().nukeTable();
    }
}
