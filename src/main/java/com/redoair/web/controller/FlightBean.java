package com.redoair.web.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.redoair.domain.Airport;
import com.redoair.domain.BusinessClassData;
import com.redoair.domain.EconomyClassData;
import com.redoair.domain.FirstClassData;
import com.redoair.domain.Flight;
import com.redoair.domain.TravelingClassType;
import com.redoair.repositories.FlightRepository;
import com.redoair.services.FlightService;

@Named(value = "flightBean")
@SessionScoped
public class FlightBean implements Serializable {

	@Inject
	private FlightService flightService;
	
	@NotNull
	private String nameCountryDeparture ="";
	
	@NotNull
	private String nameCountryArrival ="";
	
	private int seats = 1;
	
	private TravelingClassType travelingClass = TravelingClassType.ECONOMY_CLASS;
	
	@Future
	private Date fromDate ;
	
	@Future
	private Date toDate ;
	
	private List<Flight> flightsList=new ArrayList<>() ;
	private List<String> countryList = new ArrayList<>() ;
	
	
	
	public String getAllFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate() {
		
		Date fromDate=null;
		Date toDate=null;
		
		SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			fromDate= timeStampFormat.parse("2017-04-30 09:00:00");
			toDate= timeStampFormat.parse("2017-08-30 09:10:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.err.println("Arr: "+nameCountryArrival);
		System.err.println("dep: "+nameCountryDeparture);
		System.err.println("fromDate: "+fromDate);
		System.err.println("toDate: "+toDate);
		
		//flightsList = flightService.findAllFlightsByCountry(nameCountryArrival);
		flightsList = flightService.findFlightsByLocationsWithTravelingClassTypeAndSeatsAndDepartureDate(nameCountryArrival, nameCountryDeparture, TravelingClassType.ECONOMY_CLASS, 4,fromDate,toDate);
	
		//String depAirport,String destAirport, TravelingClassType travelingClass, Integer seats, Date fromDate, Date toDate
		
		return null;
	}
	
	
	public List<String> getCountryList() {
		return flightService.findAllCountryWithFlights();
	}

	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	public String getNameCountryDeparture() {
		return nameCountryDeparture;
	}
	public void setNameCountryDeparture(String nameCountryDeparture) {
		this.nameCountryDeparture = nameCountryDeparture;
	}
	public String getNameCountryArrival() {
		return nameCountryArrival;
	}
	public void setNameCountryArrival(String nameCountryArrival) {
		this.nameCountryArrival = nameCountryArrival;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public TravelingClassType getTravelingClass() {
		return travelingClass;
	}
	public void setTravelingClass(TravelingClassType travelingClass) {
		this.travelingClass = travelingClass;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public List<Flight> getFlightsList() {
		return flightsList;
	}
	public void setFlightsList(List<Flight> flightsList) {
		this.flightsList = flightsList;
	}
}
