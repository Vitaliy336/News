package com.example.vitaliy.news;


import com.example.vitaliy.news.ui.topnews.TopNewsContract;
import com.example.vitaliy.news.ui.topnews.TopNewsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;


/**
 * Created by v_shevchyk on 21.02.18.
 */

public class TopNewsPresenterUnitTest {
    TopNewsPresenter presenter;

    @Mock
    TopNewsContract.ITopNewsView view;

    @Before
    public void setUp(){
        presenter = new TopNewsPresenter();
    }

    @Test
    public void categoryName(){
        String category = "sampleCategory";
        presenter.setCategoryName(category);
    }

    @Test
    public void mult() {
        assertEquals(8, 2 * 4);
    }
}
