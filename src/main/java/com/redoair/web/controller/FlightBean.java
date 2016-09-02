package com.redoair.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
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
import com.redoair.repositories.FlightRepository;
import com.redoair.services.FlightService;

@Named(value = "flightBean")
@SessionScoped
public class FlightBean implements Serializable {

	@Inject
	private FlightService flightService;
	
	@NotNull
	private Long duration;
		
	@NotNull
	@Future
	private Date departureTime;
	
	@NotNull
	private Airport departureLocation;
	
	@NotNull
	private Airport destinationLocation;
	
	@NotNull
	private FirstClassData firstClassData;
	
	@NotNull
	private EconomyClassData economyClassData;
	
	@NotNull
	private BusinessClassData businessClassData;

	private List<Flight> filteredFlights = new ArrayList<>();

	private String nameCountryFilter = "";

}
