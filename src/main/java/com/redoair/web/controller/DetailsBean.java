package com.redoair.web.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import com.redoair.domain.Flight;
import com.redoair.services.FlightService;

@ManagedBean(name="detailsBean")
@RequestScoped
public class DetailsBean {

	@Inject
	private FlightService flightService;
	
	private Long flightId;
	private Flight flight;

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	
	public void detailsListener(ActionEvent event) {
		Flight flight = (Flight) event.getComponent().getAttributes().get("flight");
		System.out.println(flight.getId());
		flightId = flight.getId();
	}
	@PostConstruct
	public void init(){
		Flight flight = flightService.findFlightById(flightId);
		this.setFlight(flight);
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
