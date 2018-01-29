package com.example.vitaliy.news.data.room;

import android.app.Application;
import android.arch.persistence.room.Room;

/**
 * Created by v_shevchyk on 29.01.18.
 */

public class DbInstance extends Application {
    private static DbInstance instance;
    private static final String DATA_BASE_NAME= "news_data_base";
    private NewsDb db;

    public static DbInstance getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), NewsDb.class, DATA_BASE_NAME).build();
    }

    public NewsDb getDbInstance() {
        return db;
    }
}
