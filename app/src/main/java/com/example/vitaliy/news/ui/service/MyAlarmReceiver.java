package com.example.vitaliy.news.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class MyAlarmReceiver extends BroadcastReceiver {
    public static final int CODE = 322;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MyAlarmReceiver", "Welcome");
        Intent i = new Intent(context, MyService.class);
        i.putExtra("fooo", "baar");
        context.startService(i);

    }
}
