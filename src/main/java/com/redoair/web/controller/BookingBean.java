package com.redoair.web.controller;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import com.redoair.domain.Booking;
import com.redoair.domain.CreditCardType;
import com.redoair.domain.TravelingClassType;
import com.redoair.services.BookingServiceEjb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@ManagedBean(name = "bookingBean")
@SessionScoped
public class BookingBean implements Serializable {
	
	private static final long serialVersionUID = 2180718255525138721L;

	private Booking booking = new Booking();
	
	@Inject
	private BookingServiceEjb bookingServiceEjb;
	
	
	@Inject
	 FlightBean flightBean;
	private CreditCardType selectedCreditCardType = CreditCardType.MASTER_CARD;
	private List<String> CreditCardtypeList = Arrays
			.asList(Stream.of(CreditCardType.values()).map(CreditCardType::name).toArray(String[]::new));


	public void selectOneMenuListener(ValueChangeEvent event) {
	    
	    Object newValue = event.getNewValue(); 
	   System.out.println(newValue.toString());
	}
	public List<String> getCreditCardtypeList() {
		return CreditCardtypeList;
	}


	public void setCreditCardtypeList(List<String> creditCardtypeList) {
		CreditCardtypeList = creditCardtypeList;
	}


	public Booking getBooking() {
		return booking;
	}


	public void setBooking(Booking booking) {
		this.booking = booking;
	}


	public FlightBean getFlightBean() {
		return flightBean;
	}


	public void setFlightBean(FlightBean flightBean) {
		this.flightBean = flightBean;
	}


	public CreditCardType getSelectedCreditCardType() {
		return selectedCreditCardType;
	}


	public void setSelectedCreditCardType(CreditCardType selectedCreditCardType) {
		this.selectedCreditCardType = selectedCreditCardType;
	}
	
	
}
