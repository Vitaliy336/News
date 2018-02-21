package com.example.vitaliy.news.ui.searchNews;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vitaliy.news.MainActivity;
import com.example.vitaliy.news.R;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.ui.adapters.NewsAdapter;
import com.example.vitaliy.news.ui.view.EndlessRecyclerView;

import java.util.Arrays;
import java.util.List;

public class SearchNewsFragment extends Fragment implements SearchNewsContract.IAllNewsView {

    private View rootView;
    private SearchNewsContract.IAllNewsPresenter presenter;
    private NewsAdapter newsAdapter;
    private RecyclerView news;
    private LinearLayoutManager layoutManagerForNews;
    private EditText searchNews;
    private TextView info;
    private EndlessRecyclerView endlessRecycler;

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
        getOrder();
        updateData();
    }

    private void updateData() {
        presenter.start();
        endlessRecycler.reset();
    }

    private void initPresenter() {
        presenter = new SearchNewsPresenter();
        presenter.attachView(this);
    }

    private void intiListener() {
        newsAdapter.setOnItemClickListener(new NewsAdapter.onNewsClickListener() {
            @Override
            public void OnClick(Article article) {
                presenter.goTofullNews(article.getUrl());
            }
        });
        searchNews.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (!TextUtils.isEmpty(searchNews.getText().toString()) &&
                        !TextUtils.equals(searchNews.getText(), " ") ) {
                    if (i == EditorInfo.IME_ACTION_SEARCH) {
                        presenter.getSearchQuery(searchNews.getText().toString());
                        presenter.prepareNews();

                        if(newsAdapter.getItemCount() == 0);{
                            info.setText(R.string.nothing);
                        }

                        Log.e("after query submit", String.valueOf(newsAdapter.getItemCount()));
                        searchNews.clearFocus();
                        newsAdapter.clear();
                        closeKeyboard(getActivity(), searchNews.getWindowToken());
                        return true;
                    }
                } else {
                    closeKeyboard(getActivity(), searchNews.getWindowToken());
                }
                return false;
            }
        });

        endlessRecycler = new EndlessRecyclerView(layoutManagerForNews) {
            @Override
            public void onLoadMore(int page, int totalitemCount, RecyclerView view) {
                Log.e("PAGESSSS", String.valueOf(page));
                if(((MainActivity) getActivity()).checkNetwork()) {
                    presenter.setPageNumber(page);
                    presenter.prepareNews();
                } else {
                    presenter.setPageNumber(1);
                    presenter.prepareNews();
                }
            }
        };

        news.addOnScrollListener(endlessRecycler);

    }

    private void initView() {
        newsAdapter = new NewsAdapter();
        searchNews = rootView.findViewById(R.id.searchNews);
        searchNews.clearFocus();

        info = rootView.findViewById(R.id.infotv);

        layoutManagerForNews = new LinearLayoutManager(getActivity());
        layoutManagerForNews.setOrientation(LinearLayoutManager.VERTICAL);

        news = rootView.findViewById(R.id.newsA);
        news.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        news.setLayoutManager(layoutManagerForNews);
        news.setAdapter(newsAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.search_news_fragment, container, false);
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
        ((MainActivity) getActivity()).showFullInfo(url);
    }

    @Override
    public void hideMessage() {
        info.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showMessage() {
        info.setVisibility(View.VISIBLE);
    }

    @Override
    public void pagination(List<Article> articles) {
        newsAdapter.addArticles(articles);
    }

    private static void closeKeyboard(Context c, IBinder windowToken) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken, 0);
    }

    private void getOrder(){
        final String order = ((MainActivity)getActivity()).getOrder();
        presenter.setOrder(order);
    }
}
