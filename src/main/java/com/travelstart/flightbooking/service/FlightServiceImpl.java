package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateFlightRequest;
import com.travelstart.flightbooking.dto.UpdateFlightRequest;
import com.travelstart.flightbooking.exception.FlightNotFoundException;
import com.travelstart.flightbooking.exception.NoSeatsAvailableOnFlightException;
import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.repository.BookingRepository;
import com.travelstart.flightbooking.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;


    public FlightServiceImpl(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight with id " + id + " not found"));
    }

    @Override
    public Flight createFlight(CreateFlightRequest request) {
        flightRepository.findByFlightNumber(request.flightNumber());

        Flight flight = new Flight();

        flight.setFlightNumber(request.flightNumber());
        flight.setAvailableSeats(request.availableSeats());


        // TODO: maybe look at better way to parse date time and handle exceptions
        try {
            LocalDateTime departureDateTime = LocalDateTime.parse(request.departureDateTime());
            flight.setDepartureDateTime(departureDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid departure date time format");
        }

        try {
            LocalDateTime arrivalDateTime = LocalDateTime.parse(request.arrivalDateTime());
            flight.setArrivalDateTime(arrivalDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid arrival date time format");
        }

        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, UpdateFlightRequest request) {
        Flight flight = this.getFlightById(id);

        flight.setFlightNumber(request.flightNumber());
        flight.setAvailableSeats(request.availableSeats());

        long currentBookings = bookingRepository.countByFlight(flight);

        if (request.availableSeats() < currentBookings) {
            throw new NoSeatsAvailableOnFlightException("Available seats cannot be less than current bookings");
        }

        // TODO: maybe look at better way to parse date time and handle exceptions

        try {
            LocalDateTime departureDateTime = LocalDateTime.parse(request.departureDateTime());
            flight.setDepartureDateTime(departureDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid departure date time format");
        }

        try {
            LocalDateTime arrivalDateTime = LocalDateTime.parse(request.arrivalDateTime());
            flight.setArrivalDateTime(arrivalDateTime);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid arrival date time format");
        }

        return flightRepository.save(flight);
    }

    @Transactional
    @Override
    public void deleteFlight(Long id) {
        Flight flight = this.getFlightById(id);

        bookingRepository.deleteByFlight(flight);
        flightRepository.delete(flight);
    }
}
