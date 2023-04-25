package com.example.posts.Dao;

import com.example.posts.model.Category;
import com.example.posts.model.Post;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class PostJdbcDao implements PostDao{
    CategoryDao categoryDao = new CategoryJdbcDao();

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

    @Override
    public Post create(Post entity) {
        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO posts(title,author,content,pictureUrl,createdAt,idCategory) VALUES (?,?,?,?,?,?)");

            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setString(3, entity.getContent());
            preparedStatement.setString(4, entity.getPictureUrl());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setInt(6,entity.getCategory().getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new RuntimeException("Post has not been created !");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return entity;
    }
    @Override
    public  List<Post> findAll() {
        List<Post> postList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id,title,author,content,pictureUrl,createdAt,idCategory FROM posts");
            while (resultSet.next()){
                Post post = mapToPost(resultSet);
                postList.add(post);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return postList;
    }

    @Override
    public Post findById(Integer integer) {
        Connection connection = ConnectionManager.getInstance();
        Post post = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id,title,author,content,pictureUrl,createdAt,idCategory FROM posts WHERE id=?");
            ps.setInt(1,integer);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                post = mapToPost(resultSet);
            }else{
                throw new RuntimeException("post with id: "+integer+" was not found !");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void update(Post entity) {
        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE posts SET title=?, author=?, content=?, pictureUrl=?, createdAt=?,idCategory=? WHERE id=?");
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAuthor());
            preparedStatement.setString(3, entity.getContent());
            preparedStatement.setString(4, entity.getPictureUrl());
            preparedStatement.setDate(5, new java.sql.Date(entity.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli()));
            preparedStatement.setInt(6, entity.getCategory().getId());
            preparedStatement.setInt(7, entity.getId());
            int row = preparedStatement.executeUpdate();
            if(row == 0){
                throw new RuntimeException("could not update post");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Post entity) {
        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM posts WHERE id=?");
            preparedStatement.setInt(1, entity.getId());
            int row = preparedStatement.executeUpdate();
            if(row == 0){
                throw new RuntimeException("Post was not deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
