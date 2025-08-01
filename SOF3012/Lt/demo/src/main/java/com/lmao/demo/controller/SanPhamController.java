package com.lmao.demo.controller;

import com.lmao.demo.repo.CategoryRepo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CategoryServlet", value = {
        "/product"
})
public class SanPhamController extends HttpServlet {

    // GETTTTTTTTTTTTTTTT +============================================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        switch (req.getServletPath()) {
            case "/example-route":
                listProduct(req, res);
                break;
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }


    private void funcPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String getParam = req.getParameter("param");

        // Create object
        // Object object = new Object(null, tenSanPham, nhaSanXuat, gia, soLuong, conHang);

        // Add object
        //repository.func(object);

        // Redirect to home
        // res.sendRedirect("/path-to-webapp-to.jsp");
    }


    // POSTTTTTTTTTTTTT +============================================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}