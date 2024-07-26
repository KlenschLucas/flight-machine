package com.travelstart.flightbooking.dto;

public record CreateFlightRequest(
        String flightNumber,
        Long customerId,
        String origin,
        String destination,
        String departureDateTime,
        String arrivalDateTime,
        int availableSeats,
        double price
) {
}