package com.example.vitaliy.news;

import com.example.vitaliy.news.data.model.news.Article;
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

import static org.junit.Assert.*;
import static org.mockito.Matchers.isNull;

/**
 * Created by v_shevchyk on 22.02.18.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchNewsPresenterUnitTest {
    private List<Article> articles;
    private List<Object> objects;
    private String fakeUrl = "http//....";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    SearchNewsContract.IAllNewsPresenter presenter;

    @Mock
    SearchNewsContract.IAllNewsView view;



    @Before
    public void setUp() {
        articles = new ArrayList<>();
        objects = new ArrayList<>();
        presenter = new SearchNewsPresenter();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void SearchNewsDisplayTest(){
        view.displayNews(articles);
        Mockito.verify(view, new AtLeast(1)).displayNews(articles);
    }

    @Test
    public void testFullNewsUrl(){
        presenter.goTofullNews(fakeUrl);
        ArgumentCaptor<String> argum = ArgumentCaptor.forClass(String.class);
        Mockito.verify(presenter).goTofullNews(argum.capture());
        assertEquals(fakeUrl, argum.getValue());
    }

    @Test
    public void testSeachNewsQuery(){
        presenter.getSearchQuery("query");
        ArgumentCaptor<String> argum = ArgumentCaptor.forClass(String.class);
        Mockito.verify(presenter).getSearchQuery(argum.capture());
        assertEquals("query", argum.getValue());
    }

    @Test (expected = NullPointerException.class)
    public void testSearchQueryNull(){
        Object o = null;
        System.out.println(o.toString());
        presenter.getSearchQuery(o.toString());
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(presenter).getSearchQuery(captor.capture());
        assertNotNull(null, captor.getValue());
    }


}
