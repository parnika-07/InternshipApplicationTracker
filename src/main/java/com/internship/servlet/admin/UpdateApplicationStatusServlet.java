package com.internship.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.ApplicationDAO;

@WebServlet("/admin/update-application-status")
public class UpdateApplicationStatusServlet extends HttpServlet {

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

        int applicationId =
                Integer.parseInt(request.getParameter("applicationId"));

        String status = request.getParameter("status");

        ApplicationDAO applicationDAO = new ApplicationDAO();

        applicationDAO.updateApplicationStatus(
                applicationId,
                status
        );

        response.sendRedirect(
            request.getContextPath() + "/admin/applications"
        );
    }
}