package com.realdolmen.course.integration;

import com.realdolmen.course.persistence.RemoteBookRepository;
import org.junit.Assume;
import org.junit.Test;

import javax.naming.NamingException;

import static org.junit.Assume.*;

public class RemoteBookRepositoryTest extends RemoteIntegrationTest {
    @Test
    public void findAllRetrievesAllBooksRemotely() throws NamingException {
        RemoteBookRepository repository = lookup("ear-module-1.1/ejb-module-1.1/BookRepository!com.realdolmen.course.persistence.RemoteBookRepository***");
        assertEquals(3, repository.findAll().size());
    }
}
