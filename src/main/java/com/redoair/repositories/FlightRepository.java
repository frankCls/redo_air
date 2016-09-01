package com.redoair.repositories;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.redoair.domain.AbstractTravelingClassData;
import com.redoair.domain.Airport;
import com.redoair.domain.BusinessClassData;
import com.redoair.domain.EconomyClassData;
import com.redoair.domain.FirstClassData;
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

	public List<Flight> findFlightsByLocations(Airport depAirport, Airport destAirport,
			TravelingClassType travelingClass, Integer seats, Date departureDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(departureDate);
		cal.add(Calendar.DATE,1);
		Date oneDayLater = cal.getTime();
		String travelingClassdata = null;
		switch (travelingClass) {
		case ECONOMY_CLASS:
			travelingClassdata = " f.economyClassData";
			break;
		case BUSINESS_CLASS:
			travelingClassdata = " f.businessClassData";
			break;
		case FIRST_CLASS:
			travelingClassdata = " f.firstClassData";
			break;
		}

		TypedQuery<Flight> q = em.createQuery(
				"SELECT f FROM Flight f WHERE f.departureLocation=:depAirport AND f.destinationLocation=:destAirport AND" + travelingClassdata + ".remainingSeats=:seats AND f.departureTime BETWEEN :departureDate AND :oneDayLater",
				Flight.class);
		q.setParameter("depAirport", depAirport);
		q.setParameter("destAirport", destAirport);
		q.setParameter("seats", seats);
		q.setParameter("oneDayLater", oneDayLater);
		return q.getResultList();
	}
}
