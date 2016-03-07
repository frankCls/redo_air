package com.realdolmen.tickets.jms;

import com.realdolmen.tickets.domain.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class PersonMDBIntegrationTest {

    Context context;
    ConnectionFactory connectionFactory;
    Queue queue;

    @Before
    public void setup() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.PROVIDER_URL, "remote://localhost:4447");
        properties.put(Context.SECURITY_PRINCIPAL, "administrator");
        properties.put(Context.SECURITY_CREDENTIALS, "Azerty123!");
        context = new InitialContext(properties);
        connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        queue = (Queue) context.lookup("jms/queue/test");
    }

    @Test
    @Ignore
    public void shouldSendAPassengerMessage() {
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection("administrator","Azerty123!");
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(queue);
            ObjectMessage message = session.createObjectMessage(new Person("Theo", "Tester"));
            producer.send(message);
        } catch(JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
