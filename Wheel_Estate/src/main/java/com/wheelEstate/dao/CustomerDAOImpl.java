package com.wheelEstate.dao;

import java.util.List;

import com.wheelEstate.Utility.Util;
import com.wheelEstate.entity.Car;
import com.wheelEstate.entity.Customer;
import com.wheelEstate.exceptions.LoginException;
import com.wheelEstate.exceptions.NoRecordFoundException;
import com.wheelEstate.exceptions.SomethingWentWrongException;
import com.wheelEstate.ui.Runner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

/**
 * This class implements the CustomerDAO interface and provides methods to
 * interact with the database to perform CRUD operations for Customer entities.
 */
public class CustomerDAOImpl implements CustomerDAO { // this also done

	/**
	 * Adds a new Customer entity to the database.
	 *
	 * @param customer The Customer entity to be added.
	 * @return The Customer entity that was added to the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public Customer addCustomer(Customer customer) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			et.begin();
			em.persist(customer);
			et.commit();
			return customer;
		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Updates the details of an existing Customer entity in the database.
	 *
	 * @param customer The Customer entity containing updated details.
	 * @return The updated Customer entity.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public Customer updateCustomer(Customer customer) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			et.begin();
			Customer c1 = em.find(Customer.class, customer.getCustomerId());
			if (c1 != null) {
				em.merge(customer);
				et.commit();
				return customer;
			} else {
				throw new NoRecordFoundException("No customer found with the given ID :" + customer.getCustomerId());
			}

		} catch (Exception e) {
			et.rollback();
			e.printStackTrace();
			throw new SomethingWentWrongException("Something went wrong while Updating details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Deletes a Customer entity from the database based on the given customerId.
	 *
	 * @param customerId The ID of the Customer entity to be deleted.
	 * @throws NoRecordFoundException      If no Customer entity is found with the
	 *                                     given customerId in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public void deleteCustomer(Long customerId) throws NoRecordFoundException, SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Customer customer = em.find(Customer.class, customerId);
			et.begin();
			if (customer != null) {

				customer.setDeleted(true);
				em.merge(customer);
				et.commit();
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + customerId);
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}

	}

	/**
	 * Retrieves a list of all Customer entities from the database.
	 *
	 * @return A list of all Customer entities in the database.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 */
	@Override
	public List<Customer> getAllCustomers() throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Query query = em.createQuery("SELECT c FROM Customer c");
			et.begin();
			List<Customer> list = (List<Customer>) query.getResultList();
			if (list != null) {
				et.commit();
				return list;
			} else {
				throw new SomethingWentWrongException("List is empty.");
			}

		} catch (Exception e) {
			et.rollback();
			throw new SomethingWentWrongException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Retrieves a specific Customer entity from the database based on the given
	 * customerId.
	 *
	 * @param customerId The ID of the Customer entity to be retrieved.
	 * @return The Customer entity with the specified customerId.
	 * @throws NoRecordFoundException If no Customer entity is found with the given
	 *                                customerId in the database.
	 */
	@Override
	public Customer getCustomerById(Long customerId) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();
			Customer customer = em.find(Customer.class, customerId);
			et.begin();
			if (customer != null) {
				et.commit();
				return customer;
			} else {
				throw new NoRecordFoundException("No record found with the given ID : " + customerId);
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while adding Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Retrieves a specific Customer entity from the database based on the given
	 * username.
	 *
	 * @param username The username of the Customer entity to be retrieved.
	 * @return The Customer entity with the specified username.
	 * @throws NoRecordFoundException If no Customer entity is found with the given
	 *                                username in the database.
	 */
	@Override
	public Customer getCustomerByUsername(String username) throws NoRecordFoundException {
		EntityManager em = null;
		EntityTransaction et = null;

		try {
			em = Util.getEm();
			et = em.getTransaction();

			String jpql = "SELECT c FROM Customer c WHERE c.username = :username";
			Query query = em.createQuery(jpql);
			query.setParameter("username", username);

			Customer customer = (Customer) query.getSingleResult();

			et.begin();
			if (customer != null) {
				et.commit();
				return customer;
			} else {
				throw new NoRecordFoundException("No record found with the given username: " + username);
			}

		} catch (Exception e) {
			et.rollback();
			throw new NoRecordFoundException("Something went wrong while Getting Details. Try again later.");
		} finally {
			em.close();
		}
	}

	/**
	 * Validates the login credentials for a Customer entity in the database.
	 *
	 * @param username The username of the Customer trying to log in.
	 * @param password The password of the Customer trying to log in.
	 * @throws SomethingWentWrongException If something unexpected happens during
	 *                                     the database operation.
	 * @throws LoginException              If the provided username or password is
	 *                                     incorrect.
	 */
	@Override
	public void login(String username, String password) throws SomethingWentWrongException, LoginException {
		EntityManager em = null;

		try {
			em = Util.getEm();

			// Retrieve the customer by username from the database
			Query query = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username");
			query.setParameter("username", username);
			Customer customer = (Customer) query.getSingleResult();

			// Check if the provided password matches the customer's password
			if (customer != null && customer.getPassword().equals(password)) {
				// Perform any additional login-related actions here
				System.out.println("Login successful!");
			} else {
				// Invalid credentials
				System.out.println("Invalid username or password. Please try again.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoginException("No user found with the given username. Please try again.");
		} finally {
			em.close();
		}
	}

}
