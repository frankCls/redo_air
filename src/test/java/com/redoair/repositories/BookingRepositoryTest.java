package com.redoair.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.realdolmen.course.utilities.persistence.*;
import com.redoair.domain.Booking;
import com.redoair.domain.Payer;
import com.redoair.domain.PurchaseStatus;
import com.redoair.domain.Ticket;

public class BookingRepositoryTest extends JpaPersistenceTest {

	private BookingRepository bookingRepository = new BookingRepository();;
	private static final long BOOKING_ID = 2L;
	private static final long TICKET_ID1 = 2L;
	private static final long TICKET_ID2 = 3L;
	private Ticket ticket1;
	private Ticket ticket2;
	private static final long PAYER_ID = 2L;
	private Payer payer;

	@Before
	public void setUp() throws Exception {
		bookingRepository.em = entityManager();
		ticket1 = bookingRepository.em.find(Ticket.class, TICKET_ID1);
		ticket2 = bookingRepository.em.find(Ticket.class, TICKET_ID2);
		payer = bookingRepository.em.find(Payer.class, PAYER_ID);
	}

	@Test
	public void shouldSaveABooking() {
		Booking booking = new Booking();
		booking.addTickets(ticket1);
		booking.addTickets(ticket2);
		long ticket1version1 = ticket1.getVersion();
		System.err.println(ticket1version1);		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBooked = null;
		try {
			dateBooked = format.parse("2025-12-12");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		booking.setDateBooked(dateBooked);
		booking.setPayer(payer);
		booking.setPurchaseStatus(PurchaseStatus.PENDING);
		bookingRepository.save(booking);
		
		
		
		Assert.assertNotNull("booking id should not be null", booking.getId());
		Assert.assertTrue(booking.getTickets().size() == 2);
		Assert.assertEquals("Bert", booking.getPayer().getFirstName());
		ticket1 = bookingRepository.em.find(Ticket.class,TICKET_ID1);
		System.err.println(ticket1.getVersion());
		//Assert.assertTrue(1L== ticket1.getVersion());
	}

	@Test
	public void shouldReturnABooking(){
		Booking booking = bookingRepository.findBookingById(BOOKING_ID);
		Assert.assertNotNull("booking should not be null", booking);
		Assert.assertEquals("Von Testpersons", booking.getPayer().getLastName());
	}
	
	@Test
	public void shouldRemoveBooking(){
		bookingRepository.remove(BOOKING_ID);
		Ticket ticket = bookingRepository.em.find(Ticket.class, TICKET_ID1);
		Assert.assertNull(bookingRepository.em.find(Booking.class, BOOKING_ID));
	
		
	}
}
