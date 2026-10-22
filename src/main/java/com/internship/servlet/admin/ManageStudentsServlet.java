package com.internship.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.AdminDAO;
import com.internship.model.Student;

@WebServlet("/admin/students")
public class ManageStudentsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(
                request.getContextPath() + "/admin-login.jsp"
            );
            return;
        }

        AdminDAO adminDAO = new AdminDAO();

        List<Student> students = adminDAO.getAllStudents();

        request.setAttribute("students", students);

        request.getRequestDispatcher("/admin/students.jsp")
               .forward(request, response);
    }
}