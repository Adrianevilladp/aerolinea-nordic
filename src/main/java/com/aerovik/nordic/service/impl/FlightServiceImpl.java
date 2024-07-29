package com.aerovik.nordic.service.impl;

import com.aerovik.nordic.exception.NotFoundException;
import com.aerovik.nordic.model.Flight;
import com.aerovik.nordic.payload.flight.FlightSearchRequest;
import com.aerovik.nordic.repository.FlightRepository;
import com.aerovik.nordic.service.FlightService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final EntityManager entityManager;
    private static final String FLIGHT_NOT_FOUND = "Flight not found";

    @Override
    public List<Flight> findAll() {
        if (flightRepository.findAll().isEmpty()) {
            log.info("No flights found");
            throw new NotFoundException(FLIGHT_NOT_FOUND);
        }
        log.info("Flights found");
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> {
            log.info("Flight with id {} not found", id);
            return new NotFoundException(FLIGHT_NOT_FOUND);
        });
    }

    @Override
    public List<Flight> searchFlights(Optional<FlightSearchRequest> request) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> flightCriteriaQuery = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> root = flightCriteriaQuery.from(Flight.class);

        if (request.isEmpty()) {
            log.info("No parameters found");
            flightCriteriaQuery.select(root);
            return entityManager.createQuery(flightCriteriaQuery).getResultList();
        }

        List<Predicate> predicates = getPredicates(request.get(), criteriaBuilder, root);
        Predicate[] finalPredicates = new Predicate[predicates.size()];
        predicates.toArray(finalPredicates);
        flightCriteriaQuery.where(finalPredicates);

        List<Flight> result = entityManager.createQuery(flightCriteriaQuery).getResultList();

        if (result.isEmpty()) {
            log.info("No flights found");
            throw new NotFoundException(FLIGHT_NOT_FOUND);
        }
        log.info("Flights found");
        return result;
    }

    //it needs to be refactored
    private List<Predicate> getPredicates(FlightSearchRequest request, CriteriaBuilder criteriaBuilder,
                                              Root<Flight> root) {
        List<Predicate> predicates  = new ArrayList<>();
        if (Objects.nonNull(request.flightNumber())) {
            predicates.add(criteriaBuilder.equal(root.get("flightNumber"), request.flightNumber()));
        }
        if (Objects.nonNull(request.departureDate())) {
            predicates.add(criteriaBuilder.equal(root.get("departureDate"), request.departureDate()));
        }
        if (Objects.nonNull(request.arrivalDate())) {
            predicates.add(criteriaBuilder.equal(root.get("arrivalDate"), request.arrivalDate()));
        }
        if (Objects.nonNull(request.departureAirportName())) {
            predicates.add(criteriaBuilder.equal(root.get("departureAirportName"), request.departureAirportName()));
        }
        if (Objects.nonNull(request.arrivalAirportName())) {
            predicates.add(criteriaBuilder.equal(root.get("arrivalAirportName"), request.arrivalAirportName()));
        }
        return predicates;
    }

    private List<Serializable> getValidParameters(FlightSearchRequest request){
        return Arrays.asList(request.flightNumber(),
                request.departureAirportName(),
                request.arrivalAirportName(),
                request.departureDate(),
                request.arrivalDate()
                );
    }

    private List<Predicate> getPredicateToQuery(FlightSearchRequest request){
        List<Serializable> parameterList = getValidParameters(request);
        List<Predicate> predicates  = new ArrayList<>();
        parameterList.stream().forEach(parameter -> {
            if (Objects.nonNull(parameter)) {

            }
        });
    }

    private Predicate createPredicate(String conditionName, ){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> flightCriteriaQuery = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> root = flightCriteriaQuery.from(Flight.class);

    }




}
