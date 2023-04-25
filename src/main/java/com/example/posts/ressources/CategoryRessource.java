package com.example.posts.ressources;

import com.example.posts.dto.CategoryDto;
import com.example.posts.dto.PostDto;
import com.example.posts.model.Category;
import com.example.posts.model.Post;
import com.example.posts.service.CategoryService;
import com.example.posts.service.PostService;
import com.github.javafaker.Cat;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/categories")
public class CategoryRessource {
    CategoryService categoryService = new CategoryService();
    PostService postService = new PostService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllCategories(){
        List<Category> categories = categoryService.findAll();
        List<CategoryDto> dtos;

        dtos = categories
                .stream()
                .map(category -> category.toDto())
                .collect(Collectors.toList());

        return Response
                .ok(dtos)
                .build();
    }
    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAllCategoriesProducts(@PathParam("id") int id){

        List<Post> posts = postService.getByCategoryId(id);
        List<PostDto> dtos;
        dtos = posts
                .stream()
                .map(p -> p.toDto())
                .collect(Collectors.toList());
        return Response
                .ok(dtos)
                .build();
    }
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response createCategory(Category c){
        categoryService.create(c);
        if(categoryService.findById(c.getId()) == null){
            Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
        return Response
                .status(Response.Status.CREATED)
                .entity("OK")
                .build();
    }
    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deleteCategory(@PathParam("id") int id){
        Category c = categoryService.findById(id);
        categoryService.delete(c);
        if(categoryService.findById(id) != null){
            Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
        return Response.status(Response.Status.OK).build();
    }
    @PUT
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response updateCategory(@PathParam("id") int id, Category updatedCategory){
        categoryService.update(updatedCategory);
        Category c = categoryService.findById(id);
        if(c.equals(updatedCategory) == false){
            return Response.status(Response.Status.EXPECTATION_FAILED).build();
        }
        return Response.status(Response.Status.OK).entity(updatedCategory).build();
    }
}
