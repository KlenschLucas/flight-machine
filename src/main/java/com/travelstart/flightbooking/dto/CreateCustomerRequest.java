package com.travelstart.flightbooking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCustomerRequest(
        @NotBlank(message = "Name is required")
        String name,

        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone number is required")
        String phoneNumber) {
}
