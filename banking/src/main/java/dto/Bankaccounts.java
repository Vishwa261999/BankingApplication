package dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Bankaccounts {
	@Id
	@GeneratedValue(generator="acc_no")
	@SequenceGenerator(initialValue=45678123,allocationSize=1,sequenceName="acc_no",name="acc_no")
	long acc_no;           //it will generated automatically
	String	bank_type;
	double amount;
	double aclimit;
	boolean status;
	
	@ManyToOne
	Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL)			 // we can easily persist or save the code in a single line
	List<Bank_transaction> bank_transactions;

	public long getAcc_no() {
		return acc_no;
	}

	public void setAcc_no(long acc_no) {
		this.acc_no = acc_no;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAclimit() {
		return aclimit;
	}

	public void setAclimit(double aclimit) {
		this.aclimit = aclimit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Bank_transaction> getBank_transactions() {
		return bank_transactions;
	}

	public void setBank_transactions(List<Bank_transaction> bank_transactions) {
		this.bank_transactions = bank_transactions;
	}
}
