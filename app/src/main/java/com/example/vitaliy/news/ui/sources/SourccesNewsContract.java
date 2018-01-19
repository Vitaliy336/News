package com.example.vitaliy.news.ui.sources;

import com.example.vitaliy.news.ui.base.BasePresenter;
import com.example.vitaliy.news.ui.base.BaseView;

/**
 * Created by v_shevchyk on 19.01.18.
 */

public interface SourccesNewsContract {
    interface ISourcesNewsView extends BaseView{
        void showCategories();
        void showNews();
        void showFullNews();
    }

    interface ISourcesNewsPresenter extends BasePresenter<ISourcesNewsView>{
        void prepareCategoriee();
        void prepareNews();
        void goToFullNews();
    }
}
