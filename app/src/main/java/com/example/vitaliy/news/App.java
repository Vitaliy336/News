package com.example.vitaliy.news;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.data.room.NewsDb;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

/**Singleton class.
 * Create Room and NewsDataRepository instances
 *
 * @author vitaliy
 * @version 1.0
 */

public class App extends Application {

    private static App instance;
    private NewsDb db;
    private NewsDataRepository dataRepository;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dataRepository = new NewsDataRepository();
        db = Room.databaseBuilder(getApplicationContext(), NewsDb.class, "data-database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public NewsDb getDatabaseInstance() {
        return db;
    }
    public NewsDataRepository getDataRepositoryInstance(){
        return dataRepository;
    }
}
