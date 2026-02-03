package main.java.com.hms.dao;

import main.java.com.hms.model.Appointment;
import main.java.com.hms.util.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public void create(Appointment a) throws SQLException {
        String sql = "INSERT INTO appointments(patient_id,doctor_id,appointment_time,status) VALUES(?,?,?,?)";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, a.getPatientId());
            ps.setInt(2, a.getDoctorId());
            ps.setTimestamp(3, Timestamp.valueOf(a.getAppointmentTime()));
            ps.setString(4, a.getStatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) a.setId(rs.getInt(1)); }
        }
    }

    public List<Appointment> listByDoctor(int doctorId) throws SQLException {
        String sql = "SELECT * FROM appointments WHERE doctor_id=?";
        List<Appointment> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, doctorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    public void updateStatus(int id, String status) throws SQLException {
        String sql = "UPDATE appointments SET status=? WHERE id=?";
        try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private Appointment map(ResultSet rs) throws SQLException {
        Appointment a = new Appointment();
        a.setId(rs.getInt("id"));
        a.setPatientId(rs.getInt("patient_id"));
        a.setDoctorId(rs.getInt("doctor_id"));
        Timestamp t = rs.getTimestamp("appointment_time");
        if (t != null) a.setAppointmentTime(t.toLocalDateTime());
        a.setStatus(rs.getString("status"));
        return a;
    }
}
