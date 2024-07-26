package com.travelstart.flightbooking.dto;

public record UpdateBookingRequest(
        Long customerId,
        String flightNumber,
        int numberOfSeatsBooked
) {
}
