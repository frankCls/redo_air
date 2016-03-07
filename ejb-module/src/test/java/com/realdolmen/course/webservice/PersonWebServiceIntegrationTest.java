package com.realdolmen.course.webservice;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteIntegrationTest;
import com.realdolmen.course.webservice.PersonWebService;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonWebServiceIntegrationTest extends RemoteIntegrationTest {

    private static PersonWebService service;

    //@BeforeClass
    public static void setupBeforeClass() throws Exception {
        URL wsdlLocation = new URL("http://localhost:8080/ticket-ejb/PersonWebService/PersonWebServiceEndpoint?wsdl");
        QName serviceName = new QName("http://ws.tickets.realdolmen.com/", "PersonWebService");
        Service webService = Service.create(wsdlLocation, serviceName);
        service = webService.getPort(PersonWebService.class);
    }

    @Test
    public void shouldReturnAllPeople() {
        List<Person> result = service.findAll();
        assertNotNull(result);
        assertTrue(result.size() > 0);
        System.out.println(result);
    }

}
