package com.realdolmen.tickets.ws;

import com.realdolmen.tickets.domain.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface PersonWebService {
    @WebMethod
    List<Person> findAll();
}
