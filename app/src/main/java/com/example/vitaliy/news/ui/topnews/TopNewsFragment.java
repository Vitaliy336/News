package com.example.vitaliy.news.ui.topnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitaliy.news.R;

import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class TopNewsFragment extends Fragment implements TopNewsContract.ITopNewsView {

    private TextView testTV;
    private TopNewsContract.ITopNewsPresenter presenter;
    private View rootView;
    private RecyclerView topNewsRv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.top_news_fragment, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        testTV = (TextView)rootView.findViewById(R.id.HotTv);
        topNewsRv = (RecyclerView)rootView.findViewById(R.id.topNewsRV);
        initListener();
    }

    private void initListener() {

        testTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    @Override
    public void displayNews(List<String> news) {

    }
}
