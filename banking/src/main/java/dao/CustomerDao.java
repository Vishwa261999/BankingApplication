package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Bankaccounts;
import dto.Customer;

public class CustomerDao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();
	
	
	public List<Customer> check(long mobile) {
		return entityManager.createQuery("select x from Customer x where mobile=?1").setParameter(1,mobile).getResultList();
	}
	
	public List<Customer> check(String email) {
		return entityManager.createQuery("select x from Customer x where email=?1").setParameter(1,email).getResultList();
	}

	public void save(Customer customer) 
	{
		entityTransaction.begin();
		entityManager.persist(customer);
		entityTransaction.commit();
	}

	public Customer login(int cust_id) {
		return entityManager.find(Customer.class, cust_id);
	}

	public void update(Customer customer) {
		entityTransaction.begin();
		entityManager.merge(customer);
		entityTransaction.commit();
	}

	public List<Bankaccounts> fetchall() {
		return entityManager.createQuery("select x from Bankaccounts x").getResultList();
	}
	
}
