package com.example.vitaliy.news.ui.allnews;

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

public class AllNewsFragment extends Fragment implements AllNewsContract.IAllNewsView {
    private View rootView;
    private AllNewsContract.IAllNewsPresenter presenter;
    private TextView tv;
    private CategoriesAdapter categoriesAdapter = new CategoriesAdapter();
    private RecyclerView categories;
    private LinearLayoutManager layoutManager;
    private List<String> categoriesList = new ArrayList<>();

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
        presenter.prepareCategories();
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

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        categories = (RecyclerView) rootView.findViewById(R.id.categories);
        categories.setLayoutManager(layoutManager);
        categories.setAdapter(categoriesAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_news_fragment, container, false);
        return rootView;
    }

    @Override
    public void displayCategories(List<String> categories) {
        categoriesAdapter.setData(categories);
    }

    @Override
    public void ShowToastMessage() {
        Toast.makeText(getActivity(), "Did i understand it?", Toast.LENGTH_SHORT).show();
    }


}
