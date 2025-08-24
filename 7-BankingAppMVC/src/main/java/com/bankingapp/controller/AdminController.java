package com.bankingapp.controller;

import java.io.IOException;
import java.util.List;

import com.bankingapp.model.Customer;
import com.bankingapp.model.Transaction;
import com.bankingapp.service.CustomerService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.service.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.bankingapp.model.Customer;
import com.bankingapp.model.Transaction;
import com.bankingapp.service.CustomerService;
import com.bankingapp.service.TransactionService;
import com.bankingapp.service.ValidationUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/admin")
//public class AdminController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//        if (action == null) action = "dashboard";
//
//        switch (action) {
//            case "dashboard":
//                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
//                break;
//            case "customers":
//                request.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp").forward(request, response);
//                break;
//            case "transactions":
//                request.getRequestDispatcher("/WEB-INF/views/admin/transactions.jsp").forward(request, response);
//                break;
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid admin action: " + action);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
//}



//@WebServlet("/admin")
//public class AdminController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private final CustomerService customerService = new CustomerService();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//        if (action == null) action = "dashboard";
//
//        switch (action) {
//            case "dashboard":
//                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
//                break;
//
//            case "customers":
//                try {
//                    // ✅ fetch all customers and pass to JSP
//                    List<Customer> customers = customerService.listAllCustomers(true);
//                    request.setAttribute("customers", customers);
//                } catch (Exception e) {
//                    throw new ServletException("Unable to fetch customers", e);
//                }
//                request.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp").forward(request, response);
//                break;
//
//            case "transactions":
//                request.getRequestDispatcher("/WEB-INF/views/admin/transactions.jsp").forward(request, response);
//                break;
//                
//            case "logout":
//                // ✅ clear session and redirect to login
//                request.getSession().invalidate();
//                response.sendRedirect(request.getContextPath() + "/auth?role=admin");
//                break;
//
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid admin action: " + action);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//
//        try {
//            switch (action) {
//                case "createCustomer": {
//                    String username = request.getParameter("username");
//                    String email = request.getParameter("email");
//                    String password = request.getParameter("password");
//                    String fullName = request.getParameter("fullName");
//                    String phone = request.getParameter("phone");
//                    String address = request.getParameter("address");
//                    double balance = Double.parseDouble(request.getParameter("balance"));
//
//                    customerService.createCustomer(username, email, password, fullName, phone, address, balance);
//
//                    response.sendRedirect("admin?action=customers");
//                    break;
//                }
//
//                case "deleteCustomer": {
//                    int id = Integer.parseInt(request.getParameter("id"));
//                    customerService.softDelete(id);
//                    response.sendRedirect("admin?action=customers");
//                    break;
//                }
//
//                case "updateCustomer": {
//                    int id = Integer.parseInt(request.getParameter("id"));
//                    String fullName = request.getParameter("fullName");
//                    String password = request.getParameter("password");
//                    String phone = request.getParameter("phone");
//                    String address = request.getParameter("address");
//                    double balance = Double.parseDouble(request.getParameter("balance"));
//                    boolean active = "on".equals(request.getParameter("active"));
//
//                    customerService.updateCustomer(id, fullName, password, phone, address, balance, active);
//                    response.sendRedirect("admin?action=customers");
//                    break;
//                }
//
//                default:
//                    doGet(request, response);
//            }
//        } catch (Exception e) {
//            throw new ServletException("Error in admin operation: " + action, e);
//        }
//    }
//}


//----------------------------admin last updated--------------------




