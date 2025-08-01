package com.lmao.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MvcDemo", value = "/MvcDemo")
public class MvcDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // add
        String[] stud = {"studient11", "studient12", "99784407"};
        request.setAttribute("stud_list", stud);

        // get res dispatcher
        RequestDispatcher rq = request.getRequestDispatcher("/view/view_studient.jsp");

        // foward request
        rq.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}