package com.example.vitaliy.news.ui.sources;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;

import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public class SourcesFragment extends Fragment implements SourcesContract.ISourcesView{
    private View rootView;
    private SourcesPresenter presenter;
    private RemoteNewsDataSource dataSource;
    private RecyclerView newsS, categoriesS;
    private NewsAdapter sNewsAdapter;
    private CategoriesAdapter sCategoriesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.sources_news_fragment, container, false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
        initListener();
        initPresenter();

    }

    private void initPresenter() {
        dataSource = new RemoteNewsDataSource();
        presenter = new SourcesPresenter(dataSource);
        presenter.attachView(this);
        presenter.prepareCategories();
        presenter.prepareNews();

    }

    private void initListener() {
    }

    private void initView() {
        sNewsAdapter = new NewsAdapter();
        sCategoriesAdapter = new CategoriesAdapter();

        LinearLayoutManager categoryLM = new LinearLayoutManager(getActivity());
        LinearLayoutManager newsLM = new LinearLayoutManager(getActivity());

        categoryLM.setOrientation(LinearLayoutManager.HORIZONTAL);
        newsLM.setOrientation(LinearLayoutManager.VERTICAL);

        newsS = rootView.findViewById(R.id.newsS);
        categoriesS = rootView.findViewById(R.id.categoriesS);

        newsS.setLayoutManager(newsLM);
        newsS.setAdapter(sNewsAdapter);

        categoriesS.setLayoutManager(categoryLM);
        categoriesS.setAdapter(sCategoriesAdapter);
    }

    @Override
    public void displayCategories(List<String> categories) {
        sCategoriesAdapter.setData(categories);
    }

    @Override
    public void displayNews(List<Article> article) {
      // sNewsAdapter.setData(article, getActivity());
    }

    @Override
    public void displayToastMessage() {

    }

    @Override
    public void ShowFullNews(String url) {

    }

    @Override
    public void showNewsWithFilter(List<Article> article) {

    }
}
