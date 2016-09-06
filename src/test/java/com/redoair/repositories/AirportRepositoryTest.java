package com.redoair.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import com.redoair.domain.Airport;

public class AirportRepositoryTest extends JpaPersistenceTest {

	private AirportRepository airportRepository;

	@Before
	public void setUp() throws Exception {
		airportRepository = new AirportRepository();
		airportRepository.em = entityManager();
	}

	@Test
	
	public void testReturnAllRegions() {
		List<String> findAllAirportRegions = airportRepository.findAllAirportRegions();
		System.out.println(findAllAirportRegions);
	}

	@Test
	
	public void testReturnAllAirportsByCountry() {
		List<Airport> findAirportByCountry = airportRepository.findAllAirportByCountry("Belgium");
		for (Airport airport : findAirportByCountry) {
			System.out.println("name airport: "+airport.getName() +" country: "+ airport.getCountry() + " city: "+ airport.getCity());

		}

	}

	@Test
	public void testReturnAllAirportsCountry() {
	 List<String> findAllAirportCountry = airportRepository.findAllAirportCountry();
		for (String country : findAllAirportCountry) {
			System.out.println(country);
		}

	}
	
	@Test
	
	public void testReturnAllAirportsByregion() {
		List<Airport> findAirportByRegio = airportRepository.findAirportByRegio("Europe");
		for (Airport airport : findAirportByRegio) {
			System.out.println(airport.getName());

		}

	}
	
	@Test

	public void testReturnAll() {
		List<Airport> findAllAirports = airportRepository.findAllAirports();
		for (Airport airport : findAllAirports) {
			System.out.println(airport.getName());

		}

	}

}
