package com.travelstart.flightbooking.controller;

import com.travelstart.flightbooking.dto.CreateBookingRequest;
import com.travelstart.flightbooking.dto.CreateBookingResponse;
import com.travelstart.flightbooking.dto.UpdateBookingRequest;
import com.travelstart.flightbooking.model.Booking;
import com.travelstart.flightbooking.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller to handle booking-related operations.
 */
@RestController
@RequestMapping("/v1/bookings")
@Api(value = "Booking Management System", tags = "Bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Retrieve all bookings.
     *
     * @return A list of bookings.
     */
    @ApiOperation(value = "Retrieve all bookings with optional filters", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully retrieved list"), @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    /**
     * Retrieve details for a specific booking by ID.
     *
     * @param id The ID of the booking to retrieve.
     * @return A response entity containing the booking details.
     */
    @ApiOperation(value = "Retrieve details for a specific booking by ID", response = CreateBookingResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Booking found"), @ApiResponse(code = 404, message = "Booking not found"), @ApiResponse(code = 500, message = "Internal server error")})
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@ApiParam(value = "ID of the booking to retrieve", required = true) @PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);

        return ResponseEntity.ok(booking);
    }

    /**
     * Create a new booking.
     *
     * @param createBookingRequest The booking object to create.
     * @return A response entity containing the created booking and status.
     */
    @ApiOperation(value = "Create a new booking", response = CreateBookingResponse.class, notes = "This endpoint creates a new booking and returns the details of the created booking.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Booking created successfully"), @ApiResponse(code = 400, message = "Invalid input, object invalid"), @ApiResponse(code = 500, message = "Internal server error")})
    @PostMapping
    public ResponseEntity<Booking> createBooking(@ApiParam(value = "Booking object to create", required = true) @Valid @RequestBody CreateBookingRequest createBookingRequest) {

        Booking booking = bookingService.createBooking(createBookingRequest);

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    /**
     * Update a booking.
     *
     * @param updateBookingRequest The booking object to create.
     * @return A response entity containing the created booking and status.
     */
    @ApiOperation(value = "Create a new booking", response = Booking.class, notes = "This endpoint creates a new booking and returns the details of the created booking.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Booking update successfully"), @ApiResponse(code = 400, message = "Invalid input, object invalid"), @ApiResponse(code = 404, message = "Booking not found"), @ApiResponse(code = 500, message = "Internal server error")})
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@ApiParam(value = "ID of the booking to delete", required = true) @PathVariable Long id, @Valid @RequestBody UpdateBookingRequest updateBookingRequest) {

        Booking booking = bookingService.updateBooking(id, updateBookingRequest);

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    /**
     * Cancel a booking by ID.
     *
     * @param id The ID of the booking to delete.
     * @return A response entity with no content status.
     */
    @ApiOperation(value = "Cancel a booking")
    @ApiResponses(value = {@ApiResponse(code = 204, message = "Booking deleted successfully"), @ApiResponse(code = 404, message = "Booking not found"), @ApiResponse(code = 500, message = "Internal server error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@ApiParam(value = "ID of the booking to delete", required = true) @PathVariable Long id) {

        bookingService.deleteBooking(id);

        return ResponseEntity.noContent().build();
    }
}
