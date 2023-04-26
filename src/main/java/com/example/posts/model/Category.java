package com.example.posts.model;

import com.example.posts.dto.CategoryDto;

import java.util.List;


public class Category {
    private int id;
    private String name;
    private List<Post> posts;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(String name) {
        this.name = name;
    }
    public Category() {
    }
    public CategoryDto toDto(){
        CategoryDto dto = new CategoryDto();
        dto.setId(this.id);
        dto.setName(this.name);
        return dto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            System.out.println("this = null");
            return true;
        }
        if (o == null || this.getClass() != o.getClass()){
            System.out.println("o = null or not same class");
            return false;
        }
        Category category = (Category) o;
        return id == category.id && name.equals(category.name);
    }
}
