package com.example.posts.service;

import com.example.posts.Dao.CategoryDao;
import com.example.posts.Dao.CategoryJdbcDao;
import com.example.posts.Dao.ConnectionManager;
import com.example.posts.model.Category;

import java.sql.Connection;
import java.util.List;

public class CategoryService {
    private static CategoryDao categoryDao = new CategoryJdbcDao();
    Connection connection = ConnectionManager.getInstance();
    public List<Category> findAll(){
        return categoryDao.findAll();
    }
    public void create(Category cat){
        categoryDao.create(cat);
    }
    public Category findById(int id){
        return categoryDao.findById(id);
    }
    public void update(Category cat){
        categoryDao.update(cat);
    }
    public void delete(Category category){
        categoryDao.delete(category);
    }
}
