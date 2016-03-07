package com.realdolmen.course.webservice;

import com.realdolmen.course.domain.Person;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface PersonWebService {
    @WebMethod
    List<Person> findAll();
}
