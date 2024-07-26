package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateCustomerRequest;
import com.travelstart.flightbooking.dto.UpdateCustomerRequest;
import com.travelstart.flightbooking.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    Customer createCustomer(CreateCustomerRequest createCustomerRequest);

    Customer updateCustomer(Long id, UpdateCustomerRequest updateCustomerRequest);

    void deleteCustomer(Long id);
}