package com.lti;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.lti.entity.Customer;
import com.lti.repostitory.CustomerRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class CustomerTests {

	@Autowired
	private CustomerRepository custRepo;
	
	@Test
	void add() {
		Customer c = new Customer();
//		c.setId(2);
		c.setName("Aayush");
		c.setPassword("aayush");
		c.setDateOfBirth(LocalDate.of(1998, 11, 06));
		c.setEmail("aayush@lti");
		
		
		
		custRepo.save(c);
	}
	
	@Test
	void fetchByEmailAndPassword() {
		
		int id = custRepo.findByUsernamePassword("aayush@lti", "aayush");
		System.out.println(id);
		
	}

}