package com.example.vitaliy.news.background;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.vitaliy.news.App;
import com.example.vitaliy.news.MainActivity;
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
        createNotification();
        check();
        return Service.START_STICKY;
    }

    private void check() {
        Log.e("Service", "check");
        localNewsDataSource = new LocalNewsDataSource();
        newsDataRepository = App.getInstance().getDataRepositoryInstance();
        db = App.getInstance().getDatabaseInstance();
        final int count = db.getDataDao().count();
        Log.e("db.count", String.valueOf(count));

        newsDataRepository.getHotNews(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                localNewsDataSource.saveNewsToCache((List<Article>) article, null, null);
                Log.e("db", String.valueOf(db.getDataDao().count()));
                if(count < db.getDataDao().count()){
                    Log.e("db.count", String.valueOf(db.getDataDao().count()));
                    createNotification();
                }
            }

            @Override
            public void onFailure() {

            }
        }, null, null, 1, null);
    }


    private void createNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(intent);

        PendingIntent res = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_dialog_email)
                        .setVibrate(new long[] { 1000, 1000})
                        .setLights(Color.GREEN, 3000, 3000)
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(getString(R.string.update))
                        .setContentIntent(res);



        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
       // notificationManager.cancel(1);
        Log.e("Notification show", "data updated");
    }
}
