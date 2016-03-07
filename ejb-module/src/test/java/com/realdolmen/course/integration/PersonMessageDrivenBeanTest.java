package com.realdolmen.course.integration;

import com.realdolmen.course.domain.Person;
import com.realdolmen.course.utilities.integration.RemoteJmsTest;
import org.junit.Test;

import javax.jms.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class PersonMessageDrivenBeanTest extends RemoteJmsTest {
    @Test
    public void shouldSendAPassengerMessage() throws JMSException {
        ObjectMessage message = session.createObjectMessage(new Person("Theo", "Tester"));
        producer.send(message);
        assertEquals(3, count(Person.class));
    }
}
