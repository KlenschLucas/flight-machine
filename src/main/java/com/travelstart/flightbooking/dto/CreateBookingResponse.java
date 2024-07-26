package com.travelstart.flightbooking.dto;

import java.io.Serializable;

public class CreateBookingResponse implements Serializable {

    private Long bookingId;
    private String status;

    public CreateBookingResponse() {
    }

    public CreateBookingResponse(Long bookingId, String status) {
        this.bookingId = bookingId;
        this.status = status;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
