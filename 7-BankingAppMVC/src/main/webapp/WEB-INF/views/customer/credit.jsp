<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-customer.jspf" %>

<h2>Credit Money</h2>

<div class="card shadow p-3 my-4">
    <form action="customer" method="post" class="row g-3">
        <input type="hidden" name="action" value="credit"/>
        <div class="col-md-6">
            <input type="number" step="1" min="1" name="amount" class="form-control"
                   placeholder="Enter Amount" required
                   title="Enter a positive whole number"/>
        </div>
        <div class="col-md-6">
            <button type="submit" class="btn btn-success w-100">Credit</button>
        </div>
    </form>

    <c:if test="${not empty error}">
        <div class="alert alert-danger mt-3">${error}</div>
    </c:if>
</div>

<%@ include file="../includes/footer.jspf" %>
