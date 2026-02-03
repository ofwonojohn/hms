package main.java.com.hms.controller;

import main.java.com.hms.dao.DoctorDAO;
import main.java.com.hms.model.Doctor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {
    private final DoctorDAO dao = new DoctorDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("doctors", dao.listAll());
            req.getRequestDispatcher("/doctors/list.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String spec = req.getParameter("specialization");
        String dept = req.getParameter("departmentId");
        if (name == null || name.isEmpty()) { resp.sendRedirect(req.getContextPath() + "/doctors?error=missing"); return; }
        try {
            Doctor d = new Doctor();
            d.setName(name);
            d.setSpecialization(spec);
            d.setDepartmentId(dept != null && !dept.isEmpty() ? Integer.parseInt(dept) : 0);
            dao.create(d);
            resp.sendRedirect(req.getContextPath() + "/doctors");
        } catch (SQLException e) { throw new ServletException(e); }
    }
}
