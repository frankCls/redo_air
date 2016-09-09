package com.redoair.services;

import java.util.List;

import javax.ejb.Remote;

import com.redoair.domain.Booking;
import com.redoair.domain.CreditCard;
import com.redoair.domain.Payer;

@Remote
public interface BookingService {
	
	void saveBooking(Booking booking);
	 List<Booking> findAllBookings();

	 List<Payer> findPayersByLastNameAndFirstName(Payer payer);
}
