package com.travelstart.flightbooking.controller;


import com.travelstart.flightbooking.dto.CreateCustomerRequest;
import com.travelstart.flightbooking.dto.UpdateCustomerRequest;
import com.travelstart.flightbooking.model.Customer;
import com.travelstart.flightbooking.service.CustomerService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/customers")
@Api(value = "Customer Management System", tags = "Customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieve all customers.
     *
     * @return A list of all customers.
     */
    @ApiOperation(value = "Retrieve all customers", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list of customers"), @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Create a new customer.
     *
     * @param createCustomerRequest The customer object to create.
     * @return The created customer.
     */
    @ApiOperation(value = "Create a new customer", response = Customer.class)
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Customer created successfully"), @ApiResponse(code = 400, message = "Invalid input"), @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@ApiParam(value = "Customer object to create", required = true) @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    /**
     * Update an existing customer.
     *
     * @param id                    The ID of the customer to update.
     * @param updateCustomerRequest The customer object with updated details.
     * @return A response entity containing the updated customer details.
     */
    @ApiOperation(value = "Update an existing customer", response = Customer.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Customer updated successfully"), @ApiResponse(code = 404, message = "Customer not found"), @ApiResponse(code = 400, message = "Invalid input"), @ApiResponse(code = 500, message = "Internal server error")})
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@ApiParam(value = "ID of the customer to update", required = true) @PathVariable Long id, @ApiParam(value = "Updated customer object", required = true) @RequestBody UpdateCustomerRequest updateCustomerRequest) {

        Customer updatedCustomer = customerService.updateCustomer(id, updateCustomerRequest);
        return ResponseEntity.ok(updatedCustomer);
    }


    /**
     * Retrieve details for a specific customer by ID.
     *
     * @param id The ID of the customer to retrieve.
     * @return A response entity containing the customer details.
     */
    @ApiOperation(value = "Retrieve details for a specific customer by ID", response = Customer.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Customer found"), @ApiResponse(code = 404, message = "Customer not found"), @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@ApiParam(value = "ID of the customer to retrieve", required = true) @PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);

        return ResponseEntity.ok(customer);
    }


    /**
     * Delete a customer by ID.
     *
     * @param id The ID of the customer to delete.
     * @return A response entity with no content status.
     */
    @ApiOperation(value = "Delete a customer")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Customer deleted successfully"), @ApiResponse(code = 404, message = "Customer not found"), @ApiResponse(code = 500, message = "Internal server error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@ApiParam(value = "ID of the customer to delete", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}
