package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

import com.handler.CustomerException;

public class CustomerService {

	Map<Long, Customer> data = CustomerData.customers;
	
	public void addCustomer(Customer customer) {
		customer.setId(data.size()+1);
		data.put(customer.getId(), customer);
	}
	
	public Customer getCustomer(long id) {
		return data.get(id); //return data.values().stream().filter(emp -> id == emp.getId()).collect(Collectors.toList());
	}
	
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(data.values());
	}
	
	public void removeCustomer(long id) throws CustomerException {
		Customer removeCustomer = data.get(id);
		if(removeCustomer == null) {
			throw new CustomerException("Missing data", HttpStatus.NOT_MODIFIED);
		}
		data.remove(id);
	}
	
	public void removeCustomer(Customer customer) throws CustomerException {
		Customer removeCustomer = data.get(customer.getId());
		if(removeCustomer == null) {
			throw new CustomerException("Missing data", HttpStatus.NOT_MODIFIED);
		}
		data.remove(customer.getId());
	}
	
	public int getCustomersCount() {
		return CustomerData.customers.size();
	}
}
