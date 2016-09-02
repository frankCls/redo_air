package com.redoair.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.domain.Airport;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.repositories.FlightRepository;

@Stateless
@LocalBean
public class FlightService implements FlightServiceRemote{
	@EJB
	 FlightRepository flightRepository;

	@Override
	public Flight saveFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Flight findFlightById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllCitiesByCountryWithFlights(String country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllCountryWithFlights() {
		
		return flightRepository.findAllCountryWithFlights();
	}

	

	@Override
	public void remove(long flightId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,	String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate) {
	
		return flightRepository.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(depAirport, destAirport, travelingClass, seats, fromDate, toDate);
		
	}

	@Override
	public List<Flight> findAllFlightsByCountry(String country) {
		
		return flightRepository.findAllFlightsByCountry(country);
	}
	
	
	
}
