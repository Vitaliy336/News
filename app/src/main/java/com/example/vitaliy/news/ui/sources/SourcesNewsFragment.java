package com.example.vitaliy.news.ui.sources;

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
import com.example.vitaliy.news.ui.adapters.NewsAdapter;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesNewsFragment extends Fragment implements SourccesNewsContract.ISourcesNewsView {
    private SourcesNewsPresenter presenter;
    private View rootView;
    private RecyclerView categories, news;
    private CategoriesAdapter categoriesAdapter;
    private NewsAdapter newsAdapter;
    private TextView tv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sources_news_fragment, container, false);
        return rootView;
    }


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
        presenter = new SourcesNewsPresenter();
        presenter.attachView(this);
    }

    private void intiListener() {
    }

    private void initView() {
        categories = rootView.findViewById(R.id.categoriesS);
        news = rootView.findViewById(R.id.newsS);

        categoriesAdapter = new CategoriesAdapter();
        newsAdapter = new NewsAdapter();

        LinearLayoutManager layoutManagerC = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManagerN = new LinearLayoutManager(getActivity());
        layoutManagerC.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManagerN.setOrientation(LinearLayoutManager.VERTICAL);

        news.setLayoutManager(layoutManagerN);
        news.setAdapter(newsAdapter);

        categories.setLayoutManager(layoutManagerC);
        categories.setAdapter(categoriesAdapter);

    }

    @Override
    public void showCategories() {

    }

    @Override
    public void showNews() {

    }

    @Override
    public void showFullNews() {

    }
}
