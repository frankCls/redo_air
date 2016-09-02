package com.redoair.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.domain.Flight;
import com.redoair.repositories.FlightRepository;

@Stateless
@LocalBean
public class FlightService implements FlightServiceRemote{
	@EJB
	private FlightRepository flightRepository;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(long flightId) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
