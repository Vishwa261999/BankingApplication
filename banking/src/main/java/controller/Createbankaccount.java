package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bankdao;
import dao.CustomerDao;
import dto.Bankaccounts;
import dto.Customer;

@WebServlet("/createaccount")
public class Createbankaccount extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	String banktype=req.getParameter("banktype");
	
	Customer customer = (Customer) req.getSession().getAttribute("customer"); //the return type of getattribute is object
	
	boolean flag = true;
	
	List<Bankaccounts> list = customer.getBankaccounts();
	
		
			//savings
	for(Bankaccounts bankaccounts:list)
	{
		if(bankaccounts.getBank_type().equals(banktype))
		{
			 flag = false;
//			res.getWriter().print("<h1>Account is already existed</h1>");
		}
	}
	
	Bankaccounts bankaccounts = new Bankaccounts();
				//current
	
	if(flag)
	{
		bankaccounts.setBank_type(banktype);
	

//	bankaccounts.setAcc_no(acc_no); it will be generated automatically
//	bankaccounts.setAmount(amount); amount will start from zero only
//	bankaccounts.setStatus(false); by default status will be false so its not required to set the status
	
	if(banktype.equals("savings"))
		bankaccounts.setAclimit(10000);
	
	else
		bankaccounts.setAclimit(5000);
	
	bankaccounts.setCustomer(customer);
	
	Bankdao bankdao = new Bankdao();
	bankdao.save(bankaccounts);
	
	List<Bankaccounts> list2 = list;   
	//list = savings----->we have copied that
	list2.add(bankaccounts); 		  //list2.+current = savings+current
	
	customer.setBankaccounts(list2);
	
	CustomerDao customerDao	= new CustomerDao();
	customerDao.update(customer);
	res.getWriter().print("<h1>Account is created successfully waiting for manager approval!</h1>");
	
	req.getRequestDispatcher("Admin.html").include(req, res);
	}
	else
	{
		res.getWriter().print("<h1>Account already existed...</h1>");
	}
	}
}
