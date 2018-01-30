package com.example.vitaliy.news;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.vitaliy.news.data.room.NewsDb;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class App extends Application {
    private static App instance;
    private static final String DATA_BASE_NAME= "news_data_base";
    private NewsDb db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), NewsDb.class, DATA_BASE_NAME).allowMainThreadQueries().build();
    }

    public NewsDb getDbInstance() {
        return db;
    }
}
