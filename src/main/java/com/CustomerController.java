package com;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handler.CustomerException;

@RestController
public class CustomerController {
	
	CustomerService service = new CustomerService();
	
	@RequestMapping("/")
	public ResponseEntity<String> customerName( ) {
		return new ResponseEntity<String>("Welcome", HttpStatus.OK);
	}
	
	@RequestMapping("customers/count")
	public ResponseEntity<String> customerCount( ) {
		return new ResponseEntity<String>("Customer Count = "+service.getCustomersCount(), HttpStatus.OK);
	}
	
	/**********************	POST	**************/
	@RequestMapping(value = "customers", method = RequestMethod.POST)
	public ResponseEntity<Void> addCustomer(@RequestBody Customer customer) {
		service.addCustomer(customer);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	/**********************	GET	**************/
	@RequestMapping(value = "customers/{id}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) {
		return new ResponseEntity<>(service.getCustomer(id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "customers", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> sortedCustomer = service.getAllCustomers().stream()
				.sorted(Comparator.comparing(Customer::getId, Comparator.naturalOrder()).thenComparing(Customer::getName))
				.collect(Collectors.toList());
		return new ResponseEntity<List<Customer>>(sortedCustomer, HttpStatus.OK);
	}
	
	/**********************	PUT	**************/
	@RequestMapping(value = "customers", method = RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<Void> modifyCustomer(@RequestParam("id") long id, @RequestParam("name") String name) throws CustomerException {
		Customer customer = service.getCustomer(id);
		if(customer == null) {
			throw new CustomerException("Invalid Input: Missing Entity", HttpStatus.BAD_REQUEST);
		}
		customer.setName(name);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	/**********************	DELETE	**************/
	@RequestMapping(value = "customers", method = RequestMethod.DELETE) // Using RequestParam
	public ResponseEntity<Void> removeCustomer(@RequestParam("id") long id) throws CustomerException {
		service.removeCustomer(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "customers/{id}", method = RequestMethod.DELETE) //Using PathVariable
	public ResponseEntity<Void> removeCustomerById(@PathVariable(value = "id") long id) throws CustomerException {
		service.removeCustomer(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "customers/delete", method = RequestMethod.DELETE) //Using RequestBody
	public ResponseEntity<Void> removeCustomer(@RequestBody Customer customer) throws CustomerException {
		service.removeCustomer(customer);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
