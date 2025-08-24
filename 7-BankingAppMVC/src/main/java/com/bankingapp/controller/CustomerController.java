package com.bankingapp.controller;

import java.io.IOException;
//
//@WebServlet("/customer")
//public class CustomerController extends HttpServlet {
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
//                request.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(request, response);
//                break;
//            
//            case "credit":
//                request.getRequestDispatcher("/WEB-INF/views/customer/credit.jsp").forward(request, response);
//                break;
//            case "transfer":
//                request.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(request, response);
//                break;
//            case "profile":
//                request.getRequestDispatcher("/WEB-INF/views/customer/profile.jsp").forward(request, response);
//                break;
//                
//            case "logout":
//                request.getSession().invalidate();
//                response.sendRedirect(request.getContextPath() + "/auth?role=customer");
//                break;
//
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid customer action: " + action);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response);
//    }
//}
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



//import java.sql.Date;
//import java.util.List;
//
//import com.bankingapp.model.Customer;
//import com.bankingapp.model.Transaction;
//import com.bankingapp.service.CustomerService;
//import com.bankingapp.service.TransactionService;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebServlet("/customer")
//public class CustomerController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    private final CustomerService custService = new CustomerService();
//    private final TransactionService txService = new TransactionService();
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
//                request.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(request, response);
//                break;
//
//            case "passbook":
//                try {
//                    Customer c = (Customer) request.getSession().getAttribute("customer");
//
//                    String fromStr = request.getParameter("from");
//                    String toStr = request.getParameter("to");
//                    String type = request.getParameter("type");
//
//                    Date from = (fromStr != null && !fromStr.isEmpty()) ? Date.valueOf(fromStr) : null;
//                    Date to = (toStr != null && !toStr.isEmpty()) ? Date.valueOf(toStr) : null;
//                    if (type == null) type = "ALL";
//
//                    // fetch only this customer's view
//                    List<Transaction> list = txService.filter(c.getAccountNumber(), from, to, type, false);
//
//                    // ✅ override IDs with serial numbers for passbook display
//                    int serial = 1;
//                    for (Transaction t : list) {
//                        t.setId(serial++);
//                    }
//
//                    request.setAttribute("transactions", list);
//                } catch (Exception e) {
//                    throw new ServletException(e);
//                }
//                request.getRequestDispatcher("/WEB-INF/views/customer/passbook.jsp").forward(request, response);
//                break;
//
//            case "credit":
//                request.getRequestDispatcher("/WEB-INF/views/customer/credit.jsp").forward(request, response);
//                break;
//
//            case "transfer":
//                request.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(request, response);
//                break;
//
//            case "profile":
//                request.getRequestDispatcher("/WEB-INF/views/customer/profile.jsp").forward(request, response);
//                break;
//
//            case "logout":
//                request.getSession().invalidate();
//                response.sendRedirect(request.getContextPath() + "/auth?action=loginCustomer");
//                break;
//
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid customer action: " + action);
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
//            Customer c = (Customer) request.getSession().getAttribute("customer");
//
//            if ("credit".equals(action)) {
//                double amount = Double.parseDouble(request.getParameter("amount"));
//                txService.credit(c.getAccountNumber(), amount);
//
//                Customer updated = custService.findByAccount(c.getAccountNumber());
//                request.getSession().setAttribute("customer", updated);
//
//                response.sendRedirect(request.getContextPath() + "/customer?action=passbook");
//
//            } else if ("transfer".equals(action)) {
//                String toAccount = request.getParameter("toAccount");
//                double amount = Double.parseDouble(request.getParameter("amount"));
//                txService.transfer(c.getAccountNumber(), toAccount, amount);
//
//                Customer updated = custService.findByAccount(c.getAccountNumber());
//                request.getSession().setAttribute("customer", updated);
//
//                response.sendRedirect(request.getContextPath() + "/customer?action=passbook");
//
//            } else {
//                doGet(request, response);
//            }
//
//        } catch (Exception e) {
//            throw new ServletException("Error processing customer action: " + action, e);
//        }
//    }
//}


