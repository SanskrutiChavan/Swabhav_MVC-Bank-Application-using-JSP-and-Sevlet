<%@ page import="java.util.List" %>
<%@ page import="com.bankingapp.model.Transaction" %>
<%@ include file="../includes/header.jspf" %>
<%@ include file="../includes/sidebar-customer.jspf" %>

<h2>Passbook</h2>

<!-- Filter Form -->
<div class="card shadow p-3 my-4">
    <h5>Filter Transactions</h5>
   	<form action="customer" method="get">
    <input type="hidden" name="action" value="passbook"/>
    <input type="date" name="from" value="<%= request.getParameter("from") %>"/>
    <input type="date" name="to" value="<%= request.getParameter("to") %>"/>
    <select name="type">
        <option value="ALL" <%= "ALL".equals(request.getParameter("type")) ? "selected" : "" %>>All</option>
        <option value="CREDIT" <%= "CREDIT".equals(request.getParameter("type")) ? "selected" : "" %>>Credit</option>
        <option value="TRANSFER" <%= "TRANSFER".equals(request.getParameter("type")) ? "selected" : "" %>>Transfer</option>
    </select>
    <button type="submit">Filter</button>
</form>
   	
</div>

<!-- Transaction Table -->
<div class="card shadow p-3">
    <h5>Your Transactions</h5>
    <table class="table table-striped">
        <thead>
            <tr>
                <th>ID</th>
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
            List<Transaction> txs = (List<Transaction>) request.getAttribute("transactions");
            if (txs != null) {
                for (Transaction t : txs) {
        %>
            <tr>
                <td><%= t.getId() %></td>
                <td><%= t.getFromAccount() %></td>
                <td><%= t.getToAccount() %></td>
                <td><%= t.getType() %></td>
                <td><%= t.getAmount() %></td>
                <td><%= t.getTimestamp() %></td>
                <td><%= t.getDescription() %></td>
            </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>

<%@ include file="../includes/footer.jspf" %>
