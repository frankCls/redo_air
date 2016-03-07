package com.realdolmen.tickets.repository;

import com.realdolmen.tickets.domain.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@LocalBean
public class PersonRepositoryBean implements PersonRepositoryRemote {
    @PersistenceContext
    EntityManager em;

    @Override
    public Person save(Person person) {
        em.persist(person);
        return person;
    }

    @Override
    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    @Override
    public List<Person> findAll() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }
}
