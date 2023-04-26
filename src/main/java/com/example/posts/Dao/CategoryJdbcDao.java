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
        String query = "INSERT INTO categories name VALUES (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, entity.getName());
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected == 1){
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                    return entity;
                }
            }else{
                throw new RuntimeException("Category was not created!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT * FROM categories";
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
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
        String query = "SELECT * FROM categories WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
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
        String query = "UPDATE categories SET name = ? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getId());
            int row = preparedStatement.executeUpdate();
            if (row == 0){
                throw new RuntimeException("categ was not updated !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category entity) {
        Connection connection = ConnectionManager.getInstance();
        String query = "DELETE FROM categories WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
