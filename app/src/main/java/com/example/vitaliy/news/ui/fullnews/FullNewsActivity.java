package com.example.vitaliy.news.ui.fullnews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.vitaliy.news.R;
import com.example.vitaliy.news.ui.topnews.TopNewsFragment;

public class FullNewsActivity extends AppCompatActivity implements FullNewsContract.IFullNewsView {
    private FullNewsContract.IFullNewsPresenter presenter;
    private ProgressBar newsPB;
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
        fullNews = findViewById(R.id.fullNewsWV);
        fullNews.getSettings().setJavaScriptEnabled(true);
        newsPB = findViewById(R.id.newsPB);
    }

    @Override
    public void showContent(String url) {
        fullNews.setWebViewClient(new WebViewClient());
        fullNews.loadUrl(url);
    }

    public class WebViewClient extends android.webkit.WebViewClient{

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            fullNews.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            newsPB.setVisibility(view.GONE);
        }
    }

}
