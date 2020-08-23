package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.repostitory.CustomerRepository;

//@Component
@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Override
	public void register(Customer customer) {
		if(!custRepo.exists(customer.getEmail()))
			custRepo.save(customer);
		else
			throw new CustomerServiceException("Customer Already Registered");
	}
	
	@Override
	public Customer login(String email, String password) {
		try {
			if (!custRepo.exists(email)) {
				throw new CustomerServiceException("Not registered");
			}
			
			int id = custRepo.findByUsernamePassword(email, password);
			Customer cust = custRepo.findBy(id);
			return cust;
		} catch (EmptyResultDataAccessException e) {
			throw new CustomerServiceException("Incorrect username/password");
		}
	}
}
