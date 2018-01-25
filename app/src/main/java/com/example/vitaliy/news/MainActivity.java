package com.example.vitaliy.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vitaliy.news.ui.ActivityCallBack;
import com.example.vitaliy.news.ui.ViewPagerAdapter;
import com.example.vitaliy.news.ui.allnews.AllNewsFragment;
import com.example.vitaliy.news.ui.sources.SourcesFragment;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;

public class MainActivity extends AppCompatActivity implements ActivityCallBack {
    private Toolbar toolbar;
    private String sourceID = "";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

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
        adapter.addFragmet(new TopNewsFragment(), "Top News");
        adapter.addFragmet(new AllNewsFragment(), "All News");
        adapter.addFragmet(new SourcesFragment(), "Sources");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void sendSourceID(String id) {
        sourceID = id;
    }

    @Override
    public String getSourceID() {
        return sourceID;
    }
}