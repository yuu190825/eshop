package main.service;

import main.model.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Customer;
import main.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(long customerId) {
		return customerRepository.findById(customerId).get();
	}

	/**
	 *create time＆update time logic set here
	 */
	@Override
	public void saveOrUpdate(Customer customer) {
		Date setDate = new Date();
		if (customer.getCustomerId()!=0) {
			customer.setUpdateDate(setDate);
			//customer.getUser().setUpdateTime(setDate);
		} else {
			customer.setCreateDate(setDate);
		}
		customerRepository.save(customer);
	}

//	@Override
//	public Customer getByIdWithComments(long customerId) {
//		return customerRepository.getByIdWithComments(customerId);
//	}

//	@Override
//	public void addUserToCustomer(long customerId, long userId) {
//		Customer customer = getById(customerId);
//		if(customer.getUser() != null) {
//			User user = userRepository.findByLogin(login);
//			customer.setUser(user);
//			customerRepository.save(customer);
//		}
//
//	}

	@Override
	public void delete(long customerId) {
		customerRepository.deleteById(customerId);
	}

//	@Override
//	public void addUserToCustomer(long customerId, long userId) {
//		Customer customer = getById(customerId);
//		if(customer.getUsers() == null) {
//			customer.setUsers(new ArrayList<>());
//		}
//		User user = userRepository.getOne(userId);
//		if(user != null) {
//			customer.getUsers().add(user);
//			saveOrUpdate(customer);
//		}
//	}

	@Override
	public void addUserToCustomer(long customerId, String login) {
		Customer customer = getById(customerId);
		if(customer.getUsers() == null) {
			customer.setUsers(new ArrayList<>());
		}
		User user = userRepository.findByLogin(login);
		if(user != null) {
			customer.getUsers().add(user);
			saveOrUpdate(customer);
		}
	}

	// @Override
	// public long findByUser(long user) {
	// List<Customer> customers = customerRepository.getByUserId(user);
	// return customers.getCustomers();
	// }

	@Override
	public Customer getByUserId(long userId) {
		List<Customer> customers = customerRepository.getByUserId(userId);
		if (!customers.isEmpty()) {
			return customers.get(0);
		}
		return null;
	}

	@Override
	public List<Customer> listAll(String keyword) {
		if (keyword != null) {
			return customerRepository.search(keyword);
		}
		return customerRepository.findAll();
	}

	@Override
	public boolean companyNameExists(String companyName) {
		return customerRepository.existsByCompanyName(companyName);
	}

	@Override
	public boolean contactNameExists(String contactName) {
		return customerRepository.existsByContactName(contactName);
	}

	@Override
	public boolean phonenumberExists(String phonenumber) {
		return customerRepository.existsByPhonenumber(phonenumber);
	}

}
