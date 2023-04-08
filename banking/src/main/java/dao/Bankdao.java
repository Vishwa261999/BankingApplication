package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Bankaccounts;

public class Bankdao {
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("dev");
	EntityManager entityManager = entityManagerFactory.createEntityManager();  //it is nothing but pre requisite
	EntityTransaction entityTransaction = entityManager.getTransaction();   
	
	public void save(Bankaccounts bankaccounts) {
		entityTransaction.begin();					// it will make the data ready
		entityManager.persist(bankaccounts);		// it will helps to transfer or traverse the data towards database	
		entityTransaction.commit();					// it will help to commit the data or to store or to insert the data in the database
	}

	
	public List<Bankaccounts> fetchall() {
		return entityManager.createQuery("select x from Bankaccounts x").getResultList();
	}
	
	
	public Bankaccounts find(long acon) {
		return entityManager.find(Bankaccounts.class, acon);
	}


	public void update(Bankaccounts bankaccounts) {
		entityTransaction.begin();
		entityManager.merge(bankaccounts);			
		entityTransaction.commit();	
	}
}
