package com.wheelEstate.service;

import java.util.List;

import com.wheelEstate.entity.Customer;
import com.wheelEstate.exceptions.LoginException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public interface CustomerService {
	void login(String username, String password) throws SomethingWentWrongException, LoginException;

	Customer addCustomer(Customer customer) throws SomethingWentWrongException;

	Customer updateCustomer(Customer customer) throws SomethingWentWrongException;

	void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException;

	List<Customer> getAllCustomers() throws SomethingWentWrongException;

	Customer getCustomerById(Long customerId) throws NoRecordFoundException;

	Customer getCustomerByUsername(String username) throws NoRecordFoundException;
}
