package com.internship.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.dao.ApplicationDAO;
import com.internship.model.Student;

@WebServlet("/student/withdraw-application")
public class WithdrawApplicationServlet extends HttpServlet {

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

        int applicationId =
                Integer.parseInt(request.getParameter("applicationId"));

        ApplicationDAO applicationDAO = new ApplicationDAO();

        applicationDAO.withdrawApplication(
                applicationId,
                student.getStudentId()
        );

        response.sendRedirect(
                request.getContextPath() + "/student/my-applications"
        );
    }
}