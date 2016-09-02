package com.redoair.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import com.redoair.domain.Airport;
import com.redoair.domain.BusinessClassData;
import com.redoair.domain.EconomyClassData;
import com.redoair.domain.FirstClassData;
import com.redoair.domain.Flight;
import com.redoair.domain.Pricing;
import com.redoair.domain.TravelingClassType;
import com.redoair.repositories.FlightRepository;

public class TestFlightRepository extends JpaPersistenceTest {
	private FlightRepository repo;
	private static final long depAirportId = 1L;
	private static final long destAirportId = 2L;
	private static final long flightId = 2L;
	// private static final TravelingClassType TravelClassType =
	// TravelingClassType.ECONOMY_CLASS;
	private Airport depAirport;
	private Airport destAirport;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	@Before
	public void setup() throws Exception {
		repo = new FlightRepository();
		repo.em = entityManager();
		depAirport = repo.em.find(Airport.class, depAirportId);
		destAirport = repo.em.find(Airport.class, destAirportId);
	}

	@Test
	public void shouldReturnAFlight() {
		Flight flight = repo.findFlightById(flightId);
		Assert.assertNotNull(flight);
	}


	public void returnAllCitiesWithFlightTest(){
		List<String> findAllCitiesByCountryWithFlights = repo.findAllCitiesByCountryWithFlights("Papua New Guinea");
		if (findAllCitiesByCountryWithFlights.isEmpty()) {
			System.err.println("there are no flights in this country! \n");
		}
		for (String city : findAllCitiesByCountryWithFlights) {
			System.out.println(city);
		}
		assertTrue("findAllCitiesByCountryWithFlights cannot be empty!", ! findAllCitiesByCountryWithFlights.isEmpty());;
	}
	
	@Test
	public void ShouldPersistAFlight(){
		Flight flight = new Flight();		

		flight.setDepartureLocation(depAirport);
		flight.setDestinationLocation(destAirport);
		flight.setDuration(156105610L);
		// Assert.assertTrue(repo.em.contains(depLoc));
		FirstClassData firstClassData = new FirstClassData();
		Pricing pricing = new Pricing();
		pricing.setBasePrice(50.0);
		pricing.setDefaultPrice(pricing.getBasePrice() * 105);
		Map<Integer, Double> discounts = new HashMap<>();
		discounts.put(5, 0.05);
		discounts.put(10, 0.07);
		pricing.setDiscounts(discounts);
		firstClassData.setPricing(pricing);
		firstClassData.setRemainingSeats(12);
		BusinessClassData businessClassData = new BusinessClassData();
		businessClassData.setPricing(pricing);
		businessClassData.setRemainingSeats(5);
		EconomyClassData economyClassData = new EconomyClassData();
		economyClassData.setPricing(pricing);
		economyClassData.setRemainingSeats(10);
		flight.setFirstClassData(firstClassData);
		flight.setBusinessClassData(businessClassData);
		flight.setEconomyClassData(economyClassData);

		try {
			flight.setDepartureTime(dateFormat.parse("12-12-2016"));
			System.err.println(dateFormat.parse("2016-12-12").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		repo.saveFlight(flight);
		Assert.assertNotNull("Passenger ID should not be null after saving", flight.getId());

	}

	@Test(expected=javax.validation.ConstraintViolationException.class)
	public void shouldThrowExceptionPersistingAFlight() {
		Flight flight = new Flight();
		flight.setDepartureLocation(depAirport);
		repo.saveFlight(flight);
	}

	@Test
	public void shouldReturnListOfFlightsBasedOnMultipleParameters() {
		List<Flight> flights = new ArrayList<>();
		Date fromDate=null;
		Date toDate=null;
		
		try {
			fromDate= timeStampFormat.parse("2017-05-30 09:00:00");
			toDate= timeStampFormat.parse("2017-05-30 09:10:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		flights = repo.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(depAirport.getCountry(), destAirport.getCountry(), TravelingClassType.ECONOMY_CLASS, 4,fromDate,toDate);
		System.err.println(flights.size());
		flights.forEach(s->System.out.println(s.getDepartureLocation().getName()));
		Assert.assertTrue(1==flights.size());
		Assert.assertEquals("Goroka", flights.get(0).getDepartureLocation().getName());
	}
	

}
