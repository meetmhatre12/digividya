
package com.digividya.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import com.digividya.util.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = DBConnection.getConnection()) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && PasswordUtil.verifyPassword(password, rs.getString("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", rs.getString("name"));
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Server error");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
