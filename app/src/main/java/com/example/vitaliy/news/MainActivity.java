package com.example.vitaliy.news;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.vitaliy.news.data.local.LocalNewsDataSource;
import com.example.vitaliy.news.ui.ViewPagerAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;
import com.example.vitaliy.news.ui.searchNews.SearchNewsFragment;
import com.example.vitaliy.news.data.local.service.MyReceiver;
import com.example.vitaliy.news.ui.sources.SourcesFragment;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;
import com.example.vitaliy.news.ui.view.EndlessRecyclerView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private LocalNewsDataSource localNewsDataSource;
    private String sourceId = "";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private final String URL_TAG = "Url";
    private static final long delay = 1000 * 60 * 30;
    ConnectivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkForDelete();
        startAlarm();
    }

    private void startAlarm() {
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        Intent i = new Intent(getBaseContext(), MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                MainActivity.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TopNewsFragment(), "Top News");
        adapter.addFragment(new SearchNewsFragment(), "All News");
        adapter.addFragment(new SourcesFragment(), "Sources");
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

    public boolean checkNetwork(){
        if(manager.getActiveNetworkInfo()!=null){
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
        if(checkNetwork()) {
            Intent intent = new Intent(this, FullNewsActivity.class);
            intent.putExtra(URL_TAG, url);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogStyle);
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
}