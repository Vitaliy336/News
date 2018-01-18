package com.example.vitaliy.news.ui.topnews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.model.Article;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class TopNewsFragment extends Fragment implements TopNewsContract.ITopNewsView {

    TopNewsContract.ITopNewsPresenter presenter;
    private View rootView;
    private RecyclerView categoriesRV, newsRV ;
    private CategoriesAdapter categoriesAdapter;
    private NewsAdapter newsAdapter;

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
        RemoteNewsDataSource dataSource = new RemoteNewsDataSource();
        presenter = new TopNewsPresenter(dataSource);
        presenter.attachView(this);
        presenter.prepareCategories();
        presenter.prepareNews();

    }

    private void initView() {
        categoriesAdapter = new CategoriesAdapter();
        newsAdapter = new NewsAdapter();
        LinearLayoutManager layoutManagerForCategories = new LinearLayoutManager(getActivity());
        layoutManagerForCategories.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManagerForNews = new LinearLayoutManager(getActivity());
        layoutManagerForNews.setOrientation(LinearLayoutManager.VERTICAL);

        newsRV = rootView.findViewById(R.id.newsRV);
        newsRV.setLayoutManager(layoutManagerForNews);
        newsRV.setAdapter(newsAdapter);



        categoriesRV = rootView.findViewById(R.id.categoriesRV);
        categoriesRV.setLayoutManager(layoutManagerForCategories);
        categoriesRV.setAdapter(categoriesAdapter);
    }


    private void initListener() {
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void OnClick(Article article) {
                Toast.makeText(getActivity(), article.getTitle(), Toast.LENGTH_LONG).show();
                Log.e("Ss", article.getUrl());
                presenter.goToFullNews(article.getUrl());

            }
        });
    }


    @Override
    public void displayCategories(List<String> categories) {
        categoriesAdapter.setData(categories);
    }


    @Override
    public void displayNews(List<Article> news) {
        newsAdapter.setData(news, getActivity());
    }


    @Override
    public void displayToastMessage() {
        Toast.makeText(getActivity(), "@@@@", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowFullNews(String url) {
        Intent intent = new Intent(getActivity(), FullNewsActivity.class);
        intent.putExtra("Url", url);
        startActivity(intent);
    }

}
