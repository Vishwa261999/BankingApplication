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

@WebServlet("/Deposit")
public class Deposit extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	String amt = req.getParameter("amount");
	Double  amount = Double.parseDouble(amt);
	
	Long acno = (Long)req.getSession().getAttribute("acno");
	
	Bankdao bankdao = new Bankdao();
	Bankaccounts bankaccounts = bankdao.find(acno);
	
	bankaccounts.setAmount(bankaccounts.getAmount()+amount);  //5000+5000
	
	Bank_transaction bank_transaction = new Bank_transaction();
	
	bank_transaction.setDeposit(amount);
	bank_transaction.setWithdraw(0);
	bank_transaction.setBalance(bankaccounts.getAmount());
	bank_transaction.setDate_time(LocalDateTime.now());
	
	List<Bank_transaction> list = bankaccounts.getBank_transactions();
	list.add(bank_transaction);
	
	
	bankdao.update(bankaccounts);
	
	res.getWriter().print("<h1>Amount has been deposited successfully...</h1>");
	
	req.getRequestDispatcher("Accounthome.jsp").include(req, res);
	}
}
