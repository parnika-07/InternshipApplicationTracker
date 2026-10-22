package com.internship.servlet.student;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.model.Student;
import com.internship.util.DBConnection;

@WebServlet("/student/delete-account")
public class DeleteAccountServlet extends HttpServlet {

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

        String sql = "DELETE FROM student WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, student.getStudentId());
            ps.executeUpdate();

            session.invalidate();

            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );

        } catch (Exception e) {
            e.printStackTrace();

            response.sendRedirect(
                request.getContextPath() + "/student/profile"
            );
        }
    }
}