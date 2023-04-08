package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setactiveaccounts")
public class Setactive_account extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String acn = req.getParameter("acno");
		
		long acno = Long.parseLong(acn);
		
		 req.getSession().setAttribute("acno", acno);
		 
		 req.getRequestDispatcher("Accounthome.jsp").include(req, res);
	}
}
