package com.example.vitaliy.news.data.local.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;


public class MyReceiver extends BroadcastReceiver {

    public static final long delay = 3000; //* 60 * 30;
    private SharedPreferences sharedPreferences;


    @Override
    public void onReceive(Context context, Intent intent) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(sharedPreferences.getBoolean("notifications", true)){
            scheduleAlarms(context);
        } else {
            scheduleAlarms1(context);
        }

    }

    private void scheduleAlarms1(Context context) {
        AlarmManager mgr =
                (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, NewsService.class);
        PendingIntent pi = PendingIntent.getService(context, 322, i, 0);

        mgr.cancel(pi);
    }

    static void scheduleAlarms(Context context) {
        Log.e("receiver", "ss");
        AlarmManager mgr =
                (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, NewsService.class);
        PendingIntent pi = PendingIntent.getService(context, 322, i, 0);

        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(), delay, pi);
    }
}

