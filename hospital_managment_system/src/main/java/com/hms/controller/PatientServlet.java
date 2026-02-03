package com.hms.controller;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
    private final PatientDAO dao = new PatientDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("new".equals(action)) {
                req.getRequestDispatcher("/patients/form.jsp").forward(req, resp);
                return;
            }
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Patient p = dao.findById(id);
                req.setAttribute("patient", p);
                req.getRequestDispatcher("/patients/form.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("patients", dao.listAll());
            req.getRequestDispatcher("/patients/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String dob = req.getParameter("dob");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if (name == null || name.trim().isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/patients?error=missing");
            return;
        }
        try {
            Patient p = new Patient();
            if (idStr != null && !idStr.isEmpty()) p.setId(Integer.parseInt(idStr));
            p.setName(name);
            p.setGender(gender);
            if (dob != null && !dob.isEmpty()) p.setDob(LocalDate.parse(dob));
            p.setPhone(phone);
            p.setAddress(address);
            if (p.getId() > 0) dao.update(p); else dao.create(p);
            resp.sendRedirect(req.getContextPath() + "/patients");
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
