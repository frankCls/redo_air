package com.redoair.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.*;
import com.redoair.domain.Flight;
import com.redoair.domain.Passenger;
import com.redoair.domain.PurchaseStatus;
import com.redoair.domain.Ticket;
import com.redoair.domain.TravelingClassType;

public class TicketRepositoryTest extends JpaPersistenceTest {

	private TicketRepository ticketRepository;



	@Before
	public void setUp() {
		ticketRepository = new TicketRepository();
		ticketRepository.em = entityManager();
	}

	
	@Test	
	public void testSaveTicket() {

		Ticket ticket = new Ticket();
		Flight flight = new Flight();
		Passenger passenger = new Passenger();
		passenger.setPassword("password");
		passenger.setFirstName("test");
		passenger.setLastName("lastname");
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date date = format.parse("2-09-2016");
			flight.setDepartureTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		flight.setDuration( 500L);
		
		ticket.setPurchaseStatus(PurchaseStatus.PENDING);
		ticket.setTravelingClass(TravelingClassType.BUSINESS_CLASS);
		
		ticket.setFlight(flight);
		ticket.setPassenger(passenger);
		

		ticketRepository.save(ticket);
		assertNotNull("Ticket ID is not supposed to be null after saving", ticket.getId());
	}

}
