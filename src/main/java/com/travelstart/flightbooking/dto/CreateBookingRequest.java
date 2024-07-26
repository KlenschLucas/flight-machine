package com.travelstart.flightbooking.dto;

public record CreateBookingRequest(
        String flightNumber,
        Long customerId,
        Integer numberOfSeatsBooked
) {
}
