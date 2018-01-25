package com.example.vitaliy.news.data.source;

import java.util.List;

/**
 * Created by v_shevchyk on 16.01.18.
 */

public interface NewsDataSource {

    interface getListCallback {
        void onListReceived(List<?> article);

        void onFailure();
    }

    void getHotNews(getListCallback callback, String category, String source);

    void getEverything(getListCallback callback, String query);

    void getSources(getListCallback callback, String category);

}
