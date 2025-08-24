<%@ page import="java.util.List" %>
<%@ page import="com.bankingapp.model.Transaction" %>
<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-admin.jspf" %>

<h2>Transactions</h2>

<!-- Filter Form -->
<div class="card shadow p-3 my-4">
    <h5>Filter Transactions</h5>
    <form action="admin" method="get" class="row g-3">
        <input type="hidden" name="action" value="transactions"/>
        <div class="col-md-3">
            <input type="text" name="account" class="form-control" placeholder="Account Number"/>
        </div>
        <div class="col-md-3">
            <input type="date" name="from" class="form-control"/>
        </div>
        <div class="col-md-3">
            <input type="date" name="to" class="form-control"/>
        </div>
        <div class="col-md-2">
            <select name="type" class="form-select">
                <option value="ALL">All</option>
                <option value="CREDIT">Credit</option>
                <option value="TRANSFER">Transfer</option>
            </select>
        </div>
        <div class="col-md-1">
            <button type="submit" class="btn btn-primary w-100">Go</button>
        </div>
    </form>
</div>

<!-- Transaction Table -->
<div class="card shadow p-3">
    <h5>Transaction Records</h5>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>Serial</th>
                <th>From</th>
                <th>To</th>
                <th>Type</th>
                <th>Amount</th>
                <th>Timestamp</th>
                <th>Description</th>
            </tr>
        </thead>
        <tbody>
        <%
            int serial = 1; // start serial numbering
            List<Transaction> txs = (List<Transaction>) request.getAttribute("transactions");
            if (txs != null) {
                for (Transaction t : txs) {
        %>
            <tr>
                <td><%= serial++ %></td>
                <td><%= t.getFromAccount() %></td>
                <td><%= t.getToAccount() %></td>
                <td><%= t.getType() %></td>
                <td><%= t.getAmount() %></td>
                <td><%= t.getTimestamp() %></td>
                <td><%= t.getDescription() %></td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="7" class="text-center">No transactions found.</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<%@ include file="../includes/footer.jspf" %>

