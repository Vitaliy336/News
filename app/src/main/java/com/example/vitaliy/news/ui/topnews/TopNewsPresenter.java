
package com.example.vitaliy.news.ui.topnews;

import android.text.TextUtils;

import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;

import java.util.List;

/**
 * Presenter class for HotNews
 *
 * @author Vitaliy
 * @version 1.0
 */
public class TopNewsPresenter implements TopNewsContract.ITopNewsPresenter {
    private String category = "";
    private String source = "";
    private String country = "";
    private int page = 1;
    private final int startingPage = 1;
    private NewsDataSource dataSource;
    private TopNewsContract.ITopNewsView view;

    public TopNewsPresenter() {
    }

    @Override
    public void attachView(TopNewsContract.ITopNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void start() {
        prepareNews();
    }

    @Override
    public void prepareNews() {
        dataSource.getHotNews(new NewsDataRepository.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                if (page > startingPage){
                    view.pagination((List<Article>) article);
                } else {
                    view.displayNews((List<Article>) article);
                }
            }

            @Override
            public void onFailure() {
                view.displayError("Fail to load data");
            }
        }, category, source, page, country);
    }

    @Override
    public void goToFullNews(String url) {
        view.showFullNews(url);
    }

    /**
     * Set news category filter
     *
     * @param category String
     */
    @Override
    public void setCategoryName(String category) {
        this.category = category;
    }


    /**
     * Check if source paramether is empty.
     * If source not empty -> hide categories menu
     * and show editText with source name
     * Else show categories and hide source name
     *
     * @since 15/Jan/18
     */
    @Override
    public void setSourceID(String source) {
        view.hideSourceFilter();
        this.source = source;
        if (!TextUtils.isEmpty(this.source)) {
            view.hideCategories();
            view.showSourceFilter();
            view.viewSetText(source);
        } else {
            view.hideSourceFilter();
            view.showCategories();
            prepareNews();
        }
    }


    /**
     * Set news category filter
     * @param page Integer
     */
    @Override
    public void setPageNumber(int page) {
        this.page = page;
    }

    /**
     * Set news category filter
     * @param country String
     */
    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Set NewsDataSource
     * @param dataSource NewsDataSource
     */
    @Override
    public void setDataSource(NewsDataSource dataSource) {
        this.dataSource = dataSource;
    }


}