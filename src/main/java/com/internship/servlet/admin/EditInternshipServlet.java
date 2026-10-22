package com.internship.servlet.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.InternshipDAO;
import com.internship.model.Internship;

@WebServlet("/admin/edit-internship")
public class EditInternshipServlet extends HttpServlet {

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

        int internshipId =
                Integer.parseInt(request.getParameter("internshipId"));

        InternshipDAO internshipDAO = new InternshipDAO();

        Internship internship =
                internshipDAO.getInternshipById(internshipId);

        request.setAttribute("internship", internship);

        request.getRequestDispatcher("/admin/edit-internship.jsp")
               .forward(request, response);
    }

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

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String requiredSkills = request.getParameter("requiredSkills");
        String location = request.getParameter("location");
        double stipend = Double.parseDouble(request.getParameter("stipend"));
        String duration = request.getParameter("duration");
        Date deadline = Date.valueOf(request.getParameter("deadline"));
        String status = request.getParameter("status");

        Internship internship = new Internship();

        internship.setInternshipId(internshipId);
        internship.setTitle(title);
        internship.setDescription(description);
        internship.setRequiredSkills(requiredSkills);
        internship.setLocation(location);
        internship.setStipend(stipend);
        internship.setDuration(duration);
        internship.setDeadline(deadline);
        internship.setStatus(status);

        InternshipDAO internshipDAO = new InternshipDAO();

        internshipDAO.updateInternship(internship);

        response.sendRedirect(
            request.getContextPath() + "/admin/internships"
        );
    }
}