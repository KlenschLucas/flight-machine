package com.travelstart.flightbooking.dto;

import jakarta.validation.constraints.NotNull;

public record CreateBookingRequest(
        @NotNull(message = "Flight number is required")
        String flightNumber,

        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotNull(message = "Number of seats booked is required")
        Integer numberOfSeatsBooked
) {
}
