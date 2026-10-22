package com.internship.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.internship.model.Student;
import com.internship.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/student/update-profile")
public class UpdateProfileServlet extends HttpServlet {

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

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String qualification = request.getParameter("qualification");
        String skills = request.getParameter("skills");

        String sql = "UPDATE student SET name = ?, email = ?, phone = ?, "
                   + "address = ?, gender = ?, qualification = ?, skills = ? "
                   + "WHERE student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, gender);
            ps.setString(6, qualification);
            ps.setString(7, skills);
            ps.setInt(8, student.getStudentId());

            ps.executeUpdate();

            student.setName(name);
            student.setEmail(email);
            student.setPhone(phone);
            student.setAddress(address);
            student.setGender(gender);
            student.setQualification(qualification);
            student.setSkills(skills);

            session.setAttribute("student", student);

            response.sendRedirect(
                request.getContextPath() + "/student/profile"
            );

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(
                request.getContextPath() + "/student/profile"
            );
        }
    }
}