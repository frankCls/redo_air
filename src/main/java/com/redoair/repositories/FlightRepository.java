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

	@PersistenceContext(unitName = "MyPersistenceUnit")
	protected EntityManager em;

	public Flight findFlightById(Long id) {
		return em.find(Flight.class, id);
	}

	public void saveFlight(Flight flight) {
		em.persist(flight);
	}

	public List<Flight> findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(String depAirport,
			String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate) {
		/*System.out.println(depAirport);
		System.out.println(destAirport);
		System.out.println(travelingClass);
		System.out.println(seats);
		System.out.println(fromDate);
		System.out.println(toDate);*/
		String travelingClassdata = decideWhichStringInQuery(travelingClass);
	
		TypedQuery<Flight> q = em
				.createQuery(
						"SELECT f FROM Flight f WHERE f.departureLocation.country=:depAirport AND f.destinationLocation.country=:destAirport AND "
								+ travelingClassdata
								+ ".remainingSeats >= :seats AND f.departureTime BETWEEN :fromDate AND :toDate",
						Flight.class);
		q.setParameter("depAirport", depAirport);
		q.setParameter("destAirport", destAirport);
		q.setParameter("seats", seats);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
System.out.println(q.getSingleResult());
		return q.getResultList();
	}

	private String decideWhichStringInQuery(TravelingClassType travelingClassType) {

		String travelingClassdata = "f.economyClassData";
		//travelingClassType = TravelingClassType.ECONOMY_CLASS;
		
			switch (travelingClassType.name()) {
			case "ECONOMY_CLASS":
				travelingClassdata = "f.economyClassData";
				break;
			case "BUSINESS_CLASS":
				travelingClassdata = "f.businessClassData";
				break;
			case "FIRST_CLASS":
				travelingClassdata = "f.firstClassData";
				break;
			}
		
		return travelingClassdata;
	}

	public List<Flight> findAllFlightsByCountry(String country) {

		TypedQuery<Flight> q = em.createQuery("SELECT f FROM Flight f WHERE f.departureLocation.country=:country",
				Flight.class);
		q.setParameter("country", country);
		return q.getResultList();
	}

	public List<String> findAllCitiesByCountryWithFlights(String country) {

		TypedQuery<String> q = em.createQuery(
				"SELECT distinct f.departureLocation.city FROM Flight f WHERE f.departureLocation.country=:country",
				String.class);
		q.setParameter("country", country);
		return q.getResultList();
	}

	public List<String> findAllCountryWithFlights() {
		TypedQuery<String> q = em.createQuery("SELECT distinct f.departureLocation.country FROM Flight f",
				String.class);
		return q.getResultList();
	}

}
