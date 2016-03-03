package com.realdolmen.course.persistence;

import com.realdolmen.course.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class BookRepository implements RemoteBookRepository, Serializable {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager entityManager;

    public List<Book> findAll() {
        logger.debug("Find all books");
        return entityManager.createQuery("select b from Book b", Book.class).getResultList();
    }

    public void remove(int id) {
        logger.debug("Remove book by id " + id);
        entityManager.remove(entityManager.getReference(Book.class, id));
    }

    public Book findById(int id) {
        logger.debug("Find book by id " + id);
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        logger.debug("Update book");
        entityManager.merge(book);
    }
}
