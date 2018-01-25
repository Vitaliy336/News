package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.data.sourceModel.Source;
import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

import java.util.List;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public interface SourcesContract {
    interface ISourcesView extends BaseView{
        void displayCategories(List<String> categories);
        void showSources(List<Source> list);
    }

    interface ISourcesPresenter extends BasePresenter<ISourcesView>{
        void start();
        void prepareCategories();
        void prepareSources();
        void getSourceCategory(String category);
    }
}
