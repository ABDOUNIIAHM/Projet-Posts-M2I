package com.example.posts.Dao;

import com.example.posts.model.Category;
import com.example.posts.model.Post;
import com.example.posts.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class CategoryJdbcDao implements CategoryDao {

    private Category mapToCategory(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        return new Category(id,name);
    }

    @Override
    public Category create(Category entity) {
        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)");

            preparedStatement.setString(1, entity.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new RuntimeException("Category was not created!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()){
                Category category = mapToCategory(resultSet);
                categories.add(category);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findById(Integer integer) {
        Connection connection = ConnectionManager.getInstance();
        Category cat = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM categories WHERE id=?");
            ps.setInt(1,integer);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                cat = mapToCategory(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cat;
    }

    @Override
    public void update(Category entity) {

        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categories SET name = ? WHERE id=?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());
            int row = preparedStatement.executeUpdate();
            if (row == 0){
                throw new RuntimeException("categ was not updated !!");
            }else{
                System.out.println("categ updated and had a new name: " +entity.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category entity) {
        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE id=?");
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
