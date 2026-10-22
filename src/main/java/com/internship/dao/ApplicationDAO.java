package com.internship.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.internship.model.Application;
import com.internship.util.DBConnection;

public class ApplicationDAO {

    public boolean applyForInternship(Application application) {

        String sql = "INSERT INTO application "
                + "(application_id, student_id, internship_id, applied_date, status) "
                + "VALUES (application_seq.NEXTVAL, ?, ?, SYSDATE, 'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, application.getStudentId());
            ps.setInt(2, application.getInternshipId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean hasAlreadyApplied(int studentId, int internshipId) {

        String sql = "SELECT COUNT(*) FROM application "
                + "WHERE student_id = ? AND internship_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, internshipId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Application> getApplicationsByStudent(int studentId) {

        List<Application> applications = new ArrayList<>();

        String sql = "SELECT * FROM application "
                + "WHERE student_id = ? ORDER BY application_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, studentId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Application application = new Application();

                application.setApplicationId(rs.getInt("application_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setInternshipId(rs.getInt("internship_id"));
                application.setAppliedDate(rs.getDate("applied_date"));
                application.setStatus(rs.getString("status"));

                applications.add(application);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applications;
    }

    public List<Application> getAllApplications() {

        List<Application> applications = new ArrayList<>();

        String sql = "SELECT * FROM application ORDER BY application_id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Application application = new Application();

                application.setApplicationId(rs.getInt("application_id"));
                application.setStudentId(rs.getInt("student_id"));
                application.setInternshipId(rs.getInt("internship_id"));
                application.setAppliedDate(rs.getDate("applied_date"));
                application.setStatus(rs.getString("status"));

                applications.add(application);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return applications;
    }

    public boolean updateApplicationStatus(int applicationId, String status) {

        String sql = "UPDATE application SET status = ? "
                + "WHERE application_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean withdrawApplication(int applicationId, int studentId) {

        String sql = "DELETE FROM application "
                + "WHERE application_id = ? AND student_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, applicationId);
            ps.setInt(2, studentId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}