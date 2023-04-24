package com.example.posts.service;

import com.example.posts.Dao.PostDao;
import com.example.posts.Dao.PostJdbcDao;
import com.example.posts.model.Post;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.*;

// CRUD
public class PostService {
    private static PostDao postJdbcDao = new PostJdbcDao();
    private static Faker faker = new Faker(new Locale("fr"));

    private static long idSequence = 0;

    public List<Post> fetchAllPosts() {
        return postJdbcDao.findAll();
    }
    public Post createNewPost(String title, String author, String content) {

        LocalDateTime time = LocalDateTime.now();
        Post p = new Post( title, author, content, "https://picsum.photos/200/300?random=" + ++idSequence,time);
        postJdbcDao.create(p);
        return p;
    }

    public static Faker getFaker() {
        return faker;
    }

    public static long getIdSequence() {
        return idSequence;
    }

    public Post getPostById(int id) {
        return postJdbcDao.findById(id);
    }

    public void createPost(Post post) {
        postJdbcDao.create(post);
    }

    public void deletePost(Post post) {
        postJdbcDao.delete(post);
    }

    public void update(Post post) {
        postJdbcDao.update(post);
    }
}
