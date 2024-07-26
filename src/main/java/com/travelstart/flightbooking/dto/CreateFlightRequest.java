package com.travelstart.flightbooking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateFlightRequest(
        @NotBlank(message = "Flight number is required")
        String flightNumber,

        @NotBlank(message = "Origin is required")
        String origin,

        @NotBlank(message = "Destination is required")
        String destination,

        @NotNull(message = "Departure date time is required")
        LocalDateTime departureDateTime,

        @NotNull(message = "Arrival date time is required")
        LocalDateTime arrivalDateTime,

        @Min(value = 1, message = "Available seats should be at least 1")
        int availableSeats,

        @Min(value = 1, message = "Price should be at least 1")
        double price
) {
}