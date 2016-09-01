package com.redoair.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.redoair.repositories.FlightRepository;

@Stateless
public class FlightSearchService {
	@Inject
	private FlightRepository flightRepo;
	
	
	
}
