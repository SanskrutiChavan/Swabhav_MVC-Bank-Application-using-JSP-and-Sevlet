package com.bankingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bankingapp.model.Customer;

public class CustomerDao {

    public int create(Customer c) throws SQLException {
        String sql = "INSERT INTO customers (username,email,password,full_name,phone,address,balance,active) " +
                     "VALUES (?,?,?,?,?,?,?,TRUE)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getUsername());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPasswordHash());
            ps.setString(4, c.getFullName());
            ps.setString(5, c.getPhone());
            ps.setString(6, c.getAddress());
            ps.setDouble(7, c.getBalance());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return -1;
    }

    public void setAccountNumber(int id, String account) throws SQLException {
        String sql = "UPDATE customers SET account_number=? WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, account);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void update(Customer c) throws SQLException {
        String sql = "UPDATE customers SET full_name=?, password=?, phone=?, address=?, balance=?, active=? WHERE id=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getFullName());
            ps.setString(2, c.getPasswordHash());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getAddress());
            ps.setDouble(5, c.getBalance());
            ps.setBoolean(6, c.isActive());
            ps.setInt(7, c.getId());
            ps.executeUpdate();
        }
    }

    public void softDelete(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE customers SET active=FALSE WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Customer findById(int id) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
            return null;
        }
    }

    public Customer findByUsername(String username) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE username=?")) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
            return null;
        }
    }

    public Customer findByAccount(String acc) throws SQLException {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM customers WHERE account_number=?")) {
            ps.setString(1, acc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
            return null;
        }
    }

    public Customer findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email=?";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return map(rs);
            return null;
        }
    }

    public List<Customer> listAll(boolean includeInactive) throws SQLException {
        String sql = "SELECT * FROM customers " + (includeInactive ? "" : "WHERE active=TRUE") + " ORDER BY id";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<Customer> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    // ✅ Centralized row-mapping
    private Customer map(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setAccountNumber(rs.getString("account_number"));
        c.setUsername(rs.getString("username"));
        c.setEmail(rs.getString("email"));
        c.setPasswordHash(rs.getString("password"));  // ✅ use password column
        c.setFullName(rs.getString("full_name"));
        c.setPhone(rs.getString("phone"));
        c.setAddress(rs.getString("address"));
        c.setBalance(rs.getDouble("balance"));
        c.setActive(rs.getBoolean("active"));
        return c;
    }

}

