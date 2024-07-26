package com.travelstart.flightbooking.exception;

public class BookingNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1697967816868211008L;

    public BookingNotFoundException(String message) {
        super(message);
    }

    public BookingNotFoundException(String code, String message) {
        super(message);
    }
}
