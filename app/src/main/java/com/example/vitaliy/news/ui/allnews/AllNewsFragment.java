package com.example.vitaliy.news.ui.allnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class AllNewsFragment extends Fragment implements AllNewsContract.IAllNewsView {

    private View rootView;
    private AllNewsContract.IAllNewsPresenter presenter;
    private NewsAdapter newsAdapter;
    private CategoriesAdapter categoriesAdapter = new CategoriesAdapter();
    private RecyclerView categories, news;
    private LinearLayoutManager layoutManager, lm;

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
//        categoriesAdapter.setCategoryItemClick(new CategoriesAdapter.onCategoryItemClick() {
//            @Override
//            public void onCatClick(String str) {
//                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
//            }
//        }); not shure that here need categories
    }

    private void initView() {
        newsAdapter = new NewsAdapter();

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        news = rootView.findViewById(R.id.AllnewsRV);
        news.setLayoutManager(lm);
        news.setAdapter(newsAdapter);
        categories = rootView.findViewById(R.id.categories);
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
    public void displayNews(List<Article> news) {
        newsAdapter.setData(news, getActivity());
    }

    @Override
    public void ShowToastMessage() {
        Toast.makeText(getActivity(), "Did i understand it?", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFullNews(String url) {
        Intent intent = new Intent(getActivity(), FullNewsActivity.class);
        intent.putExtra("Url", url);
        startActivity(intent);
    }


}
