<%@ page import="java.util.List" %>
<%@ page import="com.bankingapp.model.Customer" %>
<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-admin.jspf" %>

<h2>Customer Management</h2>


<!-- New Customer Form -->
<div class="card shadow p-3 my-4">
    <h5>Add New Customer</h5>
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger">
        <%= request.getAttribute("error") %>
    </div>
<% } %>
    
    <form action="admin" method="post" class="row g-3">
        <input type="hidden" name="action" value="createCustomer"/>
        <div class="col-md-6">
            <input type="text" name="username" class="form-control" placeholder="Username" required/>
        </div>
        <div class="col-md-6">
            <input type="email" name="email" class="form-control" placeholder="Email" required/>
        </div>
        <div class="col-md-6">
            <input type="password" name="password" class="form-control" placeholder="Password" required/>
        </div>
        <div class="col-md-6">
            <input type="text" name="fullName" class="form-control" placeholder="Full Name" required/>
        </div>
        <div class="col-md-6">
            <input type="text" name="phone" class="form-control" placeholder="Mobile Number"/>
        </div>
        <div class="col-md-6">
            <input type="text" name="address" class="form-control" placeholder="Address"/>
        </div>
        <div class="col-md-6">
            <input type="number" step="0.01" name="balance" class="form-control" placeholder="Initial Balance" required/>
        </div>
        <div class="col-md-6">
            <button type="submit" class="btn btn-primary w-100">Create</button>
        </div>
    </form>
</div>
<!-- 🔹 Filter/Search Form -->
<div class="card shadow p-3 mb-3">
    <form action="admin" method="get" class="row g-3 align-items-end">
        <input type="hidden" name="action" value="customers"/>

        <!-- Search Field -->
        <div class="col-md-6">
            <label class="form-label">Search</label>
            <input type="text" name="keyword" class="form-control"
                   placeholder="Search by username, email, name, phone or account"
                   value="<%= request.getParameter("keyword") != null ? request.getParameter("keyword") : "" %>"/>
        </div>

        <!-- Status Filter -->
        <div class="col-md-3">
            <label class="form-label">Status</label>
            <select name="status" class="form-select">
                <option value="ALL" <%= "ALL".equals(request.getParameter("status")) ? "selected" : "" %>>All</option>
                <option value="ACTIVE" <%= "ACTIVE".equals(request.getParameter("status")) ? "selected" : "" %>>Active</option>
                <option value="INACTIVE" <%= "INACTIVE".equals(request.getParameter("status")) ? "selected" : "" %>>Inactive</option>
            </select>
        </div>

        <!-- Search Button -->
        <div class="col-md-3">
            <button type="submit" class="btn btn-primary w-100">Search</button>
        </div>
    </form>
</div>


<!-- Customer Table -->
<div class="card shadow p-3">
    <h5>All Customers</h5>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>#</th> <!-- Serial Number -->
                <th>Account</th>
                <th>Username</th>
                <th>Email</th>
                <th>Full Name</th>
                <th>Phone</th>
                <th>Balance</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <%
            List<Customer> list = (List<Customer>) request.getAttribute("customers");
            if (list != null) {
                int serial = 1; // initialize serial number
                for (Customer c : list) {
        %>
            <tr>
                <td><%= serial++ %></td> <!-- Serial number column -->
                <td><%= c.getAccountNumber() %></td>
                <td><%= c.getUsername() %></td>
                <td><%= c.getEmail() %></td>
                <td><%= c.getFullName() %></td>
                <td><%= c.getPhone() %></td>
                <td><%= c.getBalance() %></td>
                <td><%= c.isActive() ? "Active" : "Inactive" %></td>
                 <td>
            <form action="admin" method="post" style="display:inline;">
                <input type="hidden" name="action" value="toggleActive"/>
                <input type="hidden" name="id" value="<%= c.getId() %>"/>
                <button class="btn btn-sm <%= c.isActive() ? "btn-danger" : "btn-success" %>">
                
                    <%= c.isActive() ? "Deactivate" : "Activate" %>
                </button>
            </form>
        </td>
                
            </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>


<%@ include file="../includes/footer.jspf" %>
