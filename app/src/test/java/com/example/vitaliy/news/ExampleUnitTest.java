package com.example.vitaliy.news;

import com.example.vitaliy.news.modules.GoodDataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void add_notCorrect(){
        assertNotEquals(7, 3+1);
    }

    @Test (expected = NullPointerException.class)
    public void nullCheck(){
        String s = null;
        assertTrue(s.isEmpty());
    }



}