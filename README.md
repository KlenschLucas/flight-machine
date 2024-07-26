# Flight Booking System - Spring Boot

This is a simple flight booking system implemented using Spring Boot.  
The system allows users to book flights, view all
flights, view all customers, view all bookings, and update bookings.

## Running the application

To run the application, you need to have Java 21 installed on your machine.
You can run the application by executing the following command:

```bash
mvn spring-boot:run
```

this will start up the tomcat server and the application will be available at `http://localhost:8080`

There is an in-memory h2 database that is used to store the data.

### Developer Tools

- swagger ui will be available at `http://localhost:8080/swagger-ui/index.html`
- h2 console will be available at `http://localhost:8080/h2-console`
- actuator will be available at `http://localhost:8080/actuator`

### TODO

- [ ] Add a specification file for GetAllCustomers to do customer filtering
- [ ] Add a specification file for GetAllFlights to do flights filtering
- [ ] Add spring actuator for monitoring
- [ ] Add swagger annotations on all the endpoints
-
- Concurrent Bookings??
- both CreateBooking and UpdateBooking need to br
- synchronized -> Booking createBooking(Booking booking) and Booking updateBooking(Booking booking)
- since they are both using the needing to check the amount of the seats avaliable to save the booking
