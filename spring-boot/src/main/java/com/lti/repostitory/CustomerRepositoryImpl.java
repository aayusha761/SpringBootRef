package com.lti.repostitory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Customer;

@Repository()
public class CustomerRepositoryImpl implements CustomerRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public void save(Customer customer) {
		entityManager.persist(customer);
	}
	
	@Override
	public Customer findBy(int id) {
		return entityManager.find(Customer.class, id);
	}
	
	@Override
	public List<Customer> findAll() {
		return entityManager.createNamedQuery("fetch-all").getResultList();
	}
	
	@Override
	public int findByUsernamePassword(String username, String password) {
		return (Integer) entityManager.createQuery("select c.id from Customer c where c.email = :email AND c.password = :password").
				setParameter("email", username)
				.setParameter("password", password).getSingleResult();
	}
	
	@Override
	public boolean exists(String username) {
		return (Long) entityManager.createQuery("select count(c.id) from Customer c where c.email = :email")
				.setParameter("email", username)
				.getSingleResult() == 1 ? true : false;
	}
}
