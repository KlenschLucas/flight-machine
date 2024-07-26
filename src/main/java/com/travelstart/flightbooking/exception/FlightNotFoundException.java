package com.travelstart.flightbooking.exception;

public class FlightNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1697967816868211008L;

    public FlightNotFoundException(String message) {
        super(message);
    }

    public FlightNotFoundException(String code, String message) {
        super(message);
    }
}
