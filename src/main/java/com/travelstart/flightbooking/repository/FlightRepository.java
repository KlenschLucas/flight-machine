package com.travelstart.flightbooking.repository;

import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.specification.FlightSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {
    Flight findByFlightNumber(String flightNumber);
}
