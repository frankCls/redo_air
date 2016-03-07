package com.realdolmen.course.repository;

import com.realdolmen.course.domain.Person;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonRepository {
    @PersistenceContext
    EntityManager em;

    public Person save(Person person) {
        em.persist(person);
        return person;
    }

    public Person findById(Long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAll() {
        return em.createQuery("select p from Person p", Person.class).getResultList();
    }

    public void remove(long personId) {
        em.remove(em.getReference(Person.class, personId));
    }
}
