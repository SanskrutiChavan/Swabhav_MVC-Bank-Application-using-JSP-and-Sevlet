<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-admin.jspf" %>

<h2>Admin Dashboard</h2>
<p class="lead">Welcome, Admin! Use the sidebar to manage customers and view transactions.</p>


<!-- 🔹 Stats Row -->
<div class="row mt-4">
    <div class="col-md-3">
        <div class="card text-bg-primary shadow mb-3">
            <div class="card-body text-center">
                <h3>${totalCustomers}</h3>
                <p>Total Customers</p>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card text-bg-success shadow mb-3">
            <div class="card-body text-center">
                <h3>${activeCustomers}</h3>
                <p>Active Customers</p>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card text-bg-warning shadow mb-3">
            <div class="card-body text-center">
                <h3>${inactiveCustomers}</h3>
                <p>Inactive Customers</p>
            </div>
        </div>
    </div>
    <div class="col-md-3">
        <div class="card text-bg-info shadow mb-3">
            <div class="card-body text-center">
                <h3>${totalTransactions}</h3>
                <p>Total Transactions</p>
            </div>
        </div>
    </div>
</div>

<!-- 🔹 Navigation Cards -->
<div class="row mt-4">
    <div class="col-md-6">
        <a href="${pageContext.request.contextPath}/admin?action=customers" class="text-decoration-none">
            <div class="card shadow h-100">
                <div class="card-body text-center">
                    <h4 class="card-title">Manage Customers</h4>
                    <p class="card-text">Create, edit, and deactivate customer accounts.</p>
                </div>
            </div>
        </a>
    </div>
    <div class="col-md-6">
        <a href="${pageContext.request.contextPath}/admin?action=transactions" class="text-decoration-none">
            <div class="card shadow h-100">
                <div class="card-body text-center">
                    <h4 class="card-title">View Transactions</h4>
                    <p class="card-text">Browse and filter all customer transactions.</p>
                </div>
            </div>
        </a>
    </div>
</div>

<%@ include file="../includes/footer.jspf" %>

