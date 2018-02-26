package com.example.vitaliy.news.room;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.vitaliy.news.data.model.news.Article;
import com.example.vitaliy.news.data.room.NewsDao;
import com.example.vitaliy.news.data.room.NewsDb;

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
public class NewsDaoTest {
    Article article;
    Article secondArticle;
    List<Article> articleList;
    private NewsDao mDao;
    private NewsDb mDb;

    @Before
    public void initDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, NewsDb.class).build();
        mDao = mDb.getDataDao();

        article = new Article();
        secondArticle = new Article();
        articleList = new ArrayList<>();
        article.setUrl("www");
        article.setCategory("Fake category");
        article.setSourceId("Fake source");
        article.setTitle("Fake title");
        secondArticle.setUrl("url");

        articleList.add(article);
        articleList.add(secondArticle);
    }


    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void IsertandSelectArticcleDataTest() {
        mDao.insertAll(articleList);
        assertEquals(2, mDao.getAllArticles().size());
    }

    @Test(expected = NullPointerException.class)
    public void InsertBadArticleDataTest() {
        mDao.insertAll(null);
        mDao.getAllArticles();
    }

    @Test
    public void SelectArticleByGoodTitle() {
        mDao.insertAll(articleList);
        List<Article> articles = mDao.searchNews("Fake title");
        assertEquals("Fake title", articles.get(0).getTitle());
    }

    @Test
    public void SelectArticleByBadTitle() {
        mDao.insertAll(articleList);
        List<Article> articles = mDao.searchNews("Fake title");
        assertFalse(articles.get(0).getTitle().equals("Fake"));
    }


    @Test
    public void SelectArticleByGoodCategory() {
        mDao.insertAll(articleList);
        List<Article> articles = mDao.getNewsWithCategory("Fake category");
        assertEquals("Fake category", articles.get(0).getCategory());
    }

    @Test
    public void SelectArticleByBadCategory() {
        mDao.insertAll(articleList);
        List<Article> articles = mDao.getNewsWithCategory("Fake category");
        assertFalse(articles.get(0).getTitle().equals("Fake"));
    }


    @Test
    public void SelectArticleByGoodSource() {
        mDao.insertAll(articleList);
        List<Article> articles = mDao.getNewsWithSource("Fake source");
        assertEquals("Fake source", articles.get(0).getSourceId());
    }

    @Test
    public void SelectArticleByBadSource() {
        mDao.insertAll(articleList);
        List<Article> articles = mDao.getNewsWithSource("Fake source");
        assertFalse(articles.get(0).getTitle().equals("Fake"));
    }

    @Test
    public void clearAllArticles() {
        mDao.insertAll(articleList);
        mDao.nukeTable();
        assertTrue(mDao.getAllArticles().isEmpty());
    }


    @Test
    public void checkDeleteOneArticles() {
        mDao.insertAll(articleList);
        mDao.deleteArticle(article);
        assertEquals(1, mDao.getAllArticles().size());
    }

    @Test
    public void checkArticlesCount() {
        mDao.insertAll(articleList);
        assertEquals(2, mDao.count());
    }


}
