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

@WebServlet("/admin/add-internship")
public class AddInternshipServlet extends HttpServlet {

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

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String requiredSkills = request.getParameter("requiredSkills");
        String location = request.getParameter("location");
        double stipend = Double.parseDouble(request.getParameter("stipend"));
        String duration = request.getParameter("duration");
        Date deadline = Date.valueOf(request.getParameter("deadline"));

        Internship internship = new Internship();

        internship.setTitle(title);
        internship.setDescription(description);
        internship.setRequiredSkills(requiredSkills);
        internship.setLocation(location);
        internship.setStipend(stipend);
        internship.setDuration(duration);
        internship.setDeadline(deadline);
        internship.setStatus("OPEN");

        InternshipDAO internshipDAO = new InternshipDAO();

        internshipDAO.addInternship(internship);

        response.sendRedirect(
            request.getContextPath() + "/admin/internships"
        );
    }
}