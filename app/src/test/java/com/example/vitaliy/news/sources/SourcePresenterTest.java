package com.example.vitaliy.news.sources;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.ui.sources.SourcesContract;
import com.example.vitaliy.news.ui.sources.SourcesPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;


@RunWith(JUnit4.class)
public class SourcePresenterTest {
    List<Source> sources;
    List<Article> articles;

    public NewsDataSource goodDataSource = new NewsDataSource() {
        @Override
        public void getHotNews(getListCallback callback, String category, String source, int page, String country) {

        }

        @Override
        public void getEverything(getListCallback callback, String query, int page, String order) {

        }

        @Override
        public void getSources(getListCallback callback, String category) {
            callback.onListReceived(sources);
        }
    };

    public NewsDataSource badDataSource = new NewsDataSource() {
        @Override
        public void getHotNews(getListCallback callback, String category, String source, int page, String country) {
        }

        @Override
        public void getEverything(getListCallback callback, String query, int page, String order) {

        }

        @Override
        public void getSources(getListCallback callback, String category) {
            callback.onFailure();
        }
    };



    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    SourcesPresenter presenter;

    @Mock
    SourcesContract.ISourcesView view;

    @Before
    public void setUp() {
        articles = new ArrayList<>();
        articles.add(new Article());
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void SourceBadRequest() {
        presenter.setDataSource(badDataSource);
        presenter.prepareSources();
        Mockito.verify(view, Mockito.never()).showSources((List<Source>) any());
    }

    @Test
    public void SourceGoodDataSource() {
        presenter.setDataSource(goodDataSource);
        presenter.prepareSources();
        Mockito.verify(view).showSources((List<Source>) any());
    }

}
