package main.java.com.hms.dao;

import main.java.com.hms.model.Patient;
import main.java.com.hms.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void create(Patient p) throws SQLException {
        String sql = "INSERT INTO patients(name,gender,dob,phone,address) VALUES(?,?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getGender());
            ps.setDate(3, Date.valueOf(p.getDob()));
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getAddress());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        }
    }

    public Patient findById(int id) throws SQLException {
        String sql = "SELECT * FROM patients WHERE id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    public List<Patient> searchByName(String name) throws SQLException {
        String sql = "SELECT * FROM patients WHERE name LIKE ?";
        List<Patient> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public List<Patient> listAll() throws SQLException {
        String sql = "SELECT * FROM patients";
        List<Patient> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void update(Patient p) throws SQLException {
        String sql = "UPDATE patients SET name=?,gender=?,dob=?,phone=?,address=? WHERE id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getGender());
            ps.setDate(3, Date.valueOf(p.getDob()));
            ps.setString(4, p.getPhone());
            ps.setString(5, p.getAddress());
            ps.setInt(6, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Patient map(ResultSet rs) throws SQLException {
        Patient p = new Patient();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setGender(rs.getString("gender"));
        Date d = rs.getDate("dob");
        if (d != null) p.setDob(d.toLocalDate());
        p.setPhone(rs.getString("phone"));
        p.setAddress(rs.getString("address"));
        return p;
    }
}
