package com.redoair.services;

import java.util.List;

import javax.ejb.Remote;

import com.redoair.domain.Flight;

@Remote
public interface FlightServiceRemote {
	Flight saveFlight(Flight flight);

	

	Flight findFlightById(Long id);

	List<String> findAllCitiesByCountryWithFlights(String country);

	List<String> findAllCountryWithFlights();
	
	List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate();
	
	void remove(long flightId);

}
