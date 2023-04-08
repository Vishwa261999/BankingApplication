package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bankdao;
import dto.Bankaccounts;

@WebServlet("/changestatus")
public class Change_status extends HttpServlet{
@Override
protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String acn = req.getParameter("acno");
	long acon = Long.parseLong(acn);     // i have successfully done parsing
	
	Bankdao bankdao = new Bankdao();
	Bankaccounts bankaccounts = bankdao.find(acon);
	
	if(bankaccounts.isStatus())      // condition should be true
	{
		bankaccounts.setStatus(false);
	}
	else
	{
		bankaccounts.setStatus(true);
	}
	bankdao.update(bankaccounts);
	
	res.getWriter().print("<h1> Account status has been updated successfully...</h1>");
	
	List<Bankaccounts> list = bankdao.fetchall();
	
	req.getSession().setAttribute("list", list);
	
	req.getRequestDispatcher("Admin.jsp").include(req, res);
}
}
