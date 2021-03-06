package com.redoair.repositories;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redoair.domain.Airport;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;

@Stateless
public class FlightRepository {

	@PersistenceContext(unitName = "MyPersistenceUnit")
	protected EntityManager em;

	public Flight findFlightById(Long id) {
		return em.find(Flight.class, id);
	}

	public void saveFlight(Flight flight) {
		em.persist(flight);
	}
	public void updateFlight(Flight flight) {
		
		em.merge(flight);
	}

	public List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,
			String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate) {
		System.out.println("date to: " + toDate);
		String travelingClassdata = decideWhichStringInQuery(travelingClass);
		/*
		 * System.out.println(depAirport); System.out.println(destAirport);
		 * System.out.println(seats); System.out.println(fromDate);
		 * System.out.println(toDate);
		 */

		TypedQuery<Flight> q = em
				.createQuery(
						"SELECT f FROM Flight f WHERE f.departureLocation.country=:depAirport AND f.destinationLocation.country=:destAirport AND f.flightData.economyClass.remainingSeats>=:seats AND f.departureTime BETWEEN :fromDate AND :toDate",
						Flight.class);
		System.out.println("date to: " + toDate);
		q.setParameter("depAirport", depAirport);
		q.setParameter("destAirport", destAirport);
		q.setParameter("seats", seats);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		List<Flight> list = q.getResultList();
		System.out.println(q.toString());
		list.forEach(System.out::println);
		return list;
	}

	private String decideWhichStringInQuery(TravelingClassType travelingClassType) {

		String travelingClassdata = "f.flightData.economyClass";
		// travelingClassType = TravelingClassType.ECONOMY_CLASS;

		switch (travelingClassType.name()) {
		case "ECONOMY_CLASS":
			travelingClassdata = "f.flightData.economyClass";
			break;
		case "BUSINESS_CLASS":
			travelingClassdata = "f.flightData.businnessClass";
			break;
		case "FIRST_CLASS":
			travelingClassdata = "f.flightData.firstClass";
			break;
		}

		return travelingClassdata;
	}

	// public List<Flight> findAllFlightsByCountry(String country) {
	//
	// TypedQuery<Flight> q = em.createQuery("SELECT f FROM Flight f WHERE
	// f.departureLocation.country=:country",
	// Flight.class);
	// q.setParameter("country", country);
	// return q.getResultList();
	// }

	public List<Flight> findFlightsByDepartureAndDestinationCountry(String depCountry, String destCountry) {
		TypedQuery<Flight> q = em.createQuery(
				"SELECT f FROM Flight f WHERE f.departureLocation.country=:depCountry AND f.destinationLocation.country=:destCountry",
				Flight.class);
		q.setParameter("depCountry", depCountry);
		q.setParameter("destCountry", destCountry);
		return q.getResultList();
	}

	public List<Flight> findFlightsByDepartureCountryAndDestinationRegion(String depCountry, String destRegion) {
		TypedQuery<Flight> q = em.createQuery(
				"SELECT f FROM Flight f WHERE f.departureLocation.country=:depCountry AND f.destinationLocation.region=:destRegion",
				Flight.class);
		q.setParameter("depCountry", depCountry);
		q.setParameter("destRegion", destRegion);
		return q.getResultList();
	}

	public List<Flight> findFlightsByDepartureRegionAndDestinationCountry(String depRegion, String destCountry) {
		TypedQuery<Flight> q = em.createQuery(
				"SELECT f FROM Flight f WHERE f.departureLocation.region=:depRegion AND f.destinationLocation.country=:destCountry",
				Flight.class);
		q.setParameter("depRegion", depRegion);
		q.setParameter("destCountry", destCountry);
		return q.getResultList();
	}

	public List<Flight> findFlightsByDepartureRegionAndDestinationRegion(String depRegion, String destRegion) {
		TypedQuery<Flight> q = em.createQuery(
				"SELECT f FROM Flight f WHERE f.departureLocation.region=:depRegion AND f.destinationLocation.region=:destRegion",
				Flight.class);
		q.setParameter("depRegion", depRegion);
		q.setParameter("destRegion", destRegion);
		return q.getResultList();
	}

	public List<String> findAllCitiesByCountryWithFlights(String country) {

		TypedQuery<String> q = em.createQuery(
				"SELECT distinct f.departureLocation.city FROM Flight f WHERE f.departureLocation.country=:country",
				String.class);
		q.setParameter("country", country);
		return q.getResultList();
	}

	public List<String> findAllDepartureCountries() {
		TypedQuery<String> q = em.createQuery("SELECT DISTINCT f.departureLocation.country FROM Flight f",
				String.class);
		List<String> countries = q.getResultList();
		Collections.sort(countries, ((a, b) -> a.compareTo(b)));
		return countries;
	}

	public List<String> findAllDestinationCountries() {
		TypedQuery<String> q = em.createQuery("SELECT DISTINCT f.destinationLocation.country FROM Flight f",
				String.class);
		List<String> countries = q.getResultList();
		Collections.sort(countries, ((a, b) -> a.compareTo(b)));
		return countries;
	}

	public List<String> findAllDepartureRegions() {
		TypedQuery<String> q = em.createQuery("SELECT DISTINCT f.departureLocation.region FROM Flight f", String.class);
		List<String> regions = q.getResultList();
		Collections.sort(regions, ((a, b) -> a.compareTo(b)));
		return regions;
	}

	public List<String> findAllDestinationRegions() {
		TypedQuery<String> q = em.createQuery("SELECT DISTINCT f.destinationLocation.region FROM Flight f",
				String.class);
		List<String> regions = q.getResultList();
		Collections.sort(regions, ((a, b) -> a.compareTo(b)));
		return regions;
	}

	
	
}
