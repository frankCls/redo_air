package com.redoair.repositories;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.*;

public class BookingRepositoryTest extends JpaPersistenceTest{

	private BookingRepository bookingRepository;
	private TicketRepository ticketRepository;

	@Before
	public void setUp() throws Exception {
		bookingRepository = new BookingRepository();
		bookingRepository.em = entityManager();
		ticketRepository = new TicketRepository();
		ticketRepository.em = entityManager();
	}

	@Test
	public void testSaveBooking() {
		
	}

}
