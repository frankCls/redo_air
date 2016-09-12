package com.redoair.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.domain.Booking;
import com.redoair.domain.CreditCard;
import com.redoair.domain.Payer;
import com.redoair.repositories.BookingRepository;
import com.redoair.repositories.FlightRepository;

@Stateless
@LocalBean
public class BookingServiceEjb implements BookingService {

	@Inject
	private BookingRepository bookingRepo;
		
	@Override
	public Booking saveBooking(Booking booking) {
		
		return bookingRepo.save(booking);		
	}

	@Override
	public List<Booking> findAllBookings() {
		
		return bookingRepo.findAll();
	}

	@Override
	public List<Payer> findPayersByLastNameAndFirstName(Payer payer) {
		return bookingRepo.findPayersByLastNameAndFirstName(payer );
	}

	@Override
	public Booking findBookingById(Long id) {
		
		return bookingRepo.findBookingById(id);
	}

	

	
	




	
	
}
