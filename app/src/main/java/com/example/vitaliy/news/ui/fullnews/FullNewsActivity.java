package com.example.vitaliy.news.ui.fullnews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.vitaliy.news.R;

/**Show all aticle information
 *
 * @author Vitaliy
 * @version 1.1
 */
public class FullNewsActivity extends AppCompatActivity implements FullNewsContract.IFullNewsView {
    private FullNewsContract.IFullNewsPresenter presenter;
    private ProgressBar progressBar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
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
        webView = findViewById(R.id.fullNewsWV);
        webView.getSettings().setJavaScriptEnabled(true);
        progressBar = findViewById(R.id.newsPB);
    }

    /**
     * Load article info to webView by url
     * @param url String
     */
    @Override
    public void showContent(String url) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    public class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(view.GONE);
        }
    }

}
