package com.travelstart.flightbooking.repository;

import com.travelstart.flightbooking.model.Booking;
import com.travelstart.flightbooking.model.Customer;
import com.travelstart.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    void deleteByCustomer(Customer customer);

    void deleteByFlight(Flight flight);

    List<Booking> findByFlight(Flight flight);
}
