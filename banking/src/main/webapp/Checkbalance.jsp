<%@page import="dto.Customer"%>
<%@page import="dao.Bankdao"%>
<%@page import="dto.Bankaccounts"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% Long acno = (Long)request.getSession().getAttribute("acno"); 
	
	Bankdao bankdao = new Bankdao();
	
	Bankaccounts bankaccounts = bankdao.find(acno);
	
	Customer customer = bankaccounts.getCustomer();
	%>
	
	<h1>Hello<%if(customer.getGender().equals("male")){%>Ms.<% } else{%> Mr. <%}%> <%= customer.getName() %> </h1>
	
	<h1>Your <%=bankaccounts.getBank_type()%>account balance is <%=bankaccounts.getAmount() %></h1>
	
	<a href="Viewtransaction.jsp"><button>Transaction_history</button></a>
</body>
</html>