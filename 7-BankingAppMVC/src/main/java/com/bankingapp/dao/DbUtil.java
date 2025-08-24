package com.bankingapp.dao;

import java.sql.*;

public class DbUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/bankingapp?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private static final String USER = "root";   // change to your DB user
    private static final String PASS = "root";   // change to your DB password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
