package com.example.vitaliy.news.sources;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.modules.BadDataSource;
import com.example.vitaliy.news.modules.GoodDataSource;
import com.example.vitaliy.news.ui.searchNews.SearchNewsPresenter;
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
    private GoodDataSource goodDataSource;
    private BadDataSource badDataSource;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    SourcesPresenter presenter;

    @Mock
    SourcesContract.ISourcesView view;

    @Before
    public void setUp() {
        goodDataSource = new GoodDataSource();
        badDataSource = new BadDataSource();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void SourceBadRequest() {
        presenter.setDataSource(badDataSource.badDatasource);
        presenter.prepareSources();
        Mockito.verify(view, Mockito.never()).showSources((List<Source>) any());
    }

    @Test
    public void SourceGoodDataSource() {
        presenter.setDataSource(goodDataSource.dataSource);
        presenter.prepareSources();
        Mockito.verify(view).showSources((List<Source>) any());
    }

}
