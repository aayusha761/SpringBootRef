package com.lti.repostitory;

import java.util.List;

import com.lti.entity.Customer;

public interface CustomerRepository {

	void save(Customer customer);

	Customer findBy(int id);

	List<Customer> findAll();

	int findByUsernamePassword(String username, String password);

	boolean exists(String username);

}