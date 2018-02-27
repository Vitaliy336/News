package com.example.vitaliy.news.searchNews;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.modules.BadDataSource;
import com.example.vitaliy.news.modules.GoodDataSource;
import com.example.vitaliy.news.ui.searchNews.SearchNewsContract;
import com.example.vitaliy.news.ui.searchNews.SearchNewsPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.AtLeast;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;

/**
 * Created by v_shevchyk on 22.02.18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchNewsPresenterUnitTest {
    private GoodDataSource goodDataSource;
    private BadDataSource badDataSource;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    SearchNewsPresenter presenter;

    @Mock
    SearchNewsContract.IAllNewsView view;


    @Before
    public void setUp() {
        presenter.attachView(view);
        goodDataSource = new GoodDataSource();
        badDataSource = new BadDataSource();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void goodRequestTest(){
        presenter.setDataSource(goodDataSource.dataSource);
        presenter.prepareNews();
        Mockito.verify(view).displayNews((List<Article>) any());
    }

    @Test
    public void BadRequestTest(){
        presenter.setDataSource(badDataSource.badDatasource);
        presenter.prepareNews();
        Mockito.verify(view, Mockito.never()).displayNews((List<Article>)any());
    }

    @Test
    public void watchFullNewsInfoTest(){

    }
}
