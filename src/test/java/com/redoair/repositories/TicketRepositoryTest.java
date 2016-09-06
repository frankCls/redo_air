package com.redoair.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.*;
import com.redoair.domain.Flight;
import com.redoair.domain.Passenger;
import com.redoair.domain.PurchaseStatus;
import com.redoair.domain.Ticket;
import com.redoair.domain.TravelingClassType;

public class TicketRepositoryTest extends JpaPersistenceTest {

	private TicketRepository ticketRepository;
	private static final long TICKET_ID = 2L;
	private static final long FLIGHT_ID = 17313L;
	private Flight flight;



	@Before
	public void setUp() {
		ticketRepository = new TicketRepository();
		ticketRepository.em = entityManager();
		flight = ticketRepository.em.find(Flight.class, FLIGHT_ID);
	}

	
	@Test	
	public void shouldSaveATicket() {
		Ticket ticket = new Ticket();		
		Passenger passenger = new Passenger();
		passenger.setPassword("password");
		passenger.setFirstName("test");
		passenger.setLastName("lastname");		
		
		ticket.setTravelingClass(TravelingClassType.BUSINESS_CLASS);		
		ticket.setFlight(flight);
		ticket.setPassenger(passenger);
		
		ticketRepository.save(ticket);
		assertNotNull("Ticket ID is not supposed to be null after saving", ticket.getId());
	}
	
	@Test
	public void shouldReturnATicket(){
		Ticket ticket = ticketRepository.findById(TICKET_ID);
		Assert.assertNotNull("ticket should not be null", ticket);
		Assert.assertTrue(ticket.getFlight().getId()==17313);
	}
	
	

}
