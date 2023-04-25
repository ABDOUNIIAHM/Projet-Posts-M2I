package com.example.posts.servlet;
import com.example.posts.model.Category;
import com.example.posts.service.CategoryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/categories")
public class CategoryServlet extends HttpServlet {
    private static CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories",categories);
        req.getRequestDispatcher("WEB-INF/list-categories.jsp").forward(req,resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int id = Integer.parseInt(req.getParameter("delete"));
        Category cat = categoryService.findById(id);
        categoryService.delete(cat);
        resp.sendRedirect(getServletContext().getContextPath()+"/categories");

    }
}
