package com.wheelEstate.service;

import java.util.List;
import com.wheelEstate.dao.CustomerDAO;
import com.wheelEstate.dao.CustomerDAOImpl;
import com.wheelEstate.entity.Customer;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer addCustomer(Customer customer) throws SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.addCustomer(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.updateCustomer(customer);
	}

	@Override
	public void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException {
		CustomerDAO cd = new CustomerDAOImpl();
		cd.deleteCustomer(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.getAllCustomers();
	}

	@Override
	public Customer getCustomerById(Long customerId) throws NoRecordFoundException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.getCustomerById(customerId);
	}

	@Override
	public Customer getCustomerByUsername(String username) throws NoRecordFoundException {
		CustomerDAO cd = new CustomerDAOImpl();
		return cd.getCustomerByUsername(username);
	}

}