@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CustomerService custService = new CustomerService();
    private final TransactionService txService = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) action = "dashboard";

        try {
            switch (action) {
            case "dashboard": {
                List<Customer> customers = custService.listAllCustomers(true);
                long active = customers.stream().filter(Customer::isActive).count();
                long inactive = customers.size() - active;
                int totalTransactions = txService.filter(null, null, null, "ALL", true).size();

                request.setAttribute("totalCustomers", customers.size());
                request.setAttribute("activeCustomers", active);
                request.setAttribute("inactiveCustomers", inactive);
                request.setAttribute("totalTransactions", totalTransactions);

                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp")
                       .forward(request, response);
                break;
            }


//                case "customers": {
//                    List<Customer> customers = custService.listAllCustomers(true);
//                    request.setAttribute("customers", customers);
//                    request.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp")
//                            .forward(request, response);
//                    break;
//                }
            
            case "customers": {
                String keyword = trimOrNull(request.getParameter("keyword"));
                String status  = trimOrNull(request.getParameter("status"));

                List<Customer> customers = custService.listAllCustomers(true);

                // 🔹 Apply keyword filter
                if (keyword != null && !keyword.isEmpty()) {
                    String kw = keyword.toLowerCase();
                    customers = customers.stream().filter(c ->
                        (c.getUsername() != null && c.getUsername().toLowerCase().contains(kw)) ||
                        (c.getEmail() != null && c.getEmail().toLowerCase().contains(kw)) ||
                        (c.getFullName() != null && c.getFullName().toLowerCase().contains(kw)) ||
                        (c.getPhone() != null && c.getPhone().toLowerCase().contains(kw)) ||
                        (c.getAccountNumber() != null && c.getAccountNumber().toLowerCase().contains(kw))
                    ).toList();
                }

                // 🔹 Apply status filter
                if ("ACTIVE".equalsIgnoreCase(status)) {
                    customers = customers.stream().filter(Customer::isActive).toList();
                } else if ("INACTIVE".equalsIgnoreCase(status)) {
                    customers = customers.stream().filter(c -> !c.isActive()).toList();
                }

                request.setAttribute("customers", customers);
                request.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp")
                       .forward(request, response);
                break;
            }


                case "transactions": {
                    String account = trimOrNull(request.getParameter("account"));
                    String fromStr = trimOrNull(request.getParameter("from"));
                    String toStr   = trimOrNull(request.getParameter("to"));
                    String type    = trimOrNull(request.getParameter("type"));
                    if (type == null) type = "ALL";

                    Date from = (fromStr != null && !fromStr.isEmpty()) ? Date.valueOf(fromStr) : null;
                    Date to   = (toStr   != null && !toStr.isEmpty())   ? Date.valueOf(toStr)   : null;

                    List<Transaction> list = txService.filter(account, from, to, type, true);
                    request.setAttribute("transactions", list);

                    request.getRequestDispatcher("/WEB-INF/views/admin/transactions.jsp")
                            .forward(request, response);
                    break;
                }

//                case "logout": {
//                    request.getSession().invalidate();
//                    response.sendRedirect(request.getContextPath() + "/auth?action=loginAdmin");
//                    break;
//               }
                
                case "logout":
                    request.getSession().invalidate();
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    break;


                default: {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid admin action: " + action);
                }
            }
        } catch (Exception e) {
            throw new ServletException("Error handling GET action: " + action, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            doGet(request, response);
            return;
        }

        try {
            switch (action) {
                case "createCustomer": {
                    String username = trimOrNull(request.getParameter("username"));
                    String email    = trimOrNull(request.getParameter("email"));
                    String password = trimOrNull(request.getParameter("password"));
                    String fullName = trimOrNull(request.getParameter("fullName"));
                    String phone    = trimOrNull(request.getParameter("phone"));
                    String address  = trimOrNull(request.getParameter("address"));
                    double balance  = parseDoubleOrZero(request.getParameter("balance"));

                    // ✅ Validations
                    ValidationUtil.validateNotBlank(username, "Username");
                    ValidationUtil.validateEmail(email);
                    ValidationUtil.validateNotBlank(password, "Password");
                    ValidationUtil.validateName(fullName);
                    ValidationUtil.validatePhone(phone);
                    ValidationUtil.validateNotBlank(address, "Address");
                    ValidationUtil.validateAmount(balance, "Balance");

                    custService.createCustomer(username, email, password, fullName, phone, address, balance);
                    response.sendRedirect(request.getContextPath() + "/admin?action=customers");
                    break;
                }

                case "deleteCustomer": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    custService.softDelete(id);
                    response.sendRedirect(request.getContextPath() + "/admin?action=customers");
                    break;
                }

                case "updateCustomer": {
                    int id         = Integer.parseInt(request.getParameter("id"));
                    String fullName= trimOrNull(request.getParameter("fullName"));
                    String password= trimOrNull(request.getParameter("password"));
                    String phone   = trimOrNull(request.getParameter("phone"));
                    String address = trimOrNull(request.getParameter("address"));
                    double balance = parseDoubleOrZero(request.getParameter("balance"));
                    boolean active = "on".equalsIgnoreCase(request.getParameter("active"));

                    // ✅ Validations
                    ValidationUtil.validateName(fullName);
                    ValidationUtil.validatePhone(phone);
                    ValidationUtil.validateNotBlank(address, "Address");
                    ValidationUtil.validateAmount(balance, "Balance");

                    custService.updateCustomer(id, fullName, password, phone, address, balance, active);
                    response.sendRedirect(request.getContextPath() + "/admin?action=customers");
                    break;
                }
                
                case "toggleActive": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    custService.toggleActive(id);
                    response.sendRedirect(request.getContextPath() + "/admin?action=customers");
                    break;
                }


                default: {
                    doGet(request, response);
                }
            }
//        } catch (IllegalArgumentException e) {
//            // ✅ Show validation error back to admin UI
//            request.setAttribute("error", e.getMessage());
//            doGet(request, response);
//        } catch (Exception e) {
//            throw new ServletException("Error handling POST action: " + action, e);
//        }
//    }
        } catch (IllegalArgumentException e) {
            // ✅ Show validation error back on customers page
            request.setAttribute("error", e.getMessage());
            List<Customer> customers = custService.listAllCustomers(true);
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/admin/customers.jsp")
                   .forward(request, response);
        }
    }


    // ✅ Helper methods
    private static String trimOrNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }

    private static double parseDoubleOrZero(String s) {
        try {
            return s == null ? 0.0 : Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}






