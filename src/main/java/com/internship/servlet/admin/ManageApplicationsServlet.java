package com.internship.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.ApplicationDAO;
import com.internship.model.Application;

@WebServlet("/admin/applications")
public class ManageApplicationsServlet extends HttpServlet {

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

        ApplicationDAO applicationDAO = new ApplicationDAO();

        List<Application> applications =
                applicationDAO.getAllApplications();

        request.setAttribute("applications", applications);

        request.getRequestDispatcher("/admin/applications.jsp")
               .forward(request, response);
    }
}