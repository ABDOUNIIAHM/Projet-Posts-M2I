package com.example.posts.servlet;

import com.example.posts.model.Category;
import com.example.posts.service.CategoryService;
import com.example.posts.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add-post")
public class AddPostServlet extends HttpServlet {
    PostService postService = new PostService();
    CategoryService categoryService = new CategoryService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Affiche la vue
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories",categories);
        request
                .getRequestDispatcher("/WEB-INF/add-post-form.jsp")
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        // ajout parametre input choix category
        int idCategory = Integer.parseInt(req.getParameter("idCategory"));
        postService.createNewPost(title, author, content,idCategory);
        resp.sendRedirect("posts");
    }
}
