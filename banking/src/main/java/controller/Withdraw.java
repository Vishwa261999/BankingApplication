package controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Bankdao;
import dto.Bank_transaction;
import dto.Bankaccounts;

@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String amt = req.getParameter("amount");
		Double  amount = Double.parseDouble(amt);
		
		Long acno = (Long)req.getSession().getAttribute("acno");
		
		Bankdao bankdao = new Bankdao();
		Bankaccounts bankaccounts = bankdao.find(acno);
		
		if(amount>bankaccounts.getAmount())
		{
			res.getWriter().print("<h1>Insufficient Balance</h1>");
		}
		else
		{
			if(amount>bankaccounts.getAclimit())
			{
				res.getWriter().print("<h1>The amount you have entered is more" +" "+ "than your account limit:your account limit is"+bankaccounts.getAclimit()+"</h1>");
			}
			
		else{
		bankaccounts.setAmount(bankaccounts.getAmount()-amount);  
		
		Bank_transaction bank_transaction = new Bank_transaction();
		
		bank_transaction.setDeposit(0);
		bank_transaction.setWithdraw(amount);
		bank_transaction.setBalance(bankaccounts.getAmount());
		bank_transaction.setDate_time(LocalDateTime.now());
		
		List<Bank_transaction> list = bankaccounts.getBank_transactions();
		list.add(bank_transaction);
		
		bankdao.update(bankaccounts);
		
		res.getWriter().print("<h1>Amount has been Withdrawn successfully...</h1>");
		req.getRequestDispatcher("Accounthome.jsp").include(req, res);
	}
  }
 }
}
