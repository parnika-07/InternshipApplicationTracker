package com.internship.servlet.student;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.ApplicationDAO;
import com.internship.dao.InternshipDAO;
import com.internship.model.Application;
import com.internship.model.Internship;
import com.internship.model.Student;

@WebServlet("/student/apply-internship")
public class ApplyInternshipServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Student student = (Student) session.getAttribute("student");

        int internshipId =
                Integer.parseInt(request.getParameter("internshipId"));

        InternshipDAO internshipDAO = new InternshipDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();

        Internship internship =
                internshipDAO.getInternshipById(internshipId);

        if (internship == null) {
            response.sendRedirect(
                request.getContextPath() + "/student/browse-internships"
            );
            return;
        }

        if ("CLOSED".equalsIgnoreCase(internship.getStatus())) {
            response.sendRedirect(
                request.getContextPath() + "/student/browse-internships"
            );
            return;
        }

        if (internship.getDeadline() != null) {

            Date today = new Date(System.currentTimeMillis());

            if (today.after(internship.getDeadline())) {
                response.sendRedirect(
                    request.getContextPath() + "/student/browse-internships"
                );
                return;
            }
        }

        if (applicationDAO.hasAlreadyApplied(
                student.getStudentId(), internshipId)) {

            response.sendRedirect(
                request.getContextPath() + "/student/browse-internships"
            );
            return;
        }

        Application application = new Application();

        application.setStudentId(student.getStudentId());
        application.setInternshipId(internshipId);

        applicationDAO.applyForInternship(application);

        response.sendRedirect(
            request.getContextPath() + "/student/my-applications"
        );
    }
}