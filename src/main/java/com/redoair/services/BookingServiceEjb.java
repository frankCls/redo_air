package com.redoair.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.domain.Booking;
import com.redoair.repositories.BookingRepository;
import com.redoair.repositories.FlightRepository;

@Stateless
@LocalBean
public class BookingServiceEjb implements BookingService {

	@Inject
	private BookingRepository bookingRepo;
		
	@Override
	public void saveBooking(Booking booking) {
		
		bookingRepo.save(booking);		
	}

	@Override
	public List<String> findAllDepartureCountries() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
