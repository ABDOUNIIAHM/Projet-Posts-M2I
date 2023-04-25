package com.example.posts.Dao;

import com.example.posts.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements UserDao{

    private User mapToUser(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new User(id,email,password);
    }
    @Override
    public User create(User entity) {
        Connection connection = ConnectionManager.getInstance();
        try{
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO users(email, password) VALUES(?,?)");
            prepareStatement.setString(1, entity.getEmail());
            prepareStatement.setString(2, entity.getPassword());

            int rowsAffected = prepareStatement.executeUpdate();
            if(rowsAffected == 0){
                throw new RuntimeException("User has not been created !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, email, password FROM users");
            while (resultSet.next()){
                User user = mapToUser(resultSet);
                userList.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }


    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    public User findByUseremail(String userEmailFind) {
        User userFound = null;
        Connection connection = ConnectionManager.getInstance();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT id, email, password FROM users WHERE email=?");
            statement.setString(1, userEmailFind);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userFound = mapToUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }
}
