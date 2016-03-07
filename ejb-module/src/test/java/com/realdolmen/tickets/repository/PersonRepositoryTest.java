package com.realdolmen.tickets.repository;

import com.realdolmen.tickets.domain.Person;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

public class PersonRepositoryTest {

    private static PersonRepositoryBean pr;
    private static EntityManagerFactory emf;
    private EntityTransaction transaction;

    @BeforeClass
    public static void setupBeforeClass() {
        emf = Persistence.createEntityManagerFactory("Test");
        pr = new PersonRepositoryBean();
    }

    @Before
    public void setup() {
        pr.em = emf.createEntityManager();
        transaction = pr.em.getTransaction();
        transaction.begin();
    }

    @After
    public void tearDown() {
        transaction.rollback();
        pr.em.close();
    }

    @AfterClass
    public static void teardownAfterClass() {
        emf.close();
    }


    @Test
    public void shouldSaveAPassenger() {
        Person p = new Person("Theo", "Tester");
        assertNull("Person ID is supposed to be null before saving", p.getId());
        pr.save(p);
        pr.em.flush();
        assertNotNull("Person ID is not supposed to be null after saving", p.getId());
    }

    @Test
    public void shouldReturnAllPassengers() {
        List<Person> persons = pr.findAll();
        assertNotNull(persons);
        assertEquals(2, persons.size());
    }

    @Test
    public void shouldReturnAPassenger() {
        Person p = pr.findById(1L);
        assertNotNull(p);
        assertEquals(new Long(1L), p.getId());
        assertEquals("John", p.getFirstName());

    }
}
