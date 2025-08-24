package com.bankingapp.service;



import com.bankingapp.dao.CustomerDao;
import com.bankingapp.model.Customer;

public class CustomerService {
    private final CustomerDao customerDao = new CustomerDao();

    // ✅ Create a new customer with validations
    public void createCustomer(String username, String email, String passwordPlain,
                               String fullName, String phone, String address, double balance) {
        try {
            // --- Validation checks ---
            if (customerDao.findByUsername(username) != null) {
                throw new IllegalArgumentException("Username already exists: " + username);
            }
            if (customerDao.findByEmail(email) != null) {
                throw new IllegalArgumentException("Email already exists: " + email);
            }

            // Build customer
            Customer c = new Customer();
            c.setUsername(username);
            c.setEmail(email);
            c.setPasswordHash(PasswordUtil.sha256(passwordPlain)); // hash password
            c.setFullName(fullName);
            c.setPhone(phone);
            c.setAddress(address);
            c.setBalance(balance);
            c.setActive(true);

            // Insert into DB
            int newId = customerDao.create(c);

            if (newId > 0) {
                // Generate account number like BANK2025XXXX
                String account = "BANK2025" + String.format("%04d", newId);
                customerDao.setAccountNumber(newId, account);
            }
        } catch (IllegalArgumentException e) {
            // validation failure → let controller show clean message
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error creating customer", e);
        }
    }

    // ✅ Update existing customer profile
    public void updateCustomer(int id, String fullName, String passwordPlain,
                               String phone, String address, double balance, boolean active) {
        try {
            Customer c = customerDao.findById(id);
            if (c == null) throw new RuntimeException("Customer not found: " + id);

            c.setFullName(fullName);
            c.setPhone(phone);
            c.setAddress(address);
            c.setBalance(balance);
            c.setActive(active);

            if (passwordPlain != null && !passwordPlain.isEmpty()) {
                c.setPasswordHash(PasswordUtil.sha256(passwordPlain));
            }

            customerDao.update(c);
        } catch (Exception e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    public void softDelete(int id) {
        try {
            customerDao.softDelete(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    public Customer findById(int id) {
        try {
            return customerDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error finding customer by id", e);
        }
    }

    public Customer findByUsername(String username) {
        try {
            return customerDao.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException("Error finding customer by username", e);
        }
    }

    public Customer findByAccount(String account) {
        try {
            return customerDao.findByAccount(account);
        } catch (Exception e) {
            throw new RuntimeException("Error finding customer by account", e);
        }
    }

    public Customer findByEmail(String email) {
        try {
            return customerDao.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Error finding customer by email", e);
        }
    }

    public java.util.List<Customer> listAllCustomers(boolean includeInactive) {
        try {
            return customerDao.listAll(includeInactive);
        } catch (Exception e) {
            throw new RuntimeException("Error listing customers", e);
        }
    }
    
    
 // ✅ Toggle active status
    public void toggleActive(int id) {
        try {
            Customer c = customerDao.findById(id);
            if (c == null) throw new RuntimeException("Customer not found: " + id);
            c.setActive(!c.isActive()); // invert status
            customerDao.update(c);
        } catch (Exception e) {
            throw new RuntimeException("Error toggling active status", e);
        }
    }


    public Customer refresh(int id) {
        try {
            return customerDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error refreshing customer", e);
        }
    }
}
