package com.redoair.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redoair.domain.Booking;

@Stateless
public class BookingRepository {

	@PersistenceContext
	private EntityManager em;
	
	public Booking findBookingById(Long id){
		return em.find(Booking.class, id);
	}
}
