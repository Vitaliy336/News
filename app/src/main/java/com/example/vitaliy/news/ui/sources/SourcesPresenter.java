package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.data.NewsDataRepository;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.ui.sources.SourcesContract.ISourcesPresenter;

import java.util.List;


public class SourcesPresenter implements ISourcesPresenter {

    private String category = "";
    private NewsDataSource dataSource;
    private SourcesContract.ISourcesView view;

    public SourcesPresenter() {

    }

    @Override
    public void attachView(SourcesContract.ISourcesView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void start() {
        prepareSources();
    }

    @Override
    public void setDataSource(NewsDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void prepareSources() {
        dataSource.getSources(new NewsDataRepository.getListCallback() {
            @Override
            public void onListReceived(List<?> article) {
                view.showSources((List<Source>) article);
            }

            @Override
            public void onFailure() {
                view.showErrorMessage("Fail to load data");
            }
        }, category);
    }


    @Override
    public void getSourceCategory(String category) {
        this.category = category;
    }
}
