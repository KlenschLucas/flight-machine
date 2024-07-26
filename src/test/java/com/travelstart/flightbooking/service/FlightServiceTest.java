package com.travelstart.flightbooking.service;

import com.travelstart.flightbooking.model.Flight;
import com.travelstart.flightbooking.repository.BookingRepository;
import com.travelstart.flightbooking.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Autowired
    private FlightService flightService;


    public FlightServiceTest() {
//        Flight flight1 = new Flight(1L, "ABC123", "Kimberley", "Cape Town", "2021-10-10 12:00:00", "", 10, 1000);
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        Mockito.when(flightRepository.findAll()).thenReturn(List.of(flight1, flight2));
        Mockito.when(flightRepository.findById(1L)).thenReturn(Optional.of(flight1));
        Mockito.when(flightRepository.findByFlightNumber("ABC123")).thenReturn(flight1);
        Mockito.when(flightRepository.findByFlightNumber("NEW123")).thenReturn(null);


        this.flightService = new FlightService(flightRepository, bookingRepository);
    }

    // test get all flights

    // test get flight by id with invalid id

    // test get flight by id with valid id

    // test create flight with non unquiue flight number

    // test create flight with valid request


    // delete flight
    @Test
    public void testDeleteFlightWillDeleteAllBookings() {
        this.flightService.deleteFlight(1L);
        Mockito.verify(bookingRepository).deleteByFlight(Mockito.any(Flight.class));
        Mockito.verify(flightRepository).delete(Mockito.any(Flight.class);
    }
}
