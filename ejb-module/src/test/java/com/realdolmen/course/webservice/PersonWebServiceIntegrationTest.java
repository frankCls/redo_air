package com.realdolmen.course.webservice;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import com.realdolmen.course.webservice.PersonWebService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonWebServiceIntegrationTest extends RemoteIntegrationTest {

    private PersonWebService service;

    @Before
    public void setupBeforeClass() throws Exception {
        URL wsdlLocation = new URL("http://localhost:8080/ejb-module/PersonWebService/PersonWebServiceEndpoint?wsdl");
        QName serviceName = new QName("http://webservice.course.realdolmen.com/", "PersonWebService");
        Service webService = Service.create(wsdlLocation, serviceName);
        service = webService.getPort(PersonWebService.class);
    }

    @Test
    public void shouldReturnAllPeople() {
        List<Person> result = service.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.stream().map(Person::toString).forEach(logger::trace);
    }
}
