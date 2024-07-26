package com.travelstart.flightbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBookingRequest(
        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotBlank(message = "Flight number is required")
        String flightNumber,

        @Min(value = 1, message = "Number of seats booked should be at least 1")
        int numberOfSeatsBooked
) {
}
