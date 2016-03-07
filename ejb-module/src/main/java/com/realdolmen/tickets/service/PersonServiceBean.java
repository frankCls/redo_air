package com.realdolmen.tickets.service;

import com.realdolmen.tickets.domain.Person;
import com.realdolmen.tickets.repository.PersonRepositoryRemote;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@LocalBean
public class PersonServiceBean implements PersonServiceRemote{

    @EJB
    PersonRepositoryRemote personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
