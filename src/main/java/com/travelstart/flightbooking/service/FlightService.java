package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateFlightRequest;
import com.travelstart.flightbooking.dto.UpdateFlightRequest;
import com.travelstart.flightbooking.exception.FlightNotFoundException;
import com.travelstart.flightbooking.exception.NoSeatsAvailableOnFlightException;
import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.repository.BookingRepository;
import com.travelstart.flightbooking.repository.FlightRepository;
import com.travelstart.flightbooking.specification.BookingSpecification;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

    List<Flight> getAllFlights();

    Flight getFlightById(Long id);

    Flight createFlight(CreateFlightRequest request);

    Flight updateFlight(Long id, UpdateFlightRequest request);
    
    void deleteFlight(Long id);
}
