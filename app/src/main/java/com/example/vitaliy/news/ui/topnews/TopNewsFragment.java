
package com.example.vitaliy.news.ui.topnews;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.ui.view.EndlessRecyclerView;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;

import java.util.Arrays;
import java.util.List;


public class TopNewsFragment extends Fragment implements TopNewsContract.ITopNewsView {
    private TopNewsContract.ITopNewsPresenter presenter;
    private RelativeLayout relativeLayout;
    private View rootView;
    private RecyclerView categories, newsRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private LinearLayoutManager layoutManagerForCategories;
    private LinearLayoutManager layoutManagerForNews;
    private NewsAdapter newsAdapter;
    private EndlessRecyclerView endlessRecycler;
    private EditText sourceEt;
    private final int startingPage = 1;
    private ImageView clear;

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
        initPresenter();
        getCountry();
        getSourceID();
        updateData();
    }


    private void updateData() {
        presenter.start();
        endlessRecycler.reset();
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

        newsRecyclerView = rootView.findViewById(R.id.newsT);
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        newsRecyclerView.setLayoutManager(layoutManagerForNews);
        newsRecyclerView.setAdapter(newsAdapter);

        categories = rootView.findViewById(R.id.categoriesT);
        categories.setLayoutManager(layoutManagerForCategories);
        categories.setAdapter(categoriesAdapter);
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
                newsAdapter.clear();
                presenter.setCategoryName(str);
                presenter.setPageNumber(startingPage);
                presenter.prepareNews();
            }
        });

        endlessRecycler = new EndlessRecyclerView(layoutManagerForNews) {
            @Override
            public void onLoadMore(int page, int totalitemCount, RecyclerView view) {
                if(((MainActivity) getActivity()).checkNetwork()) {
                    presenter.setPageNumber(page);
                    presenter.prepareNews();
                } else {
                    presenter.setPageNumber(startingPage);
                    presenter.prepareNews();
                }
            }
        };

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.setSourceID(null);
            }
        });

        newsRecyclerView.addOnScrollListener(endlessRecycler);
    }


    @Override
    public void displayNews(List<Article> news) {
        newsAdapter.setData(news, getActivity());
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideCategories() {
        categories.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showCategories() {
        categories.setVisibility(View.VISIBLE);
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
    public void pagination(List<Article> articles) {
        newsAdapter.addArticles(articles);
    }

    @Override
    public void showFullNews(String url) {
        ((MainActivity) getActivity()).showFullInfo(url);
    }

    void getSourceID() {
        final String sourceId = ((MainActivity) getActivity()).getSourceId();
        presenter.setSourceID(sourceId);
    }

    void getCountry(){
        final String country = ((MainActivity)getActivity()).getCountry();
        presenter.setCountry(country);
    }
}
