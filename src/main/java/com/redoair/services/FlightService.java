package com.redoair.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
public class FlightService implements FlightServiceRemote {

	@Inject
	private FlightRepository flightRepository;

	@Override
	public Flight saveFlight(Flight flight) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateFlight(Flight flight) {
		flightRepository.updateFlight(flight);
	}
	@Override
	public Flight findFlightById(Long id) {
		return flightRepository.findFlightById(id);
	}

	@Override
	public List<String> findAllCitiesByCountryWithFlights(String country) {
		return flightRepository.findAllCitiesByCountryWithFlights(country);
	}

	@Override
	public List<String> findAllDepartureCountries() {
		return flightRepository.findAllDepartureCountries();
	}

	public List<String> findAllDestinationCountries() {
		return flightRepository.findAllDestinationCountries();
	}

	public List<Flight> findFlightsByDepartureAndDestinationCountry(String depCountry, String destCountry) {
		return flightRepository.findFlightsByDepartureAndDestinationCountry(depCountry, destCountry);
	}

	@Override
	public void remove(long flightId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,
			String destAirport, TravelingClassType travelingClass, Integer seats,Date  fromDate, Date toDate) {
		System.out.println("date to: " + toDate);
		return flightRepository.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(depAirport,
				destAirport, travelingClass, seats, fromDate, toDate);

	}

	// @Override
	// public List<Flight> findAllFlightsByCountry(String country) {
	//
	// return flightRepository.findAllFlightsByCountry(country);
	// }

	public List<String> findAllDepartureRegions() {
		return flightRepository.findAllDepartureRegions();
	}

	public List<String> findAllDestinationRegions() {
		return flightRepository.findAllDestinationRegions();
	}

	public List<Flight> findFlightsForSearchCriteria(String depCountry, String depRegion, String destCountry,
			String destRegion) {
		System.err.println(depCountry+" "+depRegion+" "+destCountry+" "+destRegion);
		if (depCountry != null) {
			if (destCountry != null) {
				return flightRepository.findFlightsByDepartureAndDestinationCountry(depCountry, destCountry);
			}else if(destRegion!=null){
				return flightRepository.findFlightsByDepartureCountryAndDestinationRegion(depCountry, destRegion);
			}	
		}else if(depCountry==null){
			if(depRegion!=null){
				if (destCountry!=null){
					return flightRepository.findFlightsByDepartureRegionAndDestinationCountry(depRegion, destCountry);
				}else if(destRegion!=null){
					return flightRepository.findFlightsByDepartureRegionAndDestinationRegion(depRegion, destRegion);
				}
			}
		}
		return new ArrayList<>();
	}

	

}
