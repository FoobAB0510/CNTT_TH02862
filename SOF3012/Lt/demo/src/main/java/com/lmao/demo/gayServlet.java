package com.lmao.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PhpMyServlet", value = "/gayServlet")
public class gayServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("The gay person is:" + request.getParameter("tcctl") + request.getParameter("ln"));
        for(int i = request.getParameterValues("Fargay").length; i <  request.getParameterValues("Fargay").length; i++) {
            out.println("<p>" + request.getParameterValues("Fargay")[i] + "</p>");
        }

        out.println("</html></body>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}