package com.example.vitaliy.news.data.local.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;


public class MyReceiver extends BroadcastReceiver {

    public static final long delay = 1000*60; //* 60 * 30;

    @Override
    public void onReceive(Context context, Intent intent) {
        scheduleAlarms(context);
    }

    static void scheduleAlarms(Context context) {
        AlarmManager mgr=
                (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i=new Intent(context, NewsService.class);
        PendingIntent pi=PendingIntent.getService(context, 0, i, 0);

        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime(), delay, pi);
    }
}

