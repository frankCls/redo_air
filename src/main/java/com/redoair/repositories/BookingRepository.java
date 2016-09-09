package com.redoair.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redoair.domain.Booking;
import com.redoair.domain.CreditCard;
import com.redoair.domain.Flight;
import com.redoair.domain.Payer;
import com.redoair.domain.Ticket;

@Stateless
public class BookingRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext(unitName = "MyPersistenceUnit")
	EntityManager em;
	
	public Booking findBookingById(Long id){
		return em.find(Booking.class, id);
	}
	
	public void save(Booking booking) {

		em.merge(booking);

	}
	public Booking findById(Long id) {
		return em.find(Booking.class, id);
	}

	public List<Booking> findAll() {
		return em.createQuery("select b from Booking b", Booking.class).getResultList();
	}
	
	public List<Payer> findPayersByLastNameAndFirstName(Payer payer ) {
		
		TypedQuery<Payer> q = em.createQuery("select p from Payer p  where  p.firstName = :payerFirstName AND p.lastName = :payerLastName", Payer.class);
		q.setParameter("payerFirstName", payer.getFirstName());
		q.setParameter("payerLastName", payer.getLastName());
	
		
		return q.getResultList();
	}
	
	public List<Booking> findBookingByTicket(Ticket ticket) {
		TypedQuery<Booking> q = em.createQuery("SELECT b FROM Booking b WHERE b.id=:ticket.booking.id",Booking.class);
		q.setParameter("ticket", ticket);
		return q.getResultList();
	}
	
	/*---------optioneel---------- */
	public List<Ticket> findTicketsByBooking(Booking booking) {
		TypedQuery<Ticket> q = em.createQuery("SELECT t FROM Ticket t WHERE t.booking.id=:booking.id",Ticket.class);
		q.setParameter("booking", booking);
		return q.getResultList();
	}
	/*---------optioneel---------- */
	public Booking update(Booking booking) {
		return em.merge(booking);
	}

	public void remove(long bookingId) {
		logger.info("Removing booking with id " + bookingId);
		em.remove(em.getReference(Booking.class, bookingId));
	}

}
