package com.internship.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.AdminDAO;

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AdminDAO adminDAO = new AdminDAO();

        boolean valid = adminDAO.loginAdmin(username, password);

        if (valid) {

            HttpSession session = request.getSession();

            session.setAttribute("admin", username);

            response.sendRedirect(
                request.getContextPath() + "/admin/dashboard"
            );

        } else {

            response.sendRedirect(
                request.getContextPath() + "/admin-login.jsp?error=invalid"
            );
        }
    }
}