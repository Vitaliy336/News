package com.example.vitaliy.news.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vitaliy.news.ui.allnews.AllNewsFragment;
import com.example.vitaliy.news.ui.sources.SourcesFragment;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 24.01.18.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
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
}