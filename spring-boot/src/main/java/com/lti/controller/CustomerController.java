package com.lti.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lti.controller.CustomerController.Status.StatusType;
import com.lti.dto.CustomerDto;
import com.lti.dto.LoginDto;
import com.lti.entity.Customer;
import com.lti.exception.CustomerServiceException;
import com.lti.service.CustomerService;

@RestController
@CrossOrigin
public class CustomerController {

	@Autowired
	private CustomerService custService;

//	@RequestMapping
//	@PostMapping(path = "/register", consumes = "multipart/form-data")
//	public Status register(@ResquestBody CustomerDto customerDto) {
//		try {
//			Customer customer = new Customer();
//			BeanUtils.copyProperties(customerDto, customer);
//			
//			String ROOT_DIR = "/Users/aayusharora";
//			String fileName = customerDto.getProfilePic().getOriginalFilename();
//			String targetFile = ROOT_DIR + fileName;
//			try {
//				FileCopyUtils.copy(customerDto.getProfilePic().getInputStream(), new FileOutputStream(targetFile));
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			custService.register(customer);
//			
//			Status status = new Status();
//			status.setType(StatusType.SUCCESS);
//			status.setMsg("Registration success");
//			return status;
//		} catch (CustomerServiceException e) {
//			// TODO: handle exception
//			Status status = new Status();
//			status.setType(StatusType.FAILURE);
//			status.setMsg(e.getMessage());
//			return status;
//		}
//	}
	
	@PostMapping(path = "/register", consumes = "multipart/form-data")
	public Status register(@RequestParam("name") String name, @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("email") String email, @RequestParam("password") String password, @RequestBody() MultipartFile profilePic) {
		try {
			Customer customer = new Customer();
			CustomerDto customerDto = new CustomerDto();
			customerDto.setName(name);
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			customerDto.setDateOfBirth(LocalDate.parse(dateOfBirth, formatter));
			customerDto.setPassword(password);
			customerDto.setProfilePic(profilePic);
			
			BeanUtils.copyProperties(customerDto, customer);
			
			String ROOT_DIR = "/Users/aayusharora";
			String fileName = customerDto.getProfilePic().getOriginalFilename();
			String targetFile = ROOT_DIR + fileName;
			try {
				FileCopyUtils.copy(customerDto.getProfilePic().getInputStream(), new FileOutputStream(targetFile));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			custService.register(customer);
			
			Status status = new Status();
			status.setType(StatusType.SUCCESS);
			status.setMsg("Registration success");
			return status;
		} catch (CustomerServiceException e) {
			// TODO: handle exception
			Status status = new Status();
			status.setType(StatusType.FAILURE);
			status.setMsg(e.getMessage());
			return status;
		}
	}

	@PostMapping("/login")
	public LoginStatus login(@RequestBody LoginDto loginDto) {
		System.out.println(loginDto.getEmail());
		System.out.println(loginDto.getPassword());
		try {
			Customer customer = custService.login(loginDto.getEmail(), loginDto.getPassword());
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setType(StatusType.SUCCESS);
			loginStatus.setMsg("Login Successful!");
			loginStatus.setCustomerId(customer.getId());
			loginStatus.setName(customer.getName());
			return loginStatus;
		} catch(CustomerServiceException e) {
			LoginStatus loginStatus = new LoginStatus();
			loginStatus.setType(StatusType.FAILURE);
			loginStatus.setMsg(e.getMessage());
			return loginStatus;
		}
	}

	public static class Status {
		private StatusType type;
		private String msg;

		public static enum StatusType {
			SUCCESS, FAILURE
		}

		public StatusType getType() {
			return type;
		}

		public void setType(StatusType type) {
			this.type = type;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	public static class LoginStatus extends Status {
		private int customerId;
		private String name;

		public int getCustomerId() {
			return customerId;
		}

		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

}