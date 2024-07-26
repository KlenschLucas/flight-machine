package com.travelstart.flightbooking.controller;

import com.travelstart.flightbooking.dto.CreateFlightRequest;
import com.travelstart.flightbooking.dto.UpdateFlightRequest;
import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.service.FlightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/flights")
@Api(value = "Flight Management System", tags = "Flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @ApiOperation(value = "Retrieve all available flights", response = List.class)
    @GetMapping
    public List<Flight> getAllFlights(@ApiParam(value = "Origin of the flight") @RequestParam(required = false) Optional<String> origin, @ApiParam(value = "Destination of the flight") @RequestParam(required = false) Optional<String> destination, @ApiParam(value = "Flight number") @RequestParam(required = false) Optional<String> flightNumber) {
        return flightService.getAllFlights(origin, destination, flightNumber);
    }

    @ApiOperation(value = "Retrieve details for a specific flight by ID", response = Flight.class)
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@ApiParam(value = "ID of the flight to retrieve", required = true) @PathVariable Long id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    @ApiOperation(value = "Create a new flight", response = Flight.class)
    @PostMapping
    public Flight createFlight(@ApiParam(value = "Flight object to create", required = true) @Valid @RequestBody CreateFlightRequest createFlightRequest) {
        return flightService.createFlight(createFlightRequest);
    }

    @ApiOperation(value = "Update an existing flight", response = Flight.class)
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@ApiParam(value = "ID of the flight to update", required = true) @PathVariable Long id, @ApiParam(value = "Updated flight object", required = true) @Valid @RequestBody UpdateFlightRequest updateFlightRequest) {
        Flight updatedFlight = flightService.updateFlight(id, updateFlightRequest);
        return ResponseEntity.ok(updatedFlight);
    }

    @ApiOperation(value = "Delete a flight")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@ApiParam(value = "ID of the flight to delete", required = true) @PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}