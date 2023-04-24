package com.example.posts.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String title;
    private String author;

    private String content;
    private String pictureUrl;
    private LocalDateTime createdAt;

    public Post(int id) {
        this.id = id;
    }

    public Post(int id, String title, String author, String content, String pictureUrl, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.pictureUrl = pictureUrl;
        this.createdAt = createdAt;
    }


    public Post(String title,
                String author,
                String content,
                String pictureUrl,
                LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.pictureUrl = pictureUrl;
        this.createdAt = createdAt;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Post() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }
}
