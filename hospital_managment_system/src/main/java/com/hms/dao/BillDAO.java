package main.java.com.hms.dao;

import main.java.com.hms.model.Bill;
import main.java.com.hms.util.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    public void create(Bill b) throws SQLException {
        String sql = "INSERT INTO bills(patient_id,amount,paid,date) VALUES(?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, b.getPatientId());
            ps.setDouble(2, b.getAmount());
            ps.setBoolean(3, b.isPaid());
            ps.setDate(4, Date.valueOf(b.getDate()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) b.setId(rs.getInt(1)); }
        }
    }

    public List<Bill> listByPatient(int patientId) throws SQLException {
        String sql = "SELECT * FROM bills WHERE patient_id = ?";
        List<Bill> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    private Bill map(ResultSet rs) throws SQLException {
        Bill b = new Bill();
        b.setId(rs.getInt("id"));
        b.setPatientId(rs.getInt("patient_id"));
        b.setAmount(rs.getDouble("amount"));
        b.setPaid(rs.getBoolean("paid"));
        Date d = rs.getDate("date");
        if (d != null) b.setDate(d.toLocalDate());
        return b;
    }
}
