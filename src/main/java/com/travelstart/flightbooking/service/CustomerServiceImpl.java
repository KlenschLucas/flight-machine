package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateCustomerRequest;
import com.travelstart.flightbooking.dto.UpdateCustomerRequest;
import com.travelstart.flightbooking.exception.CustomerNotFoundException;
import com.travelstart.flightbooking.model.Customer;
import com.travelstart.flightbooking.repository.BookingRepository;
import com.travelstart.flightbooking.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * Retrieve all customers.
     *
     * @return A list of all customers.
     */
    @Override
    public List<Customer> getAllCustomers() {
        logger.info("Retrieving all customers");
        return customerRepository.findAll();
    }

    /**
     * Retrieve a customer by ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return An Optional containing the customer if found.
     */
    @Override
    public Customer getCustomerById(Long id) {
        logger.info("Retrieving customer with id: {}", id);

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            logger.error("Customer with id {} not found", id);
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        return customerOptional.get();
    }

    /**
     * Create a new customer.
     *
     * @param createCustomerRequest The customer object to create.
     * @return The created customer.
     */
    @Override
    public Customer createCustomer(CreateCustomerRequest createCustomerRequest) {
        logger.info("Creating new customer request: {}", createCustomerRequest);

        Customer newCustomer = new Customer();

        newCustomer.setName(createCustomerRequest.name());
        newCustomer.setEmail(createCustomerRequest.email());
        newCustomer.setPhoneNumber(createCustomerRequest.phoneNumber());

        return customerRepository.save(newCustomer);
    }

    /**
     * Update an existing customer.
     *
     * @param id       The ID of the customer to update.
     * @param updateCustomerRequest The customer object with updated details.
     * @return The updated customer.
     */
    @Override
    public Customer updateCustomer(Long id, UpdateCustomerRequest updateCustomerRequest) {
        logger.info("Updating customer with id: {}", id);

        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);

        if (existingCustomerOptional.isEmpty()) {
            logger.error("Customer with id {} not found", id);
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        Customer updatedCustomer = existingCustomerOptional.get();

        updatedCustomer.setName(updateCustomerRequest.name());
        updatedCustomer.setEmail(updateCustomerRequest.email());
        updatedCustomer.setPhoneNumber(updateCustomerRequest.phoneNumber());

        updatedCustomer = customerRepository.save(updatedCustomer);

        logger.info("Customer with id {} updated successfully", id);

        return updatedCustomer;
    }

    /**
     * Delete a customer by ID.
     *
     * @param id The ID of the customer to delete.
     */
    @Transactional
    @Override
    public void deleteCustomer(Long id) {
        logger.info("Deleting customer with id: {}", id);

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            logger.error("Customer with id {} not found", id);
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }

        customerRepository.deleteById(id);
        bookingRepository.deleteByCustomer(customer.get());
        logger.info("Customer with id {} deleted successfully", id);
    }
}