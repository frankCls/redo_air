package com.redoair.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redoair.domain.Ticket;

@Stateless
public class TicketRepository {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@PersistenceContext(unitName = "MyPersistenceUnit")
	EntityManager em;

	public Ticket save(Ticket ticket) {
		em.persist(ticket);

		return ticket;
	}

	public Ticket findById(Long id) {
		return em.find(Ticket.class, id);
	}

	public List<Ticket> findAll() {
		return em.createQuery("select t from Ticket t", Ticket.class).getResultList();
	}

	public void clear() {
		em.clear();
	}

	public Ticket update(Ticket ticket) {

		em.merge(ticket);
		return ticket;

	}

	public void remove(long ticketId) {
		logger.info("Removing Ticket with id " + ticketId);
		em.remove(em.getReference(Ticket.class, ticketId));
	}
}
