package com.redoair.repositories;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
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
			TravelingClassType travelingClass, Integer seats, Date fromDate,Date toDate) {
		//String oneDayLater = returnOneDayLater(departureDate);
		String travelingClassdata = decideWhichStringInQuery(travelingClass);
		TypedQuery<Flight> q = em.createQuery(
				"SELECT f FROM Flight f WHERE f.departureLocation=:depAirport AND f.destinationLocation=:destAirport AND "
						+ travelingClassdata
						+ ".remainingSeats>=:seats AND f.departureTime BETWEEN :fromDate AND :toDate",
				Flight.class);
		q.setParameter("depAirport", depAirport);
		q.setParameter("destAirport", destAirport);
		q.setParameter("seats", seats);
		q.setParameter("fromDate", fromDate);
		q.setParameter("toDate", toDate);
		//q.setParameter("oneDayLater", oneDayLater,TemporalType.TIMESTAMP);
		return q.getResultList();
	}
	
	private String decideWhichStringInQuery(TravelingClassType travelingClassType){
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
	
	private String returnOneDayLater(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		System.err.println(cal.getTime());
		Date returnDate=  cal.getTime();		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return formatter.format(returnDate);
	}
}
