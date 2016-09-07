package com.redoair.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redoair.domain.Passenger;

@Stateless
public class PassengerRepository {
	@PersistenceContext
	private EntityManager em;
	
	public Passenger findById(long id){
		return em.find(Passenger.class, id);
	}
	
	public void savePassenger(Passenger passenger){
		em.persist(passenger);
		
	}
		
}
