package com.redoair.repositories;

import java.util.Date;
import java.util.HashMap;
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
import com.redoair.repositories.FlightRepository;




public class TestFlightRepository extends JpaPersistenceTest {
	private FlightRepository repo;
	private static final long ID= 1;
	private static final long depAirportId = 10L;
//	private static final String depAirportName = "Thule Air Base";
//	private static final String depAirportCountry = "Greenland";
//	private static final String depAirPortCity = "Thule";
	private static final long destAirport = 26L;
	
	
	
	@Before
	public void setup() throws Exception{
		repo = new FlightRepository();
		repo.em = entityManager();
	}
	
	//@Test
	public void ShouldReturnAFlight(){
		Assert.assertTrue(true);
	}
	
	@Test
	public void ShouldPersistAFlight(){
		Flight flight = new Flight();
		Airport depLoc = repo.em.find(Airport.class, depAirportId);
		
		Airport destLoc = repo.em.find(Airport.class, destAirport);
		flight.setDepartureLocation(depLoc);
		flight.setDestinationLocation(destLoc);
		flight.setDuration(156105610L);
		//Assert.assertTrue(repo.em.contains(depLoc));
		FirstClassData firstClassData = new FirstClassData();
		Pricing pricing = new Pricing();
		pricing.setBasePrice(50.0);
		pricing.setDefaultPrice(pricing.getBasePrice()*105);
		Map<Integer,Double>discounts = new HashMap<>();
		discounts.put(5, 0.05);
		discounts.put(10, 0.07);
		pricing.setDiscounts(discounts);
		firstClassData.setPricing(pricing);
		firstClassData.setRemainingSeats(12);
		BusinessClassData businessClassData= new BusinessClassData();
		businessClassData.setPricing(pricing);
		businessClassData.setRemainingSeats(5);
		EconomyClassData economyClassData =new EconomyClassData();
		economyClassData.setPricing(pricing);
		economyClassData.setRemainingSeats(10);
		flight.setFirstClassData(firstClassData);
		flight.setBusinessClassData(businessClassData);
		flight.setEconomyClassData(economyClassData);
		flight.setDepartureTime(new Date(2016, 12, 12));
		
		repo.saveFlight(flight);
		Flight find = repo.em.find(Flight.class, flight.getId());
		System.err.println(find.getId());
		Assert.assertNotNull("Passenger ID should not be null after saving",find);
		
	
		

	}
	
	
}
