package com.example.vitaliy.news;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.vitaliy.news.data.room.NewsDb;

/**
 * Created by gleb on 11/16/17.
 */

public class App extends Application {

    private static App instance;
    private NewsDb db;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        db = Room.databaseBuilder(getApplicationContext(), NewsDb.class, "data-database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    public NewsDb getDatabaseInstance() {
        return db;
    }
}
