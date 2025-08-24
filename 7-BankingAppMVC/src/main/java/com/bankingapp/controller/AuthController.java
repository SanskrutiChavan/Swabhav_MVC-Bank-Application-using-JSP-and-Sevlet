package com.bankingapp.controller;

import java.io.IOException;

import com.bankingapp.dao.CustomerDao;
import com.bankingapp.model.Customer;
import com.bankingapp.service.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//---------first basic----------------------
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/auth")
//public class AuthController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//        if (action == null) action = "loginChoice";
//
//        switch (action) {
//            case "loginChoice":
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//                break;
//            case "loginAdmin":
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//                break;
//            case "loginCustomer":
//                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
//                break;
//            case "logout":
//                request.getSession().invalidate();
//                response.sendRedirect(request.getContextPath() + "/index.jsp");
//                break;
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid auth action: " + action);
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // here you’ll handle login form submissions
//        doGet(request, response);
//    }
//}


//---------------------------------------if else and first corrected by batki------------------


//
//import jakarta.servlet.ServletException;
//
//import jakarta.servlet.annotation.WebServlet;
//
//import jakarta.servlet.http.HttpServlet;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//import jakarta.servlet.http.HttpServletResponse;
//
//import jakarta.servlet.http.HttpSession; // Import HttpSession
//
//import java.io.IOException;
// 
//@WebServlet("/auth")
//
//public class AuthController extends HttpServlet {
//
//    // Your doGet method remains the same...
//
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//
//	@Override
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//
//            throws ServletException, IOException {
// 
//        String action = request.getParameter("action");
//
//        if (action == null) action = "loginChoice";
// 
//        switch (action) {
//
//            case "loginChoice":
//
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//
//                break;
//
//            case "loginAdmin":
//
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//
//                break;
//
//            case "loginCustomer":
//
//                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
//
//                break;
//
//            case "logout":
//
//                request.getSession().invalidate();
//
//                response.sendRedirect(request.getContextPath() + "/index.jsp");
//
//                break;
//
//            default:
//
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid auth action: " + action);
//
//        }
//
//    }
// 
// 
//    // Replace your old doPost method with this one
//
//    @Override
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
// 
//        if ("loginAdmin".equals(action)) {
//
//            // 1. Get username and password from the form submission
//
//            String username = request.getParameter("username");
//
//            String password = request.getParameter("password");
//            
//            
// 
//            // 2. Authenticate the user (this is a simple example)
//
//            // TODO: Replace this with your actual database validation logic
//
//            if ("admin".equals(username) && "admin123".equals(password)) {
//
//                // SUCCESS!
//
//                // 3. Create a session to keep the user logged in
//
//                HttpSession session = request.getSession();
//
//                session.setAttribute("adminUser", username);
// 
//                // 4. Redirect to the admin dashboard page
//
//                //response.sendRedirect("/admin");
//                
//                response.sendRedirect(request.getContextPath() + "/admin");
// 
//            } 
//            else if ("loginCustomer".equals(action)) 
//            {
//
//                // 1. Get username and password from the form submission
//
//                String cusername = request.getParameter("username");
//
//                String cpassword = request.getParameter("password");
//                
//                
//     
//                // 2. Authenticate the user (this is a simple example)
//
//                // TODO: Replace this with your actual database validation logic
//
//                if ("john_doe".equals(username) && "pass123".equals(password)) 
//                {
//
//                    // SUCCESS!
//
//                    // 3. Create a session to keep the user logged in
//
//                    HttpSession session = request.getSession();
//
//                    session.setAttribute("customerUser", username);
//     
//                    // 4. Redirect to the admin dashboard page
//
//                    //response.sendRedirect("/admin");
//                    
//                    response.sendRedirect(request.getContextPath() + "/customer");
//     
//                }
//            else 
//            {
//
//                // FAILED!
//
//                // 5. Set an error message and forward back to the login page
//
//                request.setAttribute("error", "Invalid username or password");
//
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//
//            }
//
//        } else 
//        {
//
//            // Handle other potential POST actions here (like customer login)
//
//            // For now, we can just call doGet or send an error
//
//            doGet(request, response);
//
//        }
//
//    }
//    }
//}
 

//----------------------------hardcoded password and username and corrected if else by batki--------------------


