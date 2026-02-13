
package com.digividya.util;
import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = System.getenv("DB_URL");
        String user = System.getenv("DB_USER");
        String pass = System.getenv("DB_PASS");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
