package com.redoair.web.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import com.redoair.domain.Booking;
import com.redoair.services.BookingServiceEjb;

import java.io.Serializable;

@ManagedBean
@SessionScoped
public class BookingBean implements Serializable {
	private Booking booking = new Booking();
	
	@Inject
	private BookingServiceEjb bookingServiceEjb;
	
	
	
	@Inject
	private FlightBean flightBean;
	
	
}
