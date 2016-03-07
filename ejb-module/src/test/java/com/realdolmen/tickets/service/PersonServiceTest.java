package com.realdolmen.tickets.service;

import com.realdolmen.tickets.domain.Person;
import com.realdolmen.tickets.repository.PersonRepositoryRemote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    private PersonRepositoryRemote repository;

    @InjectMocks
    private PersonServiceRemote service = new PersonServiceBean();

    @Before
    public void setupMox() {
        Person person = new Person("Theo", "Tester");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        when(repository.findAll()).thenReturn(persons);
    }

    @Test
    public void shouldFindAllPassengers() {
        List<Person> persons = service.findAll();
        assertNotNull(persons);
        assertTrue(persons.size() > 0);
        verify(repository).findAll();
    }

    @Test
    public void shouldCreateAPassenger() {
        Person person = new Person("Theo", "Tester");
        service.save(person);
        verify(repository).save(same(person));
    }

}
