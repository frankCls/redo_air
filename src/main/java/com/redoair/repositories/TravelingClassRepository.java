package com.redoair.repositories;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redoair.domain.AbstractTravelingClassData;

@Stateless
public class TravelingClassRepository {
	
	@PersistenceContext
	protected EntityManager em;
	
	public AbstractTravelingClassData findDataById(Long id){
		return em.find(AbstractTravelingClassData.class, id);
	}

}
