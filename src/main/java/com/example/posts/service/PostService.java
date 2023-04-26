package com.example.posts.service;

import com.example.posts.Dao.*;
import com.example.posts.model.Category;
import com.example.posts.model.Post;
import com.github.javafaker.Faker;

import javax.management.Query;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

// CRUD
public class PostService {
    private PostDao postJdbcDao = new PostJdbcDao();
    private static Faker faker = new Faker(new Locale("fr"));
    private CategoryDao categoryDao = new CategoryJdbcDao();
    Connection connection = ConnectionManager.getInstance();

    private static long idSequence = 0;

    private Post mapToPost(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String author = resultSet.getString("author");
        String content = resultSet.getString("content");
        String pictureUrl = resultSet.getString("pictureUrl");
        Timestamp createdAtTimestamp = resultSet.getTimestamp("createdAt");
        int idCategory = resultSet.getInt("idCategory");
        LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
        Category cat = categoryDao.findById(idCategory);
        return new Post(id,title,author,content,pictureUrl,createdAt,cat);
    }

    public void setPostJdbcDao(PostDao postJdbcDao) {
        this.postJdbcDao = postJdbcDao;
    }

    public List<Post> fetchAllPosts() {
        return postJdbcDao.findAll();
    }
    public Post createNewPost(String title, String author, String content, int idCategory) {
        Category cat = categoryDao.findById(idCategory);
        LocalDateTime time = LocalDateTime.now();
        Post p = new Post(title, author, content, "https://picsum.photos/200/300?random=" + ++idSequence,time,cat);
        Post createdPost = postJdbcDao.create(p);
        return createdPost;
    }
    public List<Post> getByCategoryId(int id){
        List<Post> posts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM posts WHERE idCategory = ?");
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                Post post = mapToPost(resultSet);
                posts.add(post);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return posts;
    }

    public List<Post> findByDesignation(String mc){
        List<Post> foundPosts = new ArrayList<>();
        String query = "SELECT * FROM posts WHERE title LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setString(1,"%"+mc+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post post = mapToPost(rs);
                foundPosts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foundPosts;
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

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
}
