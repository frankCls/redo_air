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
import org.junit.Ignore;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import com.redoair.domain.Airport;
import com.redoair.domain.BusinessClassData;
import com.redoair.domain.EconomyClassData;
import com.redoair.domain.FirstClassData;
import com.redoair.domain.Flight;
import com.redoair.domain.FlightData;
import com.redoair.domain.Pricing;
import com.redoair.domain.TravelingClassType;
import com.redoair.repositories.FlightRepository;

public class TestFlightRepository extends JpaPersistenceTest {
	private FlightRepository repo;
	private static final long depAirportId = 32L;
	private static final long destAirportId = 37L;
	private static final long flightId = 2037L;
	private static final int NUMBER_OF_FLIGHTS = 3;
	private static final TravelingClassType TravelClassType = TravelingClassType.ECONOMY_CLASS;
	private static final Integer nrOfSeats = 4;
	private Flight flight;
	
	 
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
		flight= repo.em.find(Flight.class, flightId);
		
	}

	@Test
	public void shouldReturnAFlight() {
		Flight flight = repo.findFlightById(flightId);
		Assert.assertNotNull(flight);
	}

	@Test
	public void returnAllCitiesWithFlightTest() {
		List<String> cities = repo.findAllCitiesByCountryWithFlights("Papua New Guinea");

		assertTrue("findAllCitiesByCountryWithFlights cannot be empty!", !cities.isEmpty());
		
	}

	@Test
	public void ShouldPersistAFlight() {
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

		FlightData data = new FlightData();
		data.setBusinnessClass(businessClassData);
		data.setEconomyClass(economyClassData);
		data.setFirstClass(firstClassData);
		flight.setFlightData(data);

		try {
			flight.setDepartureTime(dateFormat.parse("12-12-2016"));
			System.err.println(dateFormat.parse("12-12-2017").toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		repo.saveFlight(flight);
		Assert.assertNotNull("Passenger ID should not be null after saving", flight.getId());

	}

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void shouldThrowExceptionPersistingAFlight() {
		Flight flight = new Flight();
		flight.setDepartureLocation(depAirport);
		repo.saveFlight(flight);
	}

	@Test
	public void shouldReturnListOfFlightsBasedOnMultipleParameters() {
		List<Flight> flights = new ArrayList<>();
		Date fromDate = null;
		Date toDate = null;

		try {
			fromDate = timeStampFormat.parse("2016-10-1 09:00:00");
			toDate = timeStampFormat.parse("2017-05-30 09:10:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		flights = repo.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(depAirport.getCountry(),
				destAirport.getCountry(), TravelingClassType.ECONOMY_CLASS, 4, fromDate, toDate);
		System.err.println(flights.size());
		Assert.assertTrue(35 <= flights.size());
		
	}

	@Test
	public void queryOnNumberOfSeats() {
		String depCountry = "Canada";
		String destCountry = "Canada";
		String destRegion = "America";
		String depRegion = "America";
		Date fromDate = null;
		Date toDate = null;
		try {
			fromDate = dateFormat.parse("28-10-1016");
			toDate = dateFormat.parse("29-10-1016");
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		List<Flight>results =repo.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(depAirport.getName(), destAirport.getName(), TravelClassType, nrOfSeats, fromDate, toDate);
//		Assert.assertTrue(results.size()>0);
		List<Flight>results =repo.findFlightsByDepartureAndDestinationCountry(depCountry, destCountry);
		Assert.assertTrue(results.size()>0);
	}

}
