package com.bankingapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bankingapp.model.Transaction;

//public class TransactionDao {
//
//    public void add(Transaction t) throws SQLException {
//        String sql = "INSERT INTO transactions (from_account,to_account,type,amount) VALUES (?,?,?,?)";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, t.getFromAccount());
//            ps.setString(2, t.getToAccount());
//            ps.setString(3, t.getType());
//            ps.setDouble(4, t.getAmount());
//            ps.executeUpdate();
//        }
//    }
//
//    public List<Transaction> findByFilters(String account, java.sql.Date from, java.sql.Date to, String type) throws SQLException {
//        StringBuilder sb = new StringBuilder("SELECT * FROM transactions WHERE 1=1");
//        List<Object> params = new ArrayList<>();
//
//        if (account != null && !account.isEmpty()) {
//            sb.append(" AND (from_account=? OR to_account=?)");
//            params.add(account); params.add(account);
//        }
//        if (from != null) { sb.append(" AND timestamp>=?"); params.add(from); }
//        if (to != null) { sb.append(" AND timestamp<=?"); params.add(to); }
//        if (type != null && !type.isEmpty() && !"ALL".equalsIgnoreCase(type)) {
//            sb.append(" AND type=?"); params.add(type.toUpperCase());
//        }
//        sb.append(" ORDER BY timestamp DESC");
//
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sb.toString())) {
//            for (int i=0; i<params.size(); i++) ps.setObject(i+1, params.get(i));
//            ResultSet rs = ps.executeQuery();
//            List<Transaction> list = new ArrayList<>();
//            while (rs.next()) {
//                Transaction t = new Transaction();
//                t.setId(rs.getInt("id"));
//                t.setFromAccount(rs.getString("from_account"));
//                t.setToAccount(rs.getString("to_account"));
//                t.setType(rs.getString("type"));
//                t.setAmount(rs.getDouble("amount"));
//                t.setTimestamp(rs.getTimestamp("timestamp"));
//                list.add(t);
//            }
//            return list;
//        }
//    }
//}


//public class TransactionDao {
//
//    public void add(Transaction t) throws SQLException {
//        try (Connection conn = DbUtil.getConnection()) {
//            add(t, conn);
//        }
//    }
//
//    // Overloaded method to use same connection from service
//    public void add(Transaction t, Connection conn) throws SQLException {
//        String sql = "INSERT INTO transactions (from_account,to_account,type,amount) VALUES (?,?,?,?)";
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, t.getFromAccount());
//            ps.setString(2, t.getToAccount());
//            ps.setString(3, t.getType());
//            ps.setDouble(4, t.getAmount());
//            ps.executeUpdate();
//        }
//    }
//
//    public List<Transaction> findByFilters(String account, java.sql.Date from, java.sql.Date to, String type) throws SQLException {
//        StringBuilder sb = new StringBuilder("SELECT * FROM transactions WHERE 1=1");
//        List<Object> params = new ArrayList<>();
//
//        if (account != null && !account.isEmpty()) {
//            sb.append(" AND (from_account=? OR to_account=?)");
//            params.add(account); params.add(account);
//        }
//        if (from != null) { sb.append(" AND timestamp>=?"); params.add(from); }
//        if (to != null) { sb.append(" AND timestamp<=?"); params.add(to); }
//        if (type != null && !type.isEmpty() && !"ALL".equalsIgnoreCase(type)) {
//            sb.append(" AND type=?"); params.add(type.toUpperCase());
//        }
//        sb.append(" ORDER BY timestamp DESC");
//
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sb.toString())) {
//            for (int i=0; i<params.size(); i++) ps.setObject(i+1, params.get(i));
//            ResultSet rs = ps.executeQuery();
//            List<Transaction> list = new ArrayList<>();
//            while (rs.next()) {
//                Transaction t = new Transaction();
//                t.setId(rs.getInt("id"));
//                t.setFromAccount(rs.getString("from_account"));
//                t.setToAccount(rs.getString("to_account"));
//                t.setType(rs.getString("type"));
//                t.setAmount(rs.getDouble("amount"));
//                t.setTimestamp(rs.getTimestamp("timestamp"));
//                list.add(t);
//            }
//            return list;
//        }
//    }
//}


//---description added and transfer-----




//public class TransactionDao {
//
//    public void add(Transaction t) throws SQLException {
//        // ✅ Auto-generate description if missing
//        if (t.getDescription() == null || t.getDescription().trim().isEmpty()) {
//            if ("CREDIT".equalsIgnoreCase(t.getType())) {
//                if (t.getFromAccount() != null && t.getFromAccount().equals(t.getToAccount())) {
//                    t.setDescription("Self deposit of " + t.getAmount());
//                } else {
//                    t.setDescription("Credit of " + t.getAmount() + " to " + t.getToAccount());
//                }
//            } else if ("TRANSFER".equalsIgnoreCase(t.getType())) {
//                t.setDescription("Transfer of " + t.getAmount() + " from " +
//                        t.getFromAccount() + " to " + t.getToAccount());
//            } else {
//                t.setDescription("Transaction of " + t.getAmount());
//            }
//        }
//
//        String sql = "INSERT INTO transactions (from_account, to_account, type, amount, description) VALUES (?,?,?,?,?)";
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, t.getFromAccount());
//            ps.setString(2, t.getToAccount());
//            ps.setString(3, t.getType());
//            ps.setDouble(4, t.getAmount());
//            ps.setString(5, t.getDescription());
//            ps.executeUpdate();
//        }
//    }
//
//    // ✅ Modified with isAdmin flag
//    public List<Transaction> findByFilters(String account, java.sql.Date from, java.sql.Date to,
//                                           String type, boolean isAdmin) throws SQLException {
//        StringBuilder sb = new StringBuilder("SELECT * FROM transactions WHERE 1=1");
//        List<Object> params = new ArrayList<>();
//
//        if (account != null && !account.isEmpty()) {
//            if (isAdmin) {
//                sb.append(" AND (from_account=? OR to_account=?)");
//                params.add(account);
//                params.add(account);
//            } else {
//                sb.append(" AND ( (type='CREDIT' AND to_account=?) " +
//                        " OR (type='TRANSFER' AND from_account=?) " +
//                        " OR (type='TRANSFER' AND to_account=?) )");
//                params.add(account); // credit
//                params.add(account); // sent transfer
//                params.add(account); // received transfer
//            }
//        }
//        if (from != null) {
//            sb.append(" AND timestamp>=?");
//            params.add(from);
//        }
//        if (to != null) {
//            sb.append(" AND timestamp<=?");
//            params.add(to);
//        }
//        if (type != null && !type.isEmpty() && !"ALL".equalsIgnoreCase(type)) {
//            sb.append(" AND type=?");
//            params.add(type.toUpperCase());
//        }
//        sb.append(" ORDER BY timestamp DESC");
//
//        try (Connection conn = DbUtil.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sb.toString())) {
//            for (int i = 0; i < params.size(); i++) ps.setObject(i + 1, params.get(i));
//            ResultSet rs = ps.executeQuery();
//            List<Transaction> list = new ArrayList<>();
//            while (rs.next()) {
//                Transaction t = new Transaction();
//                t.setId(rs.getInt("id"));
//                t.setFromAccount(rs.getString("from_account"));
//                t.setToAccount(rs.getString("to_account"));
//                t.setType(rs.getString("type"));
//                t.setAmount(rs.getDouble("amount"));
//                t.setDescription(rs.getString("description"));
//                t.setTimestamp(rs.getTimestamp("timestamp"));
//                list.add(t);
//            }
//            return list;
//        }
//    }
//}



