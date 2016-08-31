package com.redoair.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import com.redoair.domain.Airport;
import com.redoair.domain.Flight;
import com.redoair.repositories.FlightRepository;

public class TestFlightRepository extends JpaPersistenceTest {
	private FlightRepository repo;
	private static final long ID= 1;
	
	@Before
	public void setup() throws Exception{
		repo = new FlightRepository();
		repo.em = entityManager();
	}
	
	//@Test
	public void ShouldReturnAFlight(){
		Assert.assertTrue(true);
	}
	
	public void ShouldPersistAFlight(){
		Flight flight = new Flight();
		flight.setDuration(900000L);
		Airport depLoc = new Airport();
//		depLoc.s
//		flight.se
	}
	
	
}
