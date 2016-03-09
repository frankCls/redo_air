package com.realdolmen.course.webservice;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.service.PersonServiceBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName="PersonWebService", portName = "PersonWebService")
@Stateless
public class PersonWebServiceEndpoint implements PersonWebService {
    @EJB
    PersonServiceBean personServiceBean;

    @Override
    @WebMethod
    public List<Person> findAll() {
        return personServiceBean.findAll();
    }
}
