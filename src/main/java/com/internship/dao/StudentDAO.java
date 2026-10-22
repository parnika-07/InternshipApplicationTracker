package com.internship.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internship.model.Student;
import com.internship.util.DBConnection;

public class StudentDAO {

    public boolean registerStudent(Student student) {

        String sql = "INSERT INTO student "
                + "(student_id, name, username, password, email, phone, address, "
                + "gender, qualification, skills, resume) "
                + "VALUES (student_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getUsername());
            ps.setString(3, student.getPassword());
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getGender());
            ps.setString(8, student.getQualification());
            ps.setString(9, student.getSkills());
            ps.setString(10, student.getResume());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Student loginStudent(String username, String password) {

        String sql = "SELECT * FROM student "
                + "WHERE username = ? AND password = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Student student = new Student();

                student.setStudentId(rs.getInt("student_id"));
                student.setName(rs.getString("name"));
                student.setUsername(rs.getString("username"));
                student.setPassword(rs.getString("password"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setAddress(rs.getString("address"));
                student.setGender(rs.getString("gender"));
                student.setQualification(rs.getString("qualification"));
                student.setSkills(rs.getString("skills"));
                student.setResume(rs.getString("resume"));

                return student;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}