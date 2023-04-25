package com.example.posts.servlet;

import com.example.posts.model.Category;
import com.example.posts.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/edit-category")
public class EditCatServlet extends HttpServlet {
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("edit"));
        Category cat = categoryService.findById(id);
        req.setAttribute("cat",cat);

        req.getRequestDispatcher("WEB-INF/edit-category.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int id = Integer.parseInt(req.getParameter("id"));
        categoryService.update(new Category(id,name));
        resp.sendRedirect(getServletContext().getContextPath()+"/categories");
    }
}
