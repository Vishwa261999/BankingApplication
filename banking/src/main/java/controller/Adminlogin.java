package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Bankaccounts;

@WebServlet("/adminlogin")
public class Adminlogin extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		if(email.equals("admin")&&password.equals("admin"))
		{
			res.getWriter().print("<h1>Admin login Success...</h1>");
			CustomerDao customerDao = new CustomerDao();
		List<Bankaccounts> list	= customerDao.fetchall();
		req.getSession().setAttribute("list", list);
		req.getRequestDispatcher("Admin.jsp").include(req, res);
		}
		else
		{
			res.getWriter().print("<h1>Entered Invalid Credentials...</h1>");
			req.getRequestDispatcher("Admin.html").include(req, res);
		}
	}
}
