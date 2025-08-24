package com.bankingapp.dao;

import java.sql.*;
import com.bankingapp.model.Admin;

public class AdminDao {
    public Admin findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM admins WHERE username=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPasswordHash(rs.getString("password"));
                a.setFullName(rs.getString("full_name"));
                return a;
            }
            return null;
        }
    }
}
