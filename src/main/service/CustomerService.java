package main.service;

import main.model.Customer;

import java.util.List;

public interface CustomerService {

	public List<Customer> getAll();

	public Customer getById(long customerId);

	public void saveOrUpdate(Customer customer);

	public void delete(long customerId);

	//public Customer getByIdWithComments(long customerId);

	public void addUserToCustomer(long customerId, String login);

	//public long findByUser(long user);

	public Customer getByUserId(long userId);

	//public void addUserToCustomer(long customerId, long userId);

	public List<Customer> listAll(String keyword);

	public boolean companyNameExists(String companyName);

	public boolean contactNameExists(String contactName);

	public boolean phonenumberExists(String phonenumber);

}
