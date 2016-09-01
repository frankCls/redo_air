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
	@Ignore
	public void testReturnAllRegions() {
		List<String> findAllAirportRegions = airportRepository.findAllAirportRegions();
		System.out.println(findAllAirportRegions);
	}

	@Test
	public void testReturnAllAirportsByregion() {
		List<Airport> findAirportByRegio = airportRepository.findAirportByRegio("Europe");
		for (Airport airport : findAirportByRegio) {
			System.out.println(airport.getName());

		}

	}
	
	@Test
	@Ignore
	public void testReturnAll() {
		 List<Airport> findAllAirports = airportRepository.findAllAirports();
		for (Airport airport : findAllAirports) {
			System.out.println(airport.getName());

		}

	}

}
