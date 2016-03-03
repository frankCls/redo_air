package com.realdolmen.course.controller;

import com.realdolmen.course.domain.Book;
import com.realdolmen.course.persistence.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class BookController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    BookRepository repository;

    public List<Book> getAllBooks() {
        logger.debug("Retrieving all books");
        return repository.findAll();
    }

    public void remove(int bookId) {
        logger.debug("Removing book");
        repository.remove(bookId);
    }
}
