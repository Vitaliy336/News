package com.example.vitaliy.news.ui.Model.newsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by v_shevchyk on 15.01.18.
 */

public class NewsModel {
    @SerializedName("source")
    @Expose
    private NewsSource source;
    @SerializedName("author")
    @Expose
    private Object author;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private Object urlToImage;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;

    public NewsModel(String news1, String s) {
        title = news1;
        description = s;
    }


    public NewsSource getSource() {
        return source;
    }

    public void setSource(NewsSource source) {
        this.source = source;
    }

    public Object getAuthor() {
        return author;
    }

    public void setAuthor(Object author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(Object urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
