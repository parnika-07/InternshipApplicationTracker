package com.internship.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.internship.model.Internship;
import com.internship.util.DBConnection;

public class InternshipDAO {

    public boolean addInternship(Internship internship) {

        String sql = "INSERT INTO internship "
                + "(internship_id, title, description, required_skills, location, "
                + "stipend, duration, deadline, status) "
                + "VALUES (internship_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, internship.getTitle());
            ps.setString(2, internship.getDescription());
            ps.setString(3, internship.getRequiredSkills());
            ps.setString(4, internship.getLocation());
            ps.setDouble(5, internship.getStipend());
            ps.setString(6, internship.getDuration());
            ps.setDate(7, internship.getDeadline());
            ps.setString(8, internship.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Internship> getAllInternships() {

        List<Internship> internships = new ArrayList<>();

        String sql = "SELECT * FROM internship ORDER BY internship_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Internship internship = new Internship();

                internship.setInternshipId(rs.getInt("internship_id"));
                internship.setTitle(rs.getString("title"));
                internship.setDescription(rs.getString("description"));
                internship.setRequiredSkills(rs.getString("required_skills"));
                internship.setLocation(rs.getString("location"));
                internship.setStipend(rs.getDouble("stipend"));
                internship.setDuration(rs.getString("duration"));
                internship.setDeadline(rs.getDate("deadline"));
                internship.setStatus(rs.getString("status"));

                internships.add(internship);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return internships;
    }

    public Internship getInternshipById(int internshipId) {

        String sql = "SELECT * FROM internship WHERE internship_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, internshipId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Internship internship = new Internship();

                internship.setInternshipId(rs.getInt("internship_id"));
                internship.setTitle(rs.getString("title"));
                internship.setDescription(rs.getString("description"));
                internship.setRequiredSkills(rs.getString("required_skills"));
                internship.setLocation(rs.getString("location"));
                internship.setStipend(rs.getDouble("stipend"));
                internship.setDuration(rs.getString("duration"));
                internship.setDeadline(rs.getDate("deadline"));
                internship.setStatus(rs.getString("status"));

                return internship;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateInternship(Internship internship) {

        String sql = "UPDATE internship SET title = ?, description = ?, "
                + "required_skills = ?, location = ?, stipend = ?, duration = ?, "
                + "deadline = ?, status = ? WHERE internship_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, internship.getTitle());
            ps.setString(2, internship.getDescription());
            ps.setString(3, internship.getRequiredSkills());
            ps.setString(4, internship.getLocation());
            ps.setDouble(5, internship.getStipend());
            ps.setString(6, internship.getDuration());
            ps.setDate(7, internship.getDeadline());
            ps.setString(8, internship.getStatus());
            ps.setInt(9, internship.getInternshipId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteInternship(int internshipId) {

        String sql = "DELETE FROM internship WHERE internship_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, internshipId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean closeInternship(int internshipId) {

        String sql = "UPDATE internship SET status = 'CLOSED' "
                + "WHERE internship_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, internshipId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}