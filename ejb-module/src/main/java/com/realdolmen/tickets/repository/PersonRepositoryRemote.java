package com.realdolmen.tickets.repository;

import com.realdolmen.course.domain.Person;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PersonRepositoryRemote {
    Person save(Person person);
    Person findById(Long id);
    List<Person> findAll();
}
