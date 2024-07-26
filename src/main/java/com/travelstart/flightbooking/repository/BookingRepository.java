package com.travelstart.flightbooking.repository;

import com.travelstart.flightbooking.model.Booking;
import com.travelstart.flightbooking.model.Customer;
import com.travelstart.flightbooking.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    void deleteByCustomer(Customer customer);

    void deleteByFlight(Flight flight);

    long countByFlight(Flight flight);

    List<Booking> findByFlight(Flight flight);
}
