package com.example.vitaliy.news.ui.searchNews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;

import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class SearchNewsFragment extends Fragment implements SearchNewsContract.IAllNewsView {

    private View rootView;
    private SearchNewsContract.IAllNewsPresenter presenter;
    private NewsAdapter newsAdapter;
    private RecyclerView news;
    private LinearLayoutManager lm;
    private SearchView searchNews;

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
        updateData();
    }

    private void updateData() {
        presenter.start();
        presenter.prepareNews();
    }

    private void initPresenter() {
        RemoteNewsDataSource dataSource = new RemoteNewsDataSource();
        presenter = new SearchNewsPresenter(dataSource);
        presenter.attachView(this);
    }

    private void intiListener() {
        newsAdapter.setOnItemClickListener(new NewsAdapter.onNewsClickListener() {
            @Override
            public void OnClick(Article article) {
                presenter.goTofullNews(article.getUrl());
            }
        });
       searchNews.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               presenter.getSearchQuery(query);
               presenter.prepareNews();
               searchNews.clearFocus();
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               return false;
           }
       });
    }

    private void initView() {
        newsAdapter = new NewsAdapter();
        searchNews = rootView.findViewById(R.id.searchA);


        lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        news = rootView.findViewById(R.id.newsA);
        news.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        news.setLayoutManager(lm);
        news.setAdapter(newsAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.all_news_fragment, container, false);
        return rootView;
    }


    @Override
    public void displayNews(List<Article> news) {
        Log.e("Allnews", "show");
        newsAdapter.setData(news, getActivity());
        newsAdapter.notifyDataSetChanged();

    }


    @Override
    public void showFullNews(String url) {
        Intent intent = new Intent(getActivity(), FullNewsActivity.class);
        intent.putExtra("Url", url);
        startActivity(intent);
    }


}
