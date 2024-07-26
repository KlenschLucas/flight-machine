package com.travelstart.flightbooking.exception;

public class NoSeatsAvailableOnFlightException extends RuntimeException {
    public NoSeatsAvailableOnFlightException(String message) {
        super(message);
    }
}
