package com.internship.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.StudentDAO;
import com.internship.model.Student;

@WebServlet("/student/login")
public class StudentLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        StudentDAO studentDAO = new StudentDAO();

        Student student = studentDAO.loginStudent(username, password);

        if (student != null) {

            HttpSession session = request.getSession();

            session.setAttribute("student", student);

            response.sendRedirect(
                request.getContextPath() + "/student/dashboard"
            );

        } else {

            response.sendRedirect(
                request.getContextPath() + "/login.jsp?error=invalid"
            );
        }
    }
}