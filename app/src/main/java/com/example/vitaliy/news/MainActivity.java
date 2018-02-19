package com.example.vitaliy.news;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vitaliy.news.data.local.LocalNewsDataSource;
import com.example.vitaliy.news.data.local.service.MyReceiver;
import com.example.vitaliy.news.data.local.service.NewsService;
import com.example.vitaliy.news.ui.ViewPagerAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;
import com.example.vitaliy.news.ui.preference.MyPreference;
import com.example.vitaliy.news.ui.searchNews.SearchNewsFragment;
import com.example.vitaliy.news.ui.sources.SourcesFragment;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private LocalNewsDataSource localNewsDataSource;
    private String sourceId = "";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private final String URL_TAG = "Url";
    private static final long delay = 1000;// * 60 * 30;
    private ConnectivityManager manager;
    private AlertDialog.Builder builder;
    private AlarmManager am;
    private static final int code = 322;
    private boolean notifications;
    private String country;
    private String order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        preferences();
        manager.cancel(1);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                settings();
                return true;
            case R.id.clear:
                nukeNews();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void nukeNews() {
        builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
        builder.setMessage(R.string.alert_message_clear)
                .setCancelable(false)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        localNewsDataSource.nukeNews();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void settings() {
        Intent intent = new Intent(this, MyPreference.class);
        startActivity(intent);
    }


    private void startAlarm() {
//        Intent i = new Intent(this, MyReceiver.class);
//        PendingIntent pi = PendingIntent.getService(this, code, i, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        am.setRepeating(AlarmManager.ELAPSED_REALTIME,
//                SystemClock.elapsedRealtime(), delay, pi);
        Intent i = new Intent(getBaseContext(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this, code, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(System.currentTimeMillis());

        am.setRepeating(AlarmManager.RTC_WAKEUP, t.getTimeInMillis(), delay, pendingIntent);
    }

    private void checkForDelete() {
        Log.e("Check for delete", " IN");
        localNewsDataSource = new LocalNewsDataSource();
        localNewsDataSource.deleteOldNews();
    }


    private void initView() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TopNewsFragment(), getString(R.string.top_news));
        adapter.addFragment(new SearchNewsFragment(), getString(R.string.all_news));
        adapter.addFragment(new SourcesFragment(), getString(R.string.sources));
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d(TAG, "onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected " + position);
                sourceId = null;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged " + state);

            }
        });
    }

    public boolean checkNetwork() {
        if (manager.getActiveNetworkInfo() != null) {
            return true;
        } else {
            return false;
        }
    }

    public void showTopNews() {
        viewPager.setCurrentItem(0);
    }

    public void showTopNews(String sourceId) {
        Log.d(TAG, "showTopNews " + sourceId);
        this.sourceId = sourceId;
        showTopNews();
    }

    public String getSourceId() {
        return sourceId;
    }

    public void showFullInfo(String url) {
        if (checkNetwork()) {
            Intent intent = new Intent(this, FullNewsActivity.class);
            intent.putExtra(URL_TAG, url);
            startActivity(intent);
        } else {
            builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
            builder.setMessage(R.string.alert_message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        checkForDelete();
        if (notifications) {
            startAlarm();
        } else {
            stopAlarm();
        }
    }

    void stopAlarm() {
//        Intent i = new Intent(this, MyReceiver.class);
//        PendingIntent pi = PendingIntent.getService(this, code, i, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        am.cancel(pi);

        Intent i = new Intent(getBaseContext(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this, code, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar t = Calendar.getInstance();
        t.setTimeInMillis(System.currentTimeMillis());

        am.cancel(pendingIntent);
    }

    private void preferences() {
        notifications = sharedPreferences.getBoolean("notifications", true);
        Log.e("PREFERENCE", String.valueOf(notifications));
        country = sharedPreferences.getString("country_lis", "us");
        Log.e("PREFERENCE", country);
        order = sharedPreferences.getString("order_list", "PublishedAt");
        Log.e("PREFERENCE", order);
    }
}