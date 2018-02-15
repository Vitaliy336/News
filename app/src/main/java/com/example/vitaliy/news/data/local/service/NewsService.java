package com.example.vitaliy.news.data.local.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.data.local.LocalNewsDataSource;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.room.NewsDb;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.List;

public class NewsService extends Service {
    private NewsDataRepository newsDataRepository;
    private LocalNewsDataSource localNewsDataSource;
    private NewsDb db;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        check();
        return Service.START_STICKY;
    }

    private void check() {
        Log.e("Service", "check");
        localNewsDataSource = new LocalNewsDataSource();
        newsDataRepository = App.getInstance().getDataRepositoryInstance();
        db = App.getInstance().getDatabaseInstance();
        final int count = db.getDataDao().count();

        newsDataRepository.getHotNews(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                localNewsDataSource.saveNewsToCache((List<Article>) article);
                if(count < db.getDataDao().count()){
                    createNotification();
                }
            }

            @Override
            public void onFailure() {

            }
        }, null, null, 1);
    }


    private void createNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                        .setLights(Color.RED, 3000, 3000)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getString(R.string.update));

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
        Log.e("Notification show", "data updated");
    }
}
