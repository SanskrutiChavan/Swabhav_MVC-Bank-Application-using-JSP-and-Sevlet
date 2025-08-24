package com.bankingapp.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // Allow public pages
        if (path.startsWith("/auth") || path.equals("/index.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        // Customer authentication
        if (path.startsWith("/customer") && (session == null || session.getAttribute("customer") == null)) {
            res.sendRedirect(req.getContextPath() + "/auth?action=loginCustomer");
            return;
        }

        // Admin authentication
        if (path.startsWith("/admin") && (session == null || session.getAttribute("admin") == null)) {
            res.sendRedirect(req.getContextPath() + "/auth?action=loginAdmin");
            return;
        }

        chain.doFilter(request, response);
    }
}

