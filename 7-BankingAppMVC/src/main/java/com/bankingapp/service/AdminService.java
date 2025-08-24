package com.bankingapp.service;

import com.bankingapp.dao.CustomerDao;
import com.bankingapp.model.Customer;
import java.util.List;

public class AdminService {
    private final CustomerDao customerDao = new CustomerDao();

    public List<Customer> getAllCustomers(boolean includeInactive) {
        try {
            return customerDao.listAll(includeInactive);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getCustomerById(int id) {
        try {
            return customerDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(int id) {
        try {
            customerDao.softDelete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int createCustomer(Customer c) {
        try {
            ValidationUtil.validateNotBlank(c.getUsername(), "Username");
            ValidationUtil.validateNotBlank(c.getEmail(), "Email");
            ValidationUtil.validateEmail(c.getEmail());
            ValidationUtil.validateNotBlank(c.getFullName(), "Full name");
            ValidationUtil.validatePhone(c.getPhone());

            c.setPasswordHash(PasswordUtil.sha256(c.getPasswordHash()));
            int id = customerDao.create(c);
            String account = generateAccountNumber(id);
            customerDao.setAccountNumber(id, account);
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(Customer c) {
        try {
            ValidationUtil.validateNotBlank(c.getFullName(), "Full name");
            ValidationUtil.validatePhone(c.getPhone());

            if (c.getPasswordHash() != null && !c.getPasswordHash().isEmpty()) {
                c.setPasswordHash(PasswordUtil.sha256(c.getPasswordHash()));
            } else {
                // keep existing hash
                Customer existing = customerDao.findById(c.getId());
                c.setPasswordHash(existing.getPasswordHash());
            }
            customerDao.update(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateAccountNumber(int id) {
        return "BANK2025" + String.format("%04d", id);
    }
}
