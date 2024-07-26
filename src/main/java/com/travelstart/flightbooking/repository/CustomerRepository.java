package com.travelstart.flightbooking.repository;

import com.travelstart.flightbooking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
