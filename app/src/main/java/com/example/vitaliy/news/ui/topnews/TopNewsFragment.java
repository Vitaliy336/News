package com.example.vitaliy.news.ui.topnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class TopNewsFragment extends Fragment implements TopNewsContract.ITopNewsView {

    private TextView testTV;
    TopNewsContract.ITopNewsPresenter presenter;
    private View rootView;
    private RecyclerView categoriesRV;
    private CategoriesAdapter categoriesAdapter;
    private List<String> categoriesList = new ArrayList<>();

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
        presenter.prepareCategories();
    }

    private void initView() {
        testTV = rootView.findViewById(R.id.HotTv);
        categoriesAdapter = new CategoriesAdapter();
        categoriesList.add("Sport");
        categoriesList.add("World");
        categoriesList.add("Politic");
        categoriesList.add("Others");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        categoriesRV = rootView.findViewById(R.id.categoriesRV);
        categoriesRV.setLayoutManager(layoutManager);
        categoriesRV.setAdapter(categoriesAdapter);
    }

    private void initListener() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        testTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onTextClick();
            }
        });
    }


    @Override
    public void displayCategories(List<String> categories) {
        categoriesAdapter.setData(categories);
    }

    @Override
    public void displayToastMessage() {
        Toast.makeText(getActivity(), "@@@@", Toast.LENGTH_SHORT).show();
    }

}
