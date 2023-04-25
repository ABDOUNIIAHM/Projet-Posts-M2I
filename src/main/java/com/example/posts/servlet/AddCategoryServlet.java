package com.example.posts.servlet;

import com.example.posts.model.Category;
import com.example.posts.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/add-category")
public class AddCategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/add-category.jsp").forward(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("categoryName");
        categoryService.create(new Category(name));
        resp.sendRedirect(getServletContext().getContextPath()+"/categories");
    }

}
