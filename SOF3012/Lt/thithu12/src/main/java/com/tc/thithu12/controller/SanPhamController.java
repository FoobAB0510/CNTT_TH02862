package com.tc.thithu12.controller;

import com.tc.thithu12.repo.SanPhamRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "SanPhamController", value = {
        "/"
})
public class SanPhamController extends HttpServlet {
    SanPhamRepository repository = new SanPhamRepository();


    // GETTTTTTTTTTTTTTTT +============================================
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("example-route")) {
            // funcGet(req, res);
        }
    }

    private void funcGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Integer id = Integer.valueOf(req.getParameter("id"));
        // req.setAttribute("sanPham", repository.getOne(id));
        // req.getRequestDispatcher("/path-to-webapp-to.jsp").forward(req, res);
    }


    // POSTTTTTTTTTTTTT +============================================
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.contains("example-route")) {
            // funcPost(req, res);
        }
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

}