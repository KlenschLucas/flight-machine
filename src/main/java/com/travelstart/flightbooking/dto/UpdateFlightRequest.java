package com.travelstart.flightbooking.dto;

public record UpdateFlightRequest(
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

