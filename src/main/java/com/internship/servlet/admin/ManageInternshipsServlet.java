package com.internship.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.InternshipDAO;
import com.internship.model.Internship;

@WebServlet("/admin/internships")
public class ManageInternshipsServlet extends HttpServlet {

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

        InternshipDAO internshipDAO = new InternshipDAO();

        List<Internship> internships =
                internshipDAO.getAllInternships();

        request.setAttribute("internships", internships);

        request.getRequestDispatcher("/admin/internships.jsp")
               .forward(request, response);
    }
}