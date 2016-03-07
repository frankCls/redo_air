package com.realdolmen.tickets.jms;

import com.realdolmen.tickets.domain.Person;
import com.realdolmen.tickets.repository.PersonRepositoryRemote;
import com.realdolmen.tickets.service.PersonServiceBean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/test"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class PersonMDB implements MessageListener {

    @EJB
    PersonServiceBean personService;

    @Override
    public void onMessage(Message message) {
        ObjectMessage om = (ObjectMessage) message;
        try {
            Person person = (Person) om.getObject();
            personService.save(person);

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
