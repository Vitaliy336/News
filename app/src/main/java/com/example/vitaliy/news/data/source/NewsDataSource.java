package com.example.vitaliy.news.data.source;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface NewsDataSource {

    interface getListCallback {
        void onListReceived(List<?> article);

        void onFailure();
    }

    void getHotNews(getListCallback callback, String category, String source, int page, String country);
    void getEverything(getListCallback callback, String query, int page, String order);

    void getSources(getListCallback callback, String category);

}
