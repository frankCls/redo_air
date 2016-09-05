package com.redoair.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redoair.domain.Booking;
import com.redoair.domain.Ticket;

@Stateless
public class BookingRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext(unitName = "MyPersistenceUnit")
	EntityManager em;
	
	public Booking findBookingById(Long id){
		return em.find(Booking.class, id);
	}
	
	public Booking save(Booking booking) {
//		for(Ticket t : booking.getTickets()) {
//			em.merge(t);
//		}
		em.persist(booking);

		return booking;
	}
	public Booking findById(Long id) {
		return em.find(Booking.class, id);
	}

	public List<Booking> findAll() {
		return em.createQuery("select b from Booking b", Booking.class).getResultList();
	}

	public Booking update(Booking booking) {
		return em.merge(booking);
		 

	}

	public void remove(long bookingId) {
		logger.info("Removing booking with id " + bookingId);
		em.remove(em.getReference(Booking.class, bookingId));
	}
}
