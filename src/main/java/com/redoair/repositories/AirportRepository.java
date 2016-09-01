package com.redoair.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redoair.domain.Airport;

@Stateless
public class AirportRepository {
	@PersistenceContext
	private EntityManager em;
	
	public Airport findAirportById(long id){
		return em.find(Airport.class, id);
	}
	
	public List<Airport>findAll(){
		return em.createNamedQuery("SELECT a FROM Airport a", Airport.class).getResultList();
	}
	
	public void saveAirport(Airport airport){
		em.persist(airport);		
	}
	
	public void deleteAirport(long id){
		em.remove(em.find(Airport.class, id));
	}
	
	public void updateAirport(Airport airport){
		em.refresh(airport);
	}
}