@WebServlet("/customer")
public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final CustomerService custService = new CustomerService();
    private final TransactionService txService = new TransactionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "dashboard";

        switch (action) {
//            case "dashboard":
//                request.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(request, response);
//                break;
        case "dashboard":
            Customer c = (Customer) request.getSession().getAttribute("customer");

            if (c == null) {
                // session expired or not logged in
                response.sendRedirect(request.getContextPath() + "/auth?action=loginCustomer");
                return;
            }

            try {
                List<Transaction> txs = txService.filter(c.getAccountNumber(), null, null, "ALL", false);

                long total = txs.size();
                long in = txs.stream().filter(t -> t.getToAccount().equals(c.getAccountNumber())
                                                && !t.getFromAccount().equals(c.getAccountNumber())).count();
                long out = txs.stream().filter(t -> t.getFromAccount().equals(c.getAccountNumber())
                                                 && !t.getToAccount().equals(c.getAccountNumber())).count();
                long selfCredit = txs.stream().filter(t -> t.getFromAccount().equals(c.getAccountNumber())
                                                        && t.getToAccount().equals(c.getAccountNumber())
                                                        && "CREDIT".equalsIgnoreCase(t.getType())).count();

                request.setAttribute("totalTx", total);
                request.setAttribute("inTx", in);
                request.setAttribute("outTx", out);
                request.setAttribute("selfCreditTx", selfCredit);

            } catch (Exception e) {
                throw new ServletException(e);
            }

            request.getRequestDispatcher("/WEB-INF/views/customer/dashboard.jsp").forward(request, response);
            break;



//            case "passbook":
//                try {
//                    Customer c = (Customer) request.getSession().getAttribute("customer");
//
//                    String fromStr = request.getParameter("from");
//                    String toStr = request.getParameter("to");
//                    String type = request.getParameter("type");
//
//                    Date from = (fromStr != null && !fromStr.isEmpty()) ? Date.valueOf(fromStr) : null;
//                    Date to = (toStr != null && !toStr.isEmpty()) ? Date.valueOf(toStr) : null;
//                    if (type == null) type = "ALL";
//
//                    List<Transaction> list = txService.filter(c.getAccountNumber(), from, to, type, false);
//
//                    int serial = 1;
//                    for (Transaction t : list) {
//                        t.setId(serial++);
//                    }
//
//                    request.setAttribute("transactions", list);
//                } catch (Exception e) {
//                    throw new ServletException(e);
//                }
//                request.getRequestDispatcher("/WEB-INF/views/customer/passbook.jsp").forward(request, response);
//                break;
        case "passbook":
            try {
                Customer cust = (Customer) request.getSession().getAttribute("customer");

                if (cust == null) {
                    response.sendRedirect(request.getContextPath() + "/auth?action=loginCustomer");
                    return;
                }

                String fromStr = request.getParameter("from");
                String toStr = request.getParameter("to");
                String type = request.getParameter("type");

                Date from = (fromStr != null && !fromStr.isEmpty()) ? Date.valueOf(fromStr) : null;
                Date to = (toStr != null && !toStr.isEmpty()) ? Date.valueOf(toStr) : null;
                if (type == null) type = "ALL";

                List<Transaction> list = txService.filter(cust.getAccountNumber(), from, to, type, false);

                int serial = 1;
                for (Transaction t : list) {
                    t.setId(serial++);
                }

                request.setAttribute("transactions", list);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            request.getRequestDispatcher("/WEB-INF/views/customer/passbook.jsp").forward(request, response);
            break;


            case "credit":
                request.getRequestDispatcher("/WEB-INF/views/customer/credit.jsp").forward(request, response);
                break;

            case "transfer":
                request.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(request, response);
                break;

            case "profile":
                request.getRequestDispatcher("/WEB-INF/views/customer/profile.jsp").forward(request, response);
                break;

//            case "logout":
//                request.getSession().invalidate();
//                response.sendRedirect(request.getContextPath() + "/auth?action=loginCustomer");
//                break;
                
            case "logout":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/index.jsp");
                break;


            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid customer action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        Customer c = (Customer) request.getSession().getAttribute("customer");

        if ("credit".equals(action)) {
            try {
                double amount = Double.parseDouble(request.getParameter("amount"));
                txService.credit(c.getAccountNumber(), amount);

                Customer updated = custService.findByAccount(c.getAccountNumber());
                request.getSession().setAttribute("customer", updated);

                response.sendRedirect(request.getContextPath() + "/customer?action=passbook");

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/customer/credit.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error processing customer action: credit", e);
            }

        } else if ("transfer".equals(action)) {
            try {
                String toAccount = request.getParameter("toAccount");
                double amount = Double.parseDouble(request.getParameter("amount"));

                txService.transfer(c.getAccountNumber(), toAccount, amount);

                Customer updated = custService.findByAccount(c.getAccountNumber());
                request.getSession().setAttribute("customer", updated);

                response.sendRedirect(request.getContextPath() + "/customer?action=passbook");

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/customer/transfer.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error processing customer action: transfer", e);
            }

        } else if ("updateProfile".equals(action)) {
            try {
                String fullName = request.getParameter("fullName");
                String phone    = request.getParameter("phone");
                String address  = request.getParameter("address");
                String password = request.getParameter("password"); 

                // ✅ Apply validations
                ValidationUtil.validateName(fullName);
                ValidationUtil.validatePhone(phone);
                ValidationUtil.validateNotBlank(address, "Address");

                custService.updateCustomer(c.getId(), fullName, password, phone, address, c.getBalance(), true);

                Customer updated = custService.findByAccount(c.getAccountNumber());
                request.getSession().setAttribute("customer", updated);

                response.sendRedirect(request.getContextPath() + "/customer?action=profile");

            } catch (IllegalArgumentException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/WEB-INF/views/customer/profile.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error updating profile", e);
            }

        } else {
            doGet(request, response);
        }
    }
}


