package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDao;
import dto.Customer;

@WebServlet("/signup")
public class Customer_signup extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		CustomerDao customerDao = new CustomerDao();
		
		String name = req.getParameter("name");
		String mob = req.getParameter("mob");
		long mobile = Long.parseLong(mob);
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String gender = req.getParameter("gen");
		String dob = req.getParameter("dob");
		
		Date date = Date.valueOf(dob);
		
		Period period = Period.between(date.toLocalDate(), LocalDate.now());
		
		int age = period.getYears();
		
		if(age<18){
			res.getWriter().print("<h1>u have to be 18 to create account</h1>");
			req.getRequestDispatcher("Signup.html").include(req, res);
		}
		else
		{
			if(customerDao.check(mobile).isEmpty()&&customerDao.check(email).isEmpty())
			{
				Customer customer = new Customer();
				customer.setDate(date);
				customer.setEmail(email);
				customer.setGender(gender);
				customer.setMobile(mobile);
				customer.setName(name);
				customer.setPassword(password);
				
				
				customerDao.save(customer);
				
				res.getWriter().print("<h1>Account has been created successfully</h1>");
				
				req.getRequestDispatcher("Login.html").include(req, res);
				
				Customer customer2 = new CustomerDao().check(email).get(0);
							int id = customer2.getCust_id();
				
				if(customer2.getGender().equals("MALE"))
				{
					res.getWriter().print("<h1>Hello Sir</h1>");
				}
				else
				{
					res.getWriter().print("<h1>Hello Madam</h1>");
				}
				res.getWriter().print("<h1>Your Customer id is "+id+"</h1>");
			}
			else
			{
				res.getWriter().print("<h1> Account already exists</h1>");
			}
		
		}
	}
	
}