public class TransactionDao {

    public void add(Transaction t) throws SQLException {
        if (t.getDescription() == null || t.getDescription().trim().isEmpty()) {
            if ("CREDIT".equalsIgnoreCase(t.getType())) {
                if (t.getFromAccount() != null && t.getFromAccount().equals(t.getToAccount())) {
                    t.setDescription("Self deposit of " + t.getAmount());
                } else {
                    t.setDescription("Credit of " + t.getAmount() + " to " + t.getToAccount());
                }
            } else if ("TRANSFER".equalsIgnoreCase(t.getType())) {
                t.setDescription("Transfer of " + t.getAmount() + " from " +
                        t.getFromAccount() + " to " + t.getToAccount());
            } else {
                t.setDescription("Transaction of " + t.getAmount());
            }
        }

        String sql = "INSERT INTO transactions (from_account, to_account, type, amount, description) VALUES (?,?,?,?,?)";
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getFromAccount());
            ps.setString(2, t.getToAccount());
            ps.setString(3, t.getType());
            ps.setDouble(4, t.getAmount());
            ps.setString(5, t.getDescription());
            ps.executeUpdate();
        }
    }

    public List<Transaction> findByFilters(String account, java.sql.Date from, java.sql.Date to,
                                           String type, boolean isAdmin) throws SQLException {
        StringBuilder sb = new StringBuilder("SELECT * FROM transactions WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (account != null && !account.isEmpty()) {
            if (isAdmin) {
                sb.append(" AND (from_account=? OR to_account=?)");
                params.add(account);
                params.add(account);
            } else {
                sb.append(" AND ( (type='CREDIT' AND to_account=?) " +
                        " OR (type='TRANSFER' AND from_account=? AND description LIKE 'Sent%') " +
                        " OR (type='TRANSFER' AND to_account=? AND description LIKE 'Received%') )");
                params.add(account);
                params.add(account);
                params.add(account);
            }
        }
        if (from != null) {
            sb.append(" AND timestamp>=?");
            params.add(from);
        }
        if (to != null) {
            sb.append(" AND timestamp<=?");
            params.add(to);
        }
        if (type != null && !type.isEmpty() && !"ALL".equalsIgnoreCase(type)) {
            sb.append(" AND type=?");
            params.add(type.toUpperCase());
        }

        // ✅ Order by ID ASC for natural passbook sequence
        if (isAdmin) {
            sb.append(" ORDER BY timestamp DESC");
        } else {
            sb.append(" ORDER BY id ASC");
        }

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sb.toString())) {
            for (int i = 0; i < params.size(); i++) ps.setObject(i + 1, params.get(i));
            ResultSet rs = ps.executeQuery();
            List<Transaction> list = new ArrayList<>();
            while (rs.next()) {
                Transaction t = new Transaction();
                t.setId(rs.getInt("id"));
                t.setFromAccount(rs.getString("from_account"));
                t.setToAccount(rs.getString("to_account"));
                t.setType(rs.getString("type"));
                t.setAmount(rs.getDouble("amount"));
                t.setDescription(rs.getString("description"));
                t.setTimestamp(rs.getTimestamp("timestamp"));
                list.add(t);
            }
            return list;
        }
    }
}




