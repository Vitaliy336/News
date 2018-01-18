package com.example.vitaliy.news.ui.fullnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.example.vitaliy.news.R;

public class FullNewsActivity extends AppCompatActivity implements FullNewsContract.IFullNewsView {
    private FullNewsContract.IFullNewsPresenter presenter;
    private WebView fullNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
        Log.e("onStop", "View detached");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initView();
        intiListener();
        initPresenter();
    }

    private void initPresenter() {
        presenter = new FullNewsPresenter();
        presenter.attachView(this);
        Intent intent = getIntent();
        presenter.getUrl(intent.getStringExtra("Url"));
    }

    private void intiListener() {
    }

    private void initView() {
        fullNews = (WebView)findViewById(R.id.fullNewsWV);
    }

    @Override
    public void showContent(String url) {
        fullNews.loadUrl(url);
    }
}
