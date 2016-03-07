package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.service.PersonServiceBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PersonController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    PersonServiceBean personService;

    public List<Person> getAllPeople() {
        logger.debug("Retrieving all books");
        return personService.findAll();
    }

    public void remove(int bookId) {
        logger.debug("Removing book");
        personService.remove(bookId);
    }
}
