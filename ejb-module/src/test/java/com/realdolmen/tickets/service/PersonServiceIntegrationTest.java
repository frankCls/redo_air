package com.realdolmen.tickets.service;

import com.realdolmen.tickets.domain.Person;
import com.realdolmen.tickets.repository.PersonRepositoryRemote;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class PersonServiceIntegrationTest {

    private static Context context;

    //@BeforeClass
    public static void setupBeforeClass() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put("jboss.naming.client.ejb.context", true);
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put(Context.PROVIDER_URL, "remote://localhost:4447");
        properties.put(Context.SECURITY_PRINCIPAL, "administrator");
        properties.put(Context.SECURITY_CREDENTIALS, "Azerty123!");
        context = new InitialContext(properties);
    }

    @Test
    @Ignore
    public void testPersonServiceCanBeAccessedRemotely() throws Exception {
        PersonRepositoryRemote personRepository = (PersonRepositoryRemote) context.lookup("ticket-ear-1.0/ticket-ejb-1.0/PersonRepositoryBean!com.realdolmen.tickets.repository.PersonRepository");
        System.out.println(personRepository);
        for (Person person : personRepository.findAll()) {
            System.out.println(person.getFirstName() + " " + person.getLastName());
        }

    }
}
