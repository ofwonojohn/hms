package main.java.com.hms.dao;

import main.java.com.hms.model.Doctor;
import main.java.com.hms.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public void create(Doctor d) throws SQLException {
        String sql = "INSERT INTO doctors(name,specialization,department_id) VALUES(?,?,?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getName());
            ps.setString(2, d.getSpecialization());
            ps.setInt(3, d.getDepartmentId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) d.setId(rs.getInt(1)); }
        }
    }

    public Doctor findById(int id) throws SQLException {
        String sql = "SELECT * FROM doctors WHERE id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { if (rs.next()) return map(rs); }
        }
        return null;
    }

    public List<Doctor> listAll() throws SQLException {
        String sql = "SELECT * FROM doctors";
        List<Doctor> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    private Doctor map(ResultSet rs) throws SQLException {
        Doctor d = new Doctor();
        d.setId(rs.getInt("id"));
        d.setName(rs.getString("name"));
        d.setSpecialization(rs.getString("specialization"));
        d.setDepartmentId(rs.getInt("department_id"));
        return d;
    }
}
