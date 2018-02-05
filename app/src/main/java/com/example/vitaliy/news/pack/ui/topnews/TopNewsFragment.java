
package com.example.vitaliy.news.pack.ui.topnews;

import android.annotation.SuppressLint;
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

import com.example.vitaliy.news.pack.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.pack.data.model.news.Article;
import com.example.vitaliy.news.pack.data.source.LocalNewsDataSource;
import com.example.vitaliy.news.pack.data.source.NewsDataRepository;
import com.example.vitaliy.news.pack.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.pack.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.pack.ui.adapters.NewsAdapter;

import java.util.Arrays;
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
    private LinearLayoutManager layoutManagerForCategories;
    LinearLayoutManager layoutManagerForNews;
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
        RemoteNewsDataSource remoteNewsDataSource = new RemoteNewsDataSource();
        LocalNewsDataSource localNewsDataSource = new LocalNewsDataSource();
        NewsDataRepository dataRepository = new NewsDataRepository(localNewsDataSource ,remoteNewsDataSource);
        presenter = new TopNewsPresenter(dataRepository);
        presenter.attachView(this);
    }

    private void initView() {
        sourceEt = rootView.findViewById(R.id.sourceID);
        sourceEt.setFocusable(false);
        sourceEt.setClickable(false);

        clear = rootView.findViewById(R.id.clearbtn);

        relativeLayout = rootView.findViewById(R.id.sourcesFilter);
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(Arrays.asList(rootView.getResources().getStringArray(R.array.ctList)));
        newsAdapter = new NewsAdapter();

        layoutManagerForCategories = new LinearLayoutManager(getActivity());
        layoutManagerForCategories.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManagerForNews = new LinearLayoutManager(getActivity());
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
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setSourceID(null);
            }
        });
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
    public void viewSetText(String text) {
        sourceEt.setText(text);
    }

    @Override
    public void showFullNews(String url) {
        ((MainActivity)getActivity()).showFullInfo(url);
    }

    void getSourceID() {
        final String sourceId =((MainActivity)getActivity()).getSourceId();
        presenter.setSourceID(sourceId);
    }
}
