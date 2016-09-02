package com.redoair.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import com.redoair.domain.Airport;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;

@Remote
public interface FlightServiceRemote {
	Flight saveFlight(Flight flight);

	Flight findFlightById(Long id);

	List<String> findAllCitiesByCountryWithFlights(String country);

	List<String> findAllCountryWithFlights();

	List<Flight> findAllFlightsByCountry(String country);

	List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate);

	void remove(long flightId);

}
