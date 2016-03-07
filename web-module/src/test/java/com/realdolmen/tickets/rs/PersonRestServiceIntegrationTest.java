package com.realdolmen.tickets.rs;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by cda5732 on 8/02/2016.
 */
public class PersonRestServiceIntegrationTest {

    String url = "http://localhost:8080/tickets/resources/people";

    @Test
    @Ignore
    public void shouldRetrieveAllPersonsInXML() throws Exception {
        ClientRequest request = new ClientRequest(url);
        request.accept(MediaType.APPLICATION_XML);
        ClientResponse<PersonList> response = request.get(PersonList.class);
        PersonList personList = response.getEntity();
        assertNotNull(personList);
        assertTrue(personList.getPersons().size() > 0);
        assertEquals(2, personList.getPersons().size());
    }

    @Test
    @Ignore
    public void shouldRetrieveAllPersonsInJSON() throws Exception {
        ClientRequest request = new ClientRequest(url);
        request.accept(MediaType.APPLICATION_JSON);
        ClientResponse<PersonList> response = request.get(PersonList.class);
        PersonList personList = response.getEntity();
        assertNotNull(personList);
        assertTrue(personList.getPersons().size() > 0);
        assertEquals(2, personList.getPersons().size());
    }

}