//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
// 
//@WebServlet("/auth")
//public class AuthController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
// 
//    /**
//     * Handles GET requests to display login pages or perform logout.
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
// 
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "loginChoice"; // Default action
//        }
// 
//        switch (action) {
//            case "loginChoice":
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//                break;
//            case "loginAdmin":
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//                break;
//            case "loginCustomer":
//                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
//                break;
//            case "logout":
//                request.getSession().invalidate(); // Invalidate session
//                response.sendRedirect(request.getContextPath() + "/index.jsp");
//                break;
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid auth action: " + action);
//        }
//    }
// 
//    /**
//     * Handles POST requests for login form submissions.
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
// 
//        String action = request.getParameter("action");
// 
//        // Check for Admin Login
//        if ("loginAdmin".equals(action)) {
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
// 
//            // TODO: Replace this with your actual database validation logic
//            if ("admin".equals(username) && "admin123".equals(password)) {
//                // SUCCESS
//                HttpSession session = request.getSession();
//                session.setAttribute("adminUser", username);
//                // Redirect to an AdminController, assuming it's mapped to "/admin"
//                response.sendRedirect(request.getContextPath() + "/admin");
//            } else {
//                // FAILED - Handles incorrect admin credentials
//                request.setAttribute("error", "Invalid admin username or password");
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//            }
// 
//        // Check for Customer Login (Correctly structured as "else if")
//        } else if ("loginCustomer".equals(action)) {
//            String cusername = request.getParameter("username");
//            String cpassword = request.getParameter("password");
// 
//            // TODO: Replace this with your actual database validation logic
//            if ("john_doe".equals(cusername) && "pass123".equals(cpassword)) {
//                // SUCCESS
//                HttpSession session = request.getSession();
//                session.setAttribute("customerUser", cusername);
//                // Redirect to the CustomerController, which is mapped to "/customer"
//                response.sendRedirect(request.getContextPath() + "/customer");
//            } else {
//                // FAILED
//                request.setAttribute("error", "Invalid customer username or password");
//                // Forwards back to the correct customer login page
//                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
//            }
// 
//        // Handle any other unknown POST actions
//        } else {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid POST action specified.");
//        }
//    }
//}



//--------------------------------Latest with db password and not hardcoded------------------------------------




//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import java.io.IOException;
//
//import com.bankingapp.dao.CustomerDao;
//import com.bankingapp.model.Customer;
//import com.bankingapp.service.PasswordUtil;
//
//@WebServlet("/auth")
//public class AuthController extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * Handles GET requests to display login pages or perform logout.
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "loginChoice"; // Default action
//        }
//
//        switch (action) {
//            case "loginChoice":
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//                break;
//            case "loginAdmin":
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//                break;
//            case "loginCustomer":
//                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
//                break;
//            case "logout":
//                request.getSession().invalidate(); // Invalidate session
//                response.sendRedirect(request.getContextPath() + "/index.jsp");
//                break;
//            default:
//                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid auth action: " + action);
//        }
//    }
//
//    /**
//     * Handles POST requests for login form submissions.
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String action = request.getParameter("action");
//
//        // Check for Admin Login
//        if ("loginAdmin".equals(action)) {
//            String username = request.getParameter("username");
//            String password = request.getParameter("password");
//
//            // TODO: Replace this with your actual database validation logic
//            if ("admin".equals(username) && "admin123".equals(password)) {
//                // SUCCESS
//                HttpSession session = request.getSession();
//                session.setAttribute("adminUser", username);
//                // Redirect to an AdminController, assuming it's mapped to "/admin"
//                response.sendRedirect(request.getContextPath() + "/admin");
//            } else {
//                // FAILED - Handles incorrect admin credentials
//                request.setAttribute("error", "Invalid admin username or password");
//                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
//            }
//
//        // ✅ Updated Customer Login
//        } else if ("loginCustomer".equals(action)) {
//            String cusername = request.getParameter("username");
//            String cpassword = request.getParameter("password");
//
//            try {
//                CustomerDao customerDao = new CustomerDao();
//                Customer customer = customerDao.findByUsername(cusername);
//
//                if (customer != null) {
//                    // Hash entered password
//                    String hashedInput = PasswordUtil.sha256(cpassword);
//
//                    // Compare with stored DB hash
//                    if (hashedInput.equals(customer.getPasswordHash())) {
//                        HttpSession session = request.getSession();
//                        session.setAttribute("customer", customer); // store full object
//                        response.sendRedirect(request.getContextPath() + "/customer?action=dashboard");
//                        return;
//                    }
//                }
//
//                // FAILED
//                request.setAttribute("error", "Invalid customer username or password");
//                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
//
//            } catch (Exception e) {
//                throw new ServletException("Login failed", e);
//            }
//
//        // Handle any other unknown POST actions
//        } else {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid POST action specified.");
//        }
//    }
//}





//--------------------


@WebServlet("/auth")
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "loginChoice";
        }

        switch (action) {
            case "loginChoice":
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "loginAdmin":
                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
                break;
            case "loginCustomer":
                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);
                break;
            case "logout":
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/auth?action=loginChoice");
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Invalid auth action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("loginAdmin".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if ("admin".equals(username) && "admin123".equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("adminUser", username);
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                request.setAttribute("error", "Invalid admin username or password");
                request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
            }

        } else if ("loginCustomer".equals(action)) {
            String cusername = request.getParameter("username");
            String cpassword = request.getParameter("password");

            try {
                CustomerDao customerDao = new CustomerDao();
                Customer customer = customerDao.findByUsername(cusername);

                if (customer != null) {
                    String hashedInput = PasswordUtil.sha256(cpassword);

                    if (hashedInput.equals(customer.getPasswordHash())) {
                        HttpSession session = request.getSession();
                        session.setAttribute("customer", customer);
                        response.sendRedirect(request.getContextPath() + "/customer?action=dashboard");
                        return;
                    }
                }

                request.setAttribute("error", "Invalid customer username or password");
                request.getRequestDispatcher("/login-customer.jsp").forward(request, response);

            } catch (Exception e) {
                throw new ServletException("Login failed", e);
            }

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid POST action specified.");
        }
    }
}
