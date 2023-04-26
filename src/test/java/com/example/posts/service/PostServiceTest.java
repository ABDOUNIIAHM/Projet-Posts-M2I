package com.example.posts.service;

import com.example.posts.Dao.CategoryDao;
import com.example.posts.Dao.CategoryJdbcDao;
import com.example.posts.Dao.PostDao;
import com.example.posts.model.Category;
import com.example.posts.model.Post;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PostServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void should_create_newPost() {
        // GIVEN
        int idPost = 5;
        String title = "title test";
        String author = "author test";
        String content = "content test";
        LocalDateTime time = LocalDateTime.now();
        String picture = "imgUrl test";
        int idCategory = 3;

        CategoryDao categoryDaoMocked = Mockito.mock(CategoryDao.class);
        Category c = new Category();
        c.setId(idCategory);
        Mockito.when(categoryDaoMocked.findById(idCategory)).thenReturn(c);

        Post postTest = new Post(idPost, title, author, content, picture, time, c);

        PostDao postDaoMocked = Mockito.mock(PostDao.class);
        Mockito.when(postDaoMocked.create(postTest)).thenReturn(postTest);

        PostService postService = new PostService();
        postService.setPostJdbcDao(postDaoMocked);
        postService.setCategoryDao(categoryDaoMocked);

        // WHEN
        Post createdPost = postService.createNewPost(title, author, content, idCategory);

        // THEN
        Assertions.assertEquals(postTest, createdPost);
    }



}