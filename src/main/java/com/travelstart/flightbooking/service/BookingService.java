package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.dto.CreateBookingRequest;
import com.travelstart.flightbooking.dto.UpdateBookingRequest;
import com.travelstart.flightbooking.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    List<Booking> getAllBookings(Optional<String> customerName, Optional<String> bookingDate);

    Booking getBookingById(Long bookingId);

    Booking createBooking(CreateBookingRequest bookingRequest);

    Booking updateBooking(Long id, UpdateBookingRequest bookingRequest);

    void deleteBooking(Long id);
}
