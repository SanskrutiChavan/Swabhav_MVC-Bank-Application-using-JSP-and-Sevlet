<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-customer.jspf" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customer Dashboard</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"/>
    <style>
        .card-hover {
            transition: transform 0.2s ease, box-shadow 0.2s ease;
            cursor: pointer;
        }
        .card-hover:hover {
            transform: scale(1.05);
            box-shadow: 0 6px 15px rgba(0,0,0,0.2);
        }
    </style>
</head>
<body class="bg-light">
<div class="container mt-4">

    <!-- Welcome -->
    <h2 class="mb-4">Customer Dashboard</h2>
    <h4 class="mb-4">Welcome, ${customer.fullName}!</h4>

    <!-- Navigation Cards -->
    <div class="row g-4 mb-5">
        <!-- Dashboard -->
        <div class="col-md-4">
            <div class="card card-hover text-center"
                 onclick="location.href='${pageContext.request.contextPath}/customer?action=dashboard'">
                <div class="card-body">
                    <h5 class="card-title">Dashboard</h5>
                    <p class="card-text">View balance and transaction summary.</p>
                </div>
            </div>
        </div>

        <!-- Passbook -->
        <div class="col-md-4">
            <div class="card card-hover text-center"
                 onclick="location.href='${pageContext.request.contextPath}/customer?action=passbook'">
                <div class="card-body">
                    <h5 class="card-title">Passbook</h5>
                    <p class="card-text">Check all your transactions.</p>
                </div>
            </div>
        </div>

        <!-- Credit -->
        <div class="col-md-4">
            <div class="card card-hover text-center"
                 onclick="location.href='${pageContext.request.contextPath}/customer?action=credit'">
                <div class="card-body">
                    <h5 class="card-title">Credit</h5>
                    <p class="card-text">Deposit money into your account.</p>
                </div>
            </div>
        </div>

        <!-- Transfer -->
        <div class="col-md-4">
            <div class="card card-hover text-center"
                 onclick="location.href='${pageContext.request.contextPath}/customer?action=transfer'">
                <div class="card-body">
                    <h5 class="card-title">Transfer</h5>
                    <p class="card-text">Send money to another account.</p>
                </div>
            </div>
        </div>

        <!-- Profile -->
        <div class="col-md-4">
            <div class="card card-hover text-center"
                 onclick="location.href='${pageContext.request.contextPath}/customer?action=profile'">
                <div class="card-body">
                    <h5 class="card-title">Profile</h5>
                    <p class="card-text">View and update your profile.</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Summary Cards -->
    <div class="row g-4">
        <div class="col-md-4">
            <div class="card text-center bg-primary text-white">
                <div class="card-body">
                    <h5 class="card-title">Balance</h5>
                    <h3>₹ ${customer.balance}</h3>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center bg-success text-white">
                <div class="card-body">
                    <h5 class="card-title">Total Transactions</h5>
                    <h3>${totalTx}</h3>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center bg-info text-white">
                <div class="card-body">
                    <h5 class="card-title">In Transactions</h5>
                    <h3>${inTx}</h3>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center bg-warning text-dark">
                <div class="card-body">
                    <h5 class="card-title">Out Transactions</h5>
                    <h3>${outTx}</h3>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card text-center bg-secondary text-white">
                <div class="card-body">
                    <h5 class="card-title">Self Credits</h5>
                    <h3>${selfCreditTx}</h3>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>



<%@ include file="../includes/footer.jspf" %>
