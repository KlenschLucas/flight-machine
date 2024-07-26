package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateFlightRequest;
import com.travelstart.flightbooking.dto.UpdateFlightRequest;
import com.travelstart.flightbooking.model.Flight;

import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<Flight> getAllFlights(Optional<String> origin, Optional<String> destination, Optional<String> flightNumber);

    Flight getFlightById(Long id);

    Flight createFlight(CreateFlightRequest request);

    Flight updateFlight(Long id, UpdateFlightRequest request);

    void deleteFlight(Long id);
}
