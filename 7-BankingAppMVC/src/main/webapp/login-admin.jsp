<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center justify-content-center vh-100">
<div class="card shadow-lg p-4" style="width: 22rem;">
    <h3 class="text-center mb-4">Admin Login</h3>
    <form action="auth" method="post">
        <input type="hidden" name="action" value="loginAdmin"/>
        <div class="mb-3">
            <input type="text" class="form-control" name="username" placeholder="Username" required/>
        </div>
        <div class="mb-3">
            <input type="password" class="form-control" name="password" placeholder="Password" required/>
        </div>
        <button type="submit" class="btn btn-primary w-100">Login</button>
        <a href="index.jsp" class="btn btn-link w-100 mt-2">Back</a>
    </form>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="alert alert-danger mt-3"><%= error %></div>
    <% } %>
</div>
</body>
</html>
