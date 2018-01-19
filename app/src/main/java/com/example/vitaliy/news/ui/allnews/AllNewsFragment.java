package com.example.vitaliy.news.ui.allnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;

import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class AllNewsFragment extends Fragment implements AllNewsContract.IAllNewsView {

    private View rootView;
    private AllNewsContract.IAllNewsPresenter presenter;
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
    }

    private void initPresenter() {
        RemoteNewsDataSource dataSource = new RemoteNewsDataSource();
        presenter = new AllNewsPresenter(dataSource);
        presenter.attachView(this);
        presenter.prepareCategories();
        presenter.prepareNews();
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
               Toast.makeText(getActivity(), query, Toast.LENGTH_SHORT).show();
               presenter.searchNews(query);
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
    public void displayCategories(List<String> categories) {
    }

    @Override
    public void displayNews(List<Article> news) {
        newsAdapter.setData(news, getActivity());
    }

    @Override
    public void displayNewsbySearch(List<Article> news) {
        newsAdapter.setData(news, getActivity());
    }

    @Override
    public void showToastMessage() {
        Toast.makeText(getActivity(), "Did i understand it?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFullNews(String url) {
        Intent intent = new Intent(getActivity(), FullNewsActivity.class);
        intent.putExtra("Url", url);
        startActivity(intent);
    }


}
