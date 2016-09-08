package com.redoair.repositories;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redoair.domain.Pricing;

@Stateless
public class PricingRepository {

	@PersistenceContext
	protected EntityManager em;
	
	public Pricing updatePricing(Pricing pricing){
		return em.merge(pricing);
	}
}
