package com.travelstart.flightbooking.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1697967816868211008L;

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String code, String message) {
        super(message);
    }
}
