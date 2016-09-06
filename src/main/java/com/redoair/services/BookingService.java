package com.redoair.services;

import java.util.List;

import javax.ejb.Remote;

import com.redoair.domain.Booking;

@Remote
public interface BookingService {
	
	void saveBooking(Booking booking);
	 List<Booking> findAllBookings();
}
