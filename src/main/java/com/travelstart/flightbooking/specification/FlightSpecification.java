package com.travelstart.flightbooking.specification;

import com.travelstart.flightbooking.model.Booking;
import com.travelstart.flightbooking.model.Flight;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightSpecification implements Specification<Flight> {

    private final Optional<String> flightNumber;
    private final Optional<String> origin;
    private final Optional<String> destination;

    public FlightSpecification(Optional<String> flightNumber, Optional<String> origin, Optional<String> destination) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public Predicate toPredicate(Root<Flight> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        flightNumber.ifPresent(name -> predicates.add(criteriaBuilder.equal(root.get("flightNumber"), name)));
        origin.ifPresent(date -> predicates.add(criteriaBuilder.equal(root.get("origin"), date)));
        destination.ifPresent(date -> predicates.add(criteriaBuilder.equal(root.get("destination"), date)));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
