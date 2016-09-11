package com.redoair.services;

import java.util.List;

import javax.ejb.Remote;

import com.redoair.domain.Booking;
import com.redoair.domain.CreditCard;
import com.redoair.domain.Payer;

@Remote
public interface BookingService {
	
	Booking saveBooking(Booking booking);
	 List<Booking> findAllBookings();

	 List<Payer> findPayersByLastNameAndFirstName(Payer payer);
	 Booking findBookingById(Long id);
}
