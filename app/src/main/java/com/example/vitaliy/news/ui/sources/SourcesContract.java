package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

public interface SourcesContract {
    interface ISourcesView extends BaseView {
        void showSources(List<Source> list);

        void showErrorMessage(String message);

        void doNothing();

    }

    interface ISourcesPresenter extends BasePresenter<ISourcesView> {
        void start();

        void setDataSource(NewsDataSource dataSource);

        void prepareSources();

        void getSourceCategory(String category);

    }
}
