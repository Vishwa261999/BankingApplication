<%@page import="dto.Bank_transaction"%>
<%@page import="java.util.List"%>
<%@page import="dto.Bankaccounts"%>
<%@page import="dao.Bankdao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Transaction History</h1>

	<% Long acno = (Long)request.getSession().getAttribute("acno");
	
	Bankdao bankdao = new Bankdao();
	
	Bankaccounts bankaccounts = bankdao.find(acno);
	
	List<Bank_transaction> list = bankaccounts.getBank_transactions();
	%>
	
	<table border="1">
	<tr>
	<th>Transaction_id</th>
	<th>Deposit</th>
	<th>Withdraw</th>
	<th>Balance</th>
	<th>Date_time</th>
	</tr>
	
	<% for(Bank_transaction bank_transaction:list) {%>
	<tr>
	<th><%= bank_transaction.getTransaction_id() %></th>
	<th><%= bank_transaction.getDeposit() %></th>
	<th><%= bank_transaction.getWithdraw() %></th>
	<th><%= bank_transaction.getBalance() %></th>
	<th><%= bank_transaction.getDate_time() %></th>
	
	</tr>
	
	<%}%>
	</table>
	
	<br><br>
	<a href="Accounthome.jsp"><button>Back</button></a>
</body>
</html>