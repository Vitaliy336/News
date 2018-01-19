package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public interface SourcesContract {
    interface ISourcesView extends BaseView{
        void displayCategories(List<String> categories);
        void displayNews(List<Article> article);
        void displayToastMessage();
        void ShowFullNews(String url);
        void showNewsWithFilter(List<Article> article);
    }

    interface ISourcesPresenter extends BasePresenter<ISourcesView>{
        void onTextClick();
        void prepareCategories();
        void prepareNews();
        void prepareNewsWithFilter(String category);
        void goToFullNews(String url);
    }
}
