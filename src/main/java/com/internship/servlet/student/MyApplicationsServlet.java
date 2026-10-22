package com.internship.servlet.student;

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
import com.internship.model.Student;

@WebServlet("/student/my-applications")
public class MyApplicationsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("student") == null) {
            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );
            return;
        }

        Student student = (Student) session.getAttribute("student");

        ApplicationDAO applicationDAO = new ApplicationDAO();

        List<Application> applications =
                applicationDAO.getApplicationsByStudent(
                    student.getStudentId()
                );

        request.setAttribute("applications", applications);

        request.getRequestDispatcher(
            "/student/my-applications.jsp"
        ).forward(request, response);
    }
}