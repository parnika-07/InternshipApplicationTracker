package com.internship.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.AdminDAO;

@WebServlet("/admin/delete-student")
public class DeleteStudentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("admin") == null) {
            response.sendRedirect(
                request.getContextPath() + "/admin-login.jsp"
            );
            return;
        }

        int studentId =
                Integer.parseInt(request.getParameter("studentId"));

        AdminDAO adminDAO = new AdminDAO();

        adminDAO.deleteStudent(studentId);

        response.sendRedirect(
            request.getContextPath() + "/admin/students"
        );
    }
}