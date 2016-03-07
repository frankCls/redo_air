package com.realdolmen.tickets.rs;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * Created by cda5732 on 8/02/2016.
 */
public class PersonRestServiceIntegrationTest {

    String url = "http://localhost:8080/tickets/resources/people";

    @Test
    @Ignore
    public void shouldRetrieveAllPersonsInXML() throws Exception {
        fail();
//        ClientRequest request = new ClientRequest(url);
//        request.accept(MediaType.APPLICATION_XML);
//        ClientResponse<PersonList> response = request.get(PersonList.class);
//        PersonList personList = response.getEntity();
//        assertNotNull(personList);
//        assertTrue(personList.getPersons().size() > 0);
//        assertEquals(2, personList.getPersons().size());
    }

    @Test
    @Ignore
    public void shouldRetrieveAllPersonsInJSON() throws Exception {
        fail();
//        ClientRequest request = new ClientRequest(url);
//        request.accept(MediaType.APPLICATION_JSON);
//        ClientResponse<PersonList> response = request.get(PersonList.class);
//        PersonList personList = response.getEntity();
//        assertNotNull(personList);
//        assertTrue(personList.getPersons().size() > 0);
//        assertEquals(2, personList.getPersons().size());
    }

}
