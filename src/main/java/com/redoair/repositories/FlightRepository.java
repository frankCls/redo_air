package com.redoair.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redoair.domain.Flight;


@Stateless
public class FlightRepository {

	@PersistenceContext 
	protected EntityManager em;
	
	public Flight findFlightById(Long id){
		return em.find(Flight.class, id);
	}
}
