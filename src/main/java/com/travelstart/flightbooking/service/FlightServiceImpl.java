package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateFlightRequest;
import com.travelstart.flightbooking.dto.UpdateFlightRequest;
import com.travelstart.flightbooking.exception.FlightNotFoundException;
import com.travelstart.flightbooking.exception.FlightNumberNotUniqueException;
import com.travelstart.flightbooking.exception.NoSeatsAvailableOnFlightException;
import com.travelstart.flightbooking.model.Booking;
import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.repository.BookingRepository;
import com.travelstart.flightbooking.repository.FlightRepository;
import com.travelstart.flightbooking.specification.FlightSpecification;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;


    public FlightServiceImpl(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Flight> getAllFlights(Optional<String> origin, Optional<String> destination, Optional<String> flightNumber) {
        return flightRepository.findAll(new FlightSpecification(flightNumber, origin, destination));
    }

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("Flight with id " + id + " not found"));
    }

    @Override
    public Flight createFlight(CreateFlightRequest request) {
        Flight existingFlight = flightRepository.findByFlightNumber(request.flightNumber());

        if (existingFlight != null) {
            throw new FlightNumberNotUniqueException("Flight with flight number " + request.flightNumber() + " already exists");
        }

        Flight flight = new Flight();

        flight.setFlightNumber(request.flightNumber());
        flight.setAvailableSeats(request.availableSeats());
        flight.setOrigin(request.origin());
        flight.setDestination(request.destination());
        flight.setPrice(request.price());
        flight.setDepartureDateTime(request.departureDateTime());
        flight.setArrivalDateTime(request.arrivalDateTime());

        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Long id, UpdateFlightRequest request) {
        Flight flight = this.getFlightById(id);


        List<Booking> currentBookings = bookingRepository.findByFlight(flight);

        int currentSeatsBookedOnFlight = currentBookings.stream().mapToInt(Booking::getNumberOfSeatsBooked).sum();

        if (request.availableSeats() < currentSeatsBookedOnFlight) {
            throw new NoSeatsAvailableOnFlightException("Available seats cannot be less than current bookings");
        }

        flight.setFlightNumber(request.flightNumber());
        flight.setAvailableSeats(request.availableSeats());
        flight.setOrigin(request.origin());
        flight.setDestination(request.destination());
        flight.setPrice(request.price());
        flight.setDepartureDateTime(request.departureDateTime());
        flight.setArrivalDateTime(request.arrivalDateTime());

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
