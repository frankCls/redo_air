package com.realdolmen.tickets.ws;

import com.realdolmen.tickets.domain.Person;
import com.realdolmen.tickets.service.PersonServiceBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName="PersonWebService")
@Stateless
public class PersonWebServiceEndpoint implements PersonWebService{
    @EJB
    PersonServiceBean personServiceBean;

    @Override
    @WebMethod
    public List<Person> findAll() {
        return personServiceBean.findAll();
    }
}
