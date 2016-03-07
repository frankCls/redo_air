package com.realdolmen.tickets.service;

import com.realdolmen.tickets.domain.Person;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonServiceRemote {
    Person save(Person person);
    Person findById(Long id);
    List<Person> findAll();
}
