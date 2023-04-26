package com.example.posts.service;

import com.example.posts.Dao.CategoryDao;
import com.example.posts.dto.CategoryDto;
import com.example.posts.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void categoryTest(){
        //GIVEN

        //WHEN

        //THEN
        int value = 100;
        Assertions.assertEquals(100,value);
    }
    @Test
    void should_return_all_categories(){
        //GIVEN
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category(1,"categ1"));
        expectedCategories.add(new Category(2,"categ2"));

        CategoryDao categoryDaoMocked = Mockito.mock(CategoryDao.class);
        Mockito
                .when(categoryDaoMocked.findAll())
                .thenReturn(expectedCategories);

        //WHEN
        CategoryService categoryService = new CategoryService();
        categoryService.setCategoryDao(categoryDaoMocked);
        List<Category> categoryResult = categoryService.findAll();

        //THEN
        Assertions.assertEquals(expectedCategories,categoryResult);
    }
}