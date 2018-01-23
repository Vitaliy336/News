package com.example.vitaliy.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.vitaliy.news.ui.allnews.AllNewsFragment;
import com.example.vitaliy.news.ui.sources.SourcesFragment;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SourcesFragment.OnSourceDataListener{
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private TopNewsFragment topNewsFragment = new TopNewsFragment();


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragmet(topNewsFragment, "Top News");
        adapter.addFragmet(new AllNewsFragment(), "All News");
        adapter.addFragmet(new SourcesFragment(), "Sources");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onSourceDataReceived(String data) {
        adapter.onSourceDataReceived(data);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter implements SourcesFragment.OnSourceDataListener{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> Titles = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragmet(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            Titles.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Titles.get(position);
        }

        @Override
        public void onSourceDataReceived(String data) {
            if(topNewsFragment !=null){
                topNewsFragment.onSourceDataReceived(data);
            }
        }
    }


}
