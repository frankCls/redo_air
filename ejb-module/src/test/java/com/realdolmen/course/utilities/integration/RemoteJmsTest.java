package com.realdolmen.course.utilities.integration;

import org.junit.After;
import org.junit.Before;

import javax.jms.*;

public abstract class RemoteJmsTest extends RemoteIntegrationTest {
    protected Connection connection;
    protected Session session;
    protected MessageProducer producer;

    @Before
    public void initializeJms() throws Exception {
        ConnectionFactory connectionFactory = lookup("jms/RemoteConnectionFactory");
        Queue queue = lookup("rd/queues/RealDolmenQueue");
        connection = connectionFactory.createConnection("administrator", "Azerty123!");
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(queue);
    }

    @After
    public void destroyJms() throws JMSException {
        if(connection != null) {
            connection.close();
        }
    }
}
