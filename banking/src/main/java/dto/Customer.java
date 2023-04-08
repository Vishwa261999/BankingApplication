package dto;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {

	@Id
	@SequenceGenerator(initialValue = 1223344, allocationSize = 1, sequenceName = "cust_id", name = "cust_id")
	@GeneratedValue(generator = "cust_id")
	int cust_id; // it will be generated automatically or randomlly
	String name;
	long mobile;
	String email;
	String gender;
	String password;
	Date date;

	@OneToMany
	List<Bankaccounts> bankaccounts;

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Bankaccounts> getBankaccounts() {
		return bankaccounts;
	}

	public void setBankaccounts(List<Bankaccounts> bankaccounts) {
		this.bankaccounts = bankaccounts;
	}
}
