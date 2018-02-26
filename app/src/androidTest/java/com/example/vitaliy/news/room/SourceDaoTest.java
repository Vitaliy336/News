package com.example.vitaliy.news.room;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.vitaliy.news.data.model.source.Source;
import com.example.vitaliy.news.data.room.NewsDb;
import com.example.vitaliy.news.data.room.SourcesDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class SourceDaoTest {
    private NewsDb mDb;
    private SourcesDao mDao;
    private Source source;
    private Source secondSource;
    private List<Source> sourceList;

    @Before
    public void setUp(){
        mDb = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), NewsDb.class).build();
        mDao = mDb.getSourcesDao();
        secondSource = new Source();
        source = new Source();
        sourceList = new ArrayList<>();
        source.setId("Fake Source");
        source.setCategory("Fake category");
        secondSource.setId("Fake Source1");
        secondSource.setCategory("Fake category1");
        sourceList.add(source);
        sourceList.add(secondSource);

    }

    @After
    public void closeDb(){
        mDb.close();
    }

    @Test
    public void InsertGoodSourcesTest(){
        mDao.insertAll(sourceList);
        assertEquals(2,  mDao.getAllSources().size());
    }

    @Test (expected = NullPointerException.class)
    public void InsertBadSourcesTest(){
        mDao.insertAll(null);
        mDao.getAllSources();
    }

    @Test
    public void clearSourcesTest(){
        mDao.insertAll(sourceList);
        mDao.nukeTable();
        assertTrue(mDao.getAllSources().isEmpty());
    }

    @Test
    public void getSourceWithBadCategory(){
        mDao.insertAll(sourceList);
        List<Source> s = mDao.getSourtedSources("Fake category1");
        assertFalse(s.get(0).getCategory().equals("Fake category"));
    }

    @Test
    public void getSourceWithGoodCategory(){
        mDao.insertAll(sourceList);
        List<Source> s = mDao.getSourtedSources("Fake category");
        assertEquals("Fake category", s.get(0).getCategory());

    }
}
