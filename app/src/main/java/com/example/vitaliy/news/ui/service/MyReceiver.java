package com.example.vitaliy.news.ui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by v_shevchyk on 14.02.18.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Context oAppContext = context.getApplicationContext();

        if (oAppContext == null) {
            oAppContext = context;
        }

        Intent serviceIntent = new Intent(oAppContext, MyService.class);
        oAppContext.startService(serviceIntent);
    }
}

