package com.redoair.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ejb.Remote;

import com.redoair.domain.Airport;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;

@Remote
public interface FlightServiceRemote {
	Flight saveFlight(Flight flight);
	void updateFlight(Flight flight);

	Flight findFlightById(Long id);

	List<String> findAllCitiesByCountryWithFlights(String country);

	List<String> findAllDepartureCountries();

	//List<Flight> findAllFlightsByCountry(String country);

	List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate);

	void remove(long flightId);

}
