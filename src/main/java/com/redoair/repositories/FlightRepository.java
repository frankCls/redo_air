package com.redoair.repositories;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redoair.domain.Airport;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;

@Stateless
public class FlightRepository {

	@PersistenceContext
	protected EntityManager em;

	public Flight findFlightById(Long id) {
		return em.find(Flight.class, id);
	}

	public void saveFlight(Flight flight) {
		em.persist(flight);
	}

	public List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,
			String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate) {
		
		String travelingClassdata = decideWhichStringInQuery(travelingClass);
		TypedQuery<Flight> q = em
				.createQuery(
						"SELECT f FROM Flight f WHERE f.departureLocation.country=:depAirport AND f.destinationLocation.country=:destAirport AND "
								+ travelingClassdata
								+ ".remainingSeats>=:seats AND f.departureTime BETWEEN :fromDate AND :toDate",
						Flight.class);
		q.setParameter("depAirport", depAirport);
		q.setParameter("destAirport", destAirport);
		q.setParameter("seats", seats);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		return q.getResultList();
	}

	private String decideWhichStringInQuery(TravelingClassType travelingClassType) {
		String travelingClassdata = null;
		switch (travelingClassType) {
		case ECONOMY_CLASS:
			travelingClassdata = "f.economyClassData";
			break;
		case BUSINESS_CLASS:
			travelingClassdata = "f.businessClassData";
			break;
		case FIRST_CLASS:
			travelingClassdata = "f.firstClassData";
			break;
		}
		System.err.println(travelingClassdata);
		return travelingClassdata;
	}

	public List<String> findAllCitiesByCountryWithFlights(String country) {

		TypedQuery<String> q = em.createQuery(
				"SELECT f.departureLocation.city FROM Flight f WHERE f.departureLocation.country=:country",
				String.class);
		q.setParameter("country", country);
		return q.getResultList();
	}

	public List<String> findAllCountryWithFlights() {
		TypedQuery<String> q = em.createQuery("SELECT f.departureLocation.country FROM Flight f", String.class);
		return q.getResultList();
	}

}
