package com.lmao.demo.controller;

import com.lmao.demo.enity.Student;
import com.lmao.demo.service.StudentService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "StudentServlet", value = {
        "/students",
        "/students/new",
        "/students/insert",
        "/students/delete",
        "/students/edit",
        "/students/update"
})
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();
        System.out.println("path:"+path);

        switch (path) {
            case "/students":
                listStudents(request, response);
                break;
            case "/students/new":
                showNewForm(request, response);
                break;
            case "/students/insert":
                insertStudent(request, response);
                break;
            case "/students/delete":
                deleteStudent(request, response);
                break;
            case "/students/edit":
                editStudent(request, response);
                break;
            case "/students/update":
                updateStudent(request, response);
                break;
        }
    }

    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // read data from form
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Student student = new Student(id, name, email, phone);

        // save data to DB
        studentService.addStudent(student);

        // redirect to list students
        response.sendRedirect("/students");
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/view/addStudentForm.jsp")
                .forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        studentService.deleteStudent(id);

        response.sendRedirect("/students");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // read student from form
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Student student = new Student(id, name, email, phone);
        // update to DB
        studentService.updateStudent(student);

        // redirect to list students
        response.sendRedirect("/students");
    }


    private void listStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // data student from db

        List<Student> student = new ArrayList<>();
        student = studentService.getStudent();

        // data => std.jsp
        request.setAttribute("student", student);
        request.getRequestDispatcher("/view/std.jsp").forward(request, response);
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Student student = studentService.getStudentById(id);

        // fw to jsp
        request.setAttribute("student", student);
        request.getRequestDispatcher("/view/editStudentForm.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}