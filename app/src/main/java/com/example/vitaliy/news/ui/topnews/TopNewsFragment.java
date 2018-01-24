
package com.example.vitaliy.news.ui.topnews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

import android.widget.Toast;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;
import com.example.vitaliy.news.ui.adapters.CategoriesAdapter;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;
import com.example.vitaliy.news.ui.fullnews.FullNewsActivity;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vitaliy on 1/11/2018.
 */

public class TopNewsFragment extends Fragment implements TopNewsContract.ITopNewsView {

    private TopNewsContract.ITopNewsPresenter presenter;
    private View rootView;
    private RecyclerView categoriesRV, newsRV;
    private CategoriesAdapter categoriesAdapter;
    private NewsAdapter newsAdapter;
    private SearchView sourceSV;
    private Editor editor;
    private SharedPreferences sharedPreferences;
    private final String SAVED_TEXT = "sourceID";

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
        loadSourceText();
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
        presenter.prepareNews();
        presenter.prepareCategories();
    }

    private void initView() {
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

        sourceSV = rootView.findViewById(R.id.sourceID);
        sourceSV.setFocusable(false);

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
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
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
    public void ShowFullNews(String url) {
        Intent intent = new Intent(getActivity(), FullNewsActivity.class);
        intent.putExtra("Url", url);
        startActivity(intent);
    }

    void loadSourceText() {
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        String savedText = sharedPreferences.getString(SAVED_TEXT, "");
        Log.e("TOP", savedText);
        if (savedText != "") {
            categoriesRV.setAlpha(0);
            sourceSV.setQuery(savedText, false);
            presenter.setSourceID(savedText);
            presenter.prepareNews();
            saveSourceText();
        }
    }

    void saveSourceText() {
        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVED_TEXT, "");

    }
}
