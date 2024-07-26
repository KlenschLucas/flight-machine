package com.travelstart.flightbooking.repository;

import com.travelstart.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Flight findByFlightNumber(String flightNumber);
}
