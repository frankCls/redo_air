package com.realdolmen.tickets.rs;

import com.realdolmen.course.service.PersonServiceBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/people")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Stateless
public class PersonRestServiceEndpoint implements PersonRestService {

    @EJB
    PersonServiceBean personService;

    @Override
    @GET
    public PersonList findAll() {
        return new PersonList(personService.findAll());
    }

}
