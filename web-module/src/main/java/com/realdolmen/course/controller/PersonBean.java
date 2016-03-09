package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.service.PersonServiceBean;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PersonBean {
    @Inject
    private PersonServiceBean personService;

    public List<Person> allPeople() {
        return personService.findAll();
    }

    public void remove(long personId) {
        personService.remove(personId);
    }
}
