<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-customer.jspf" %>

<h2>Profile</h2>

<div class="card shadow p-3 my-4">
    <form action="customer" method="post" class="row g-3">
        <input type="hidden" name="action" value="updateProfile"/>

        <div class="col-md-6">
            <label class="form-label">Full Name</label>
            <input type="text" name="fullName" class="form-control"
                   value="${sessionScope.customer.fullName}" required
                   pattern="[A-Za-z ]+" title="Name can only contain letters and spaces"/>
        </div>

        <div class="col-md-6">
            <label class="form-label">Email (cannot change)</label>
            <input type="email" class="form-control"
                   value="${sessionScope.customer.email}" readonly/>
        </div>

        <div class="col-md-6">
            <label class="form-label">Password</label>
            <input type="password" name="password" class="form-control"
                   placeholder="Leave blank to keep current"/>
        </div>

        <div class="col-md-6">
            <label class="form-label">Mobile</label>
            <input type="text" name="phone" class="form-control"
                   value="${sessionScope.customer.phone}"
                   pattern="[0-9]{10}" title="Phone must be exactly 10 digits"/>
        </div>

        <div class="col-md-12">
            <label class="form-label">Address</label>
            <input type="text" name="address" class="form-control"
                   value="${sessionScope.customer.address}" required/>
        </div>

        <div class="col-md-12">
            <button type="submit" class="btn btn-success">Update Profile</button>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
</div>

<%@ include file="../includes/footer.jspf" %>
