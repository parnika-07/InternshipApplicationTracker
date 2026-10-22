package com.internship.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.InternshipDAO;

@WebServlet("/admin/close-internship")
public class CloseInternshipServlet extends HttpServlet {

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

        int internshipId =
                Integer.parseInt(request.getParameter("internshipId"));

        InternshipDAO internshipDAO = new InternshipDAO();

        internshipDAO.closeInternship(internshipId);

        response.sendRedirect(
            request.getContextPath() + "/admin/internships"
        );
    }
}