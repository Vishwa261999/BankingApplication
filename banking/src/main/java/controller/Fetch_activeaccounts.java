package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Bankaccounts;
import dto.Customer;

@WebServlet("/fetchactiveaccounts")
public class Fetch_activeaccounts extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Customer customer = (Customer)req.getSession().getAttribute("customer");
		List<Bankaccounts> list = customer.getBankaccounts();
		
		List<Bankaccounts>list2 = new ArrayList<Bankaccounts>();
		
		for(Bankaccounts bankaccounts:list)
		{
			list2.add(bankaccounts);
			
		}
		
		req.getSession().setAttribute("list", list2);
		req.getRequestDispatcher("Accounts.jsp").include(req, res);
	}
}
