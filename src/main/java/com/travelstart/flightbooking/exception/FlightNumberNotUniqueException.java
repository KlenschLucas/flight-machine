package com.travelstart.flightbooking.exception;

public class FlightNumberNotUniqueException extends RuntimeException {
    private static final long serialVersionUID = 1697967816868211008L;

    public FlightNumberNotUniqueException(String message) {
        super(message);
    }

    public FlightNumberNotUniqueException(String code, String message) {
        super(message);
    }
}
