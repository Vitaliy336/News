package com.example.vitaliy.news.ui.allnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class AllNewsFragment extends Fragment implements AllNewsContract.IAllNewsView {
    private View rootView;
    private AllNewsContract.IAllNewsPresenter presenter;
    private TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        intiListener();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new AllNewsPresenter();
        presenter.attachView(this);
    }

    private void intiListener() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onTextClickListener();
            }
        });
    }

    private void initView() {
        tv = (TextView)rootView.findViewById(R.id.AllNewsTv);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_news_fragment, container, false);
        return rootView;
    }

    @Override
    public void displayNews(List<String> news) {

    }

    @Override
    public void ShowToastMessage() {
        Toast.makeText(getActivity(), "Did i understand it?", Toast.LENGTH_SHORT).show();
    }
}
