package com.example.vitaliy.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.vitaliy.news.ui.ViewPagerAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;
import com.example.vitaliy.news.ui.searchNews.SearchNewsFragment;
import com.example.vitaliy.news.ui.sources.SourcesFragment;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar toolbar;
    private String sourceId = "";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private final String URL_TAG = "Url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
        Intent intent = new Intent(this, FullNewsActivity.class);
        intent.putExtra(URL_TAG, url);
        startActivity(intent);
    }
}