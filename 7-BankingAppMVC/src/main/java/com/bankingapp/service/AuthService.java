package com.bankingapp.service;

import com.bankingapp.dao.AdminDao;
import com.bankingapp.dao.CustomerDao;
import com.bankingapp.model.Admin;
import com.bankingapp.model.Customer;

public class AuthService {
    private final AdminDao adminDao = new AdminDao();
    private final CustomerDao customerDao = new CustomerDao();

    public Admin loginAdmin(String username, String passwordPlain) {
        try {
            Admin a = adminDao.findByUsername(username);
            if (a == null) return null;
            String hash = PasswordUtil.sha256(passwordPlain);
            if (hash.equals(a.getPasswordHash())) return a;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer loginCustomer(String username, String passwordPlain) {
        try {
            Customer c = customerDao.findByUsername(username);
            if (c == null || !c.isActive()) return null;
            String hash = PasswordUtil.sha256(passwordPlain);
            if (hash.equals(c.getPasswordHash())) return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
