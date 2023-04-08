package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/Customer_signup")
public class Customer_login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// 1st step
		String id = req.getParameter("cust_id");
		int cust_id = Integer.parseInt(id);

		String password = req.getParameter("password");

		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.login(cust_id);
		if (customer == null)
		{
			res.getWriter().print("<h1>Invalid customer id</h1>");
			req.getRequestDispatcher("Login.html").include(req, res);
		}
		else
		{
			if (customer.getPassword().equals(password))
			{
			res.getWriter().print("<h1>Login Successfully</h1>");
			req.getSession().setAttribute("customer", customer); //used to set the complete information of one table(customer)
			req.getRequestDispatcher("Customerhome.html").include(req, res);

			}
			else
			{
				res.getWriter().print("<h1>Entered wrong password");
				req.getRequestDispatcher("Login.html").include(req, res);
			}
		}
	}
}

