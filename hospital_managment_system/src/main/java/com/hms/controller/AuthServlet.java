package main.java.com.hms.controller;

import main.java.com.hms.dao.UserDAO;
import main.java.com.hms.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username == null || password == null) {
            resp.sendRedirect(req.getContextPath() + "/auth?error=missing");
            return;
        }
        try {
            User u = userDAO.findByUsername(username);
            if (u != null && u.getPasswordHash().equals(password)) { // NOTE: replace with hashing
                HttpSession s = req.getSession(true);
                s.setAttribute("user", u);
                resp.sendRedirect(req.getContextPath() + "/dashboard.jsp");
                return;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/auth?error=invalid");
    }
}
