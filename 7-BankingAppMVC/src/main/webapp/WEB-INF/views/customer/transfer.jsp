<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-customer.jspf" %>

<h2>Transfer Money</h2>

<div class="card shadow p-3 my-4">
    <form action="customer" method="post" class="row g-3">
        <input type="hidden" name="action" value="transfer"/>

        <div class="col-md-4">
            <input type="text" name="toAccount" class="form-control"
                   placeholder="Recipient Account Number" required
                   pattern="BANK[0-9]{8}"
                   title="Enter a valid account number e.g. BANK20250001"/>
        </div>

        <div class="col-md-4">
            <input type="number" step="1" min="1" name="amount" class="form-control"
                   placeholder="Enter Amount" required
                   title="Enter a positive whole number"/>
        </div>

        <div class="col-md-4">
            <button type="submit" class="btn btn-primary w-100">Transfer</button>
        </div>
    </form>
	<c:if test="${not empty error}">
  	<div style="color:red; font-weight:bold;">
       ${error}
   </div>
</c:if>

</div>

<%@ include file="../includes/footer.jspf" %>
