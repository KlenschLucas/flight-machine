package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateBookingRequest;
import com.travelstart.flightbooking.dto.UpdateBookingRequest;
import com.travelstart.flightbooking.exception.BookingNotFoundException;
import com.travelstart.flightbooking.exception.CustomerNotFoundException;
import com.travelstart.flightbooking.exception.FlightNotFoundException;
import com.travelstart.flightbooking.exception.NoSeatsAvailableOnFlightException;
import com.travelstart.flightbooking.model.Booking;
import com.travelstart.flightbooking.model.Customer;
import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.repository.BookingRepository;
import com.travelstart.flightbooking.repository.CustomerRepository;
import com.travelstart.flightbooking.repository.FlightRepository;
import com.travelstart.flightbooking.specification.BookingSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final CustomerRepository customerRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, FlightRepository flightRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.flightRepository = flightRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Booking> getAllBookings(Optional<String> customerName, Optional<String> bookingDate) {
        return bookingRepository.findAll(new BookingSpecification(customerName, bookingDate));
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking with id " + id + " not found"));
    }

    @Override
    public synchronized Booking createBooking(CreateBookingRequest bookingRequest) {

        Flight flight = flightRepository.findByFlightNumber(bookingRequest.flightNumber());

        if (flight == null) {
            throw new FlightNotFoundException("Flight with flightName " + bookingRequest.flightNumber() + " not found");
        }

        Customer customer = customerRepository.findById(bookingRequest.customerId()).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + bookingRequest.customerId() + " not found"));

        log.info("Checking if there are enough seats available for new booking");
        List<Booking> previousBookings = bookingRepository.findByFlight(flight);
        int previousSeats = previousBookings.stream().mapToInt(Booking::getNumberOfSeatsBooked).sum();

        if (flight.getAvailableSeats() - previousSeats > bookingRequest.numberOfSeatsBooked()) {
            log.error("Not enough seats available for new booking");
            throw new NoSeatsAvailableOnFlightException("Not enough seats available");
        }

        Booking booking = new Booking();

        booking.setFlight(flight);
        booking.setNumberOfSeatsBooked(bookingRequest.numberOfSeatsBooked());
        booking.setCustomer(customer);
        // todo: validate date format - assume correct for now
        booking.setBookingDate(LocalDateTime.now().toLocalDate().atStartOfDay());

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, UpdateBookingRequest bookingRequest) {
        Booking existingBooking = this.bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("Booking with id " + id + " not found"));

        // TODO: validate if flight exists
        Flight flight = flightRepository.findByFlightNumber(bookingRequest.flightNumber());
        if (flight == null) {
            throw new FlightNotFoundException("Flight with flightName " + bookingRequest.flightNumber() + " not found");
        }

        // find the customer doing the booking
        Customer customer = customerRepository.findById(bookingRequest.customerId()).orElseThrow(() -> new CustomerNotFoundException("Customer with id " + bookingRequest.customerId() + " not found"));
        existingBooking.setCustomer(customer);

        // check if there are enough seats available
        log.info("Checking if there are enough seats available on update of booking");
        List<Booking> previousBookings = bookingRepository.findByFlight(flight);
        int previousSeats = previousBookings.stream().mapToInt(Booking::getNumberOfSeatsBooked).sum();

        if (flight.getAvailableSeats() - previousSeats + existingBooking.getNumberOfSeatsBooked() > bookingRequest.numberOfSeatsBooked()) {
            log.error("Not enough seats available for update of booking");
            throw new NoSeatsAvailableOnFlightException("Not enough seats available");
        }

        return bookingRepository.save(existingBooking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}