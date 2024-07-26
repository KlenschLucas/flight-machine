package com.travelstart.flightbooking.specification;

import com.travelstart.flightbooking.model.Booking;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingSpecification implements Specification<Booking> {

    private final Optional<String> customerName;
    private final Optional<String> bookingDate;

    public BookingSpecification(Optional<String> customerName, Optional<String> bookingDate) {
        this.customerName = customerName;
        this.bookingDate = bookingDate;
    }

    @Override
    public Predicate toPredicate(Root<Booking> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        customerName.ifPresent(name -> predicates.add(criteriaBuilder.equal(root.get("customerName"), name)));
        bookingDate.ifPresent(date -> predicates.add(criteriaBuilder.equal(root.get("bookingDate"), date)));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
