
package com.digividya.controller;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import com.digividya.util.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = DBConnection.getConnection()) {

            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = PasswordUtil.hashPassword(request.getParameter("password"));

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, "student");
            ps.executeUpdate();

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            request.setAttribute("error", "Registration failed");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
