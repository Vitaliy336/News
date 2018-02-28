package com.example.vitaliy.news.data;

import com.example.vitaliy.news.data.local.LocalNewsDataSource;
import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

import java.util.List;

/**
 * Class which deside where get data Rest or DataBase
 * @author Vitaliy
 * @version 1.1.4
 */
public class NewsDataRepository implements NewsDataSource {
    private LocalNewsDataSource localNewsDataSource;
    private RemoteNewsDataSource remoteNewsDataSource;

    /**
     * use to intit local and remote -datasource
     */
    public NewsDataRepository() {
        this.localNewsDataSource = new LocalNewsDataSource();
        this.remoteNewsDataSource = new RemoteNewsDataSource();
    }

    /**Get hot news from api, if failed - from database
     *
     * @param callback
     * @param category
     * @param source
     * @param page
     * @param country
     */
    @Override
    public void getHotNews(final getListCallback callback, final String category, final String source, int page,
                           final String country) {
        remoteNewsDataSource.getHotNews(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveNewsToCache((List<Article>) article, category, source);
            }

            @Override
            public void onFailure() {
                int thispage = 1;
                localNewsDataSource.getHotNews(callback, category, source, thispage, country);
            }
        }, category, source, page, country);
    }

    /**Get news by user query from api, if failed - from database
     *
     * @param callback
     * @param query
     * @param page
     * @param order
     */
    @Override
    public void getEverything(final getListCallback callback, final String query, final int page, final String order) {
        remoteNewsDataSource.getEverything(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveNewsToCache((List<Article>) article, null, null);
            }

            @Override
            public void onFailure() {
                localNewsDataSource.getEverything(callback, query, page, order);
            }
        }, query, page, order);
    }

    /**Get sources from api, if failed - from database
     *
     * @param callback
     * @param category
     */
    @Override
    public void getSources(final getListCallback callback, final String category) {
        remoteNewsDataSource.getSources(new getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                callback.onListReceived(article);
                localNewsDataSource.saveSourcesToCache((List<Source>) article);
            }

            @Override
            public void onFailure() {
                localNewsDataSource.getSources(callback, category);
            }
        }, category);
    }
}
