package com.redoair.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redoair.domain.Airport;
import com.redoair.domain.Booking;

@Stateless
public class AirportRepository {

	@PersistenceContext(unitName = "MyPersistenceUnit")
	private EntityManager em;

	public Airport findAirportById(Long id) {
		return em.find(Airport.class, id);
	}

	public List<Airport> findAirportByRegio(String region) {
		TypedQuery<Airport> query = em.createQuery("select a from Airport a where a.region= :region", Airport.class);
		query.setParameter("region", region);

		return query.getResultList();
	}

	public List<String> findAllAirportRegions() {
		return em.createQuery("select region from Airport a", String.class).getResultList();

	}

	public List<Airport> findAllAirports() {
		return em.createQuery("select a from Airport a", Airport.class).getResultList();
	}

	public void updateAirport(Airport airport) {
		em.refresh(airport);
	}

	public void deleteAirport(long id) {
		em.remove(em.find(Airport.class, id));
	}

}
