package com.internship.servlet.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.internship.dao.StudentDAO;
import com.internship.model.Student;

@WebServlet("/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String gender = request.getParameter("gender");
        String qualification = request.getParameter("qualification");
        String skills = request.getParameter("skills");
        String resume = request.getParameter("resume");

        Student student = new Student();

        student.setName(name);
        student.setUsername(username);
        student.setPassword(password);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAddress(address);
        student.setGender(gender);
        student.setQualification(qualification);
        student.setSkills(skills);
        student.setResume(resume);

        StudentDAO studentDAO = new StudentDAO();

        boolean registered = studentDAO.registerStudent(student);

        if (registered) {
            response.sendRedirect(
                request.getContextPath() + "/login.jsp"
            );
        } else {
            response.sendRedirect(
                request.getContextPath() + "/register.jsp"
            );
        }
    }
}