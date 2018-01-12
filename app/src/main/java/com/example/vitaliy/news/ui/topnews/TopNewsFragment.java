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
    TopNewsContract.ITopNewsPresenter presenter;
    private View rootView;
    private RecyclerView topNewsRv;
    private TopNewsAdapter newsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.top_news_fragment, container, false);
        return rootView;
    }

    private void initPresenter() {
        presenter = new TopNewsPresenter();
        presenter.attachView(this);
    }

    private void initView() {
        testTV = rootView.findViewById(R.id.HotTv);
        topNewsRv = rootView.findViewById(R.id.topNewsRV);
    }

    private void initListener() {

        testTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onTextClick();
            }
        });
        newsAdapter = new TopNewsAdapter();
        topNewsRv.setAdapter(newsAdapter);
    }


    @Override
    public void displayNews(List<String> news) {

    }

    @Override
    public void displayToastMessage() {
        Toast.makeText(getActivity(), "@@@@", Toast.LENGTH_SHORT).show();
    }

}
