package com.example.vitaliy.news.data.source;

import com.example.vitaliy.news.data.room.DbInstance;
import com.example.vitaliy.news.data.room.NewsDb;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class LocalNewsDataSource implements NewsDataSource {
    private NewsDb db = DbInstance.getInstance().getDbInstance();

    @Override
    public void getHotNews(getListCallback callback, String category, String source) {
       // callback.onListReceived(db.getDataDao().getAllArticles());
    }

    @Override
    public void getEverything(getListCallback callback, String query) {
    }

    @Override
    public void getSources(getListCallback callback, String category) {
      //  callback.onListReceived(db.sourcesDao().getAllSources());
    }
}
