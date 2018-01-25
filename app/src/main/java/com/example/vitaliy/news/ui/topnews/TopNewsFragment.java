
package com.example.vitaliy.news.ui.topnews;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;

import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class TopNewsFragment extends Fragment implements TopNewsContract.ITopNewsView {

    private TopNewsContract.ITopNewsPresenter presenter;
    private RelativeLayout relativeLayout;
    private View rootView;
    private RecyclerView categoriesRV, newsRV;
    private CategoriesAdapter categoriesAdapter;
    private NewsAdapter newsAdapter;
    private TextView sourceEt;
    private ImageView clear;

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
        initPresenter();
        getSourceID();
        updateData();
    }

    private void updateData() {
        presenter.start();
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
    }

    private void initView() {
        sourceEt = rootView.findViewById(R.id.sourceID);
        sourceEt.setFocusable(false);
        sourceEt.setClickable(false);

        clear = rootView.findViewById(R.id.clearbtn);

        relativeLayout = rootView.findViewById(R.id.sourcesFilter);
        categoriesAdapter = new CategoriesAdapter();
        newsAdapter = new NewsAdapter();

        LinearLayoutManager layoutManagerForCategories = new LinearLayoutManager(getActivity());
        layoutManagerForCategories.setOrientation(LinearLayoutManager.HORIZONTAL);
        LinearLayoutManager layoutManagerForNews = new LinearLayoutManager(getActivity());
        layoutManagerForNews.setOrientation(LinearLayoutManager.VERTICAL);

        newsRV = rootView.findViewById(R.id.newsT);
        newsRV.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        newsRV.setLayoutManager(layoutManagerForNews);
        newsRV.setAdapter(newsAdapter);

        categoriesRV = rootView.findViewById(R.id.categoriesT);
        categoriesRV.setLayoutManager(layoutManagerForCategories);
        categoriesRV.setAdapter(categoriesAdapter);
    }


    private void initListener() {
        newsAdapter.setOnItemClickListener(new NewsAdapter.onNewsClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void OnClick(Article article) {
                presenter.goToFullNews(article.getUrl());
            }
        });
        categoriesAdapter.setCategoryItemClick(new CategoriesAdapter.onCategoryItemClick() {
            @Override
            public void onCatClick(String str) {
                presenter.setCategoryName(str);
                presenter.prepareNews();
                //categoriesRV.setAlpha(0);
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
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideCategories() {
        categoriesRV.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showCategories() {
        categoriesRV.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSourceFilter() {
        relativeLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSourceFilter() {
        relativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void ShowFullNews(String url) {
        Intent intent = new Intent(getActivity(), FullNewsActivity.class);
        intent.putExtra("Url", url);
        startActivity(intent);
    }

    void getSourceID() {
        final String sourceId =((MainActivity)getActivity()).getSourceId();
        presenter.setSourceID(sourceId);
        sourceEt.setText(sourceId);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceEt.setText(null);
                presenter.setSourceID(null);
            }
        });

    }
}
