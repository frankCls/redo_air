package com.realdolmen.course.controller;

import com.realdolmen.course.service.PersonServiceBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest extends Mockito {
    @Mock
    private PersonServiceBean personService;

    @InjectMocks
    private PersonController controller = new PersonController();

    @Test
    public void getAllPeopleDelegatesToBookService() throws Exception {
        controller.getAllPeople();
        verify(personService).findAll();
        verifyNoMoreInteractions(personService);
    }

    @Test
    public void removeDelegatesToPersonService() throws Exception {
        controller.remove(1507);
        verify(personService).remove(1507);
        verifyNoMoreInteractions(personService);
    }
}
