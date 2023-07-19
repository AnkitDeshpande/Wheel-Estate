package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.entity.Customer;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public Customer addCustomer(Customer customer) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerById(Long customerId) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerByUsername(String username) throws NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
