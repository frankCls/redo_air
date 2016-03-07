package com.realdolmen.tickets.rs;

import com.realdolmen.tickets.domain.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/people")
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public interface PersonRestService {

    @GET
    public PersonList findAll();

}
