package com.qa.service.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.domain.Book;
import com.qa.service.business.BookService;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class BookDBRepository implements BookRepository{
	
	private static final Logger LOGGER = Logger.getLogger(BookService.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllBooks() {
		LOGGER.info("In BookDBRepository getAllBooks ");
		Query query = manager.createQuery("SELECT a FROM Book a");
		Collection<Book> books = (Collection<Book>) query.getResultList();
		return util.getJSONForObject(books);
	}

	@Override
	@Transactional(REQUIRED)
	public String createBook(String book) {
		LOGGER.info("In BookDBRepository createBook ");
		Book aBook = util.getObjectForJSON(book, Book.class);
		manager.persist(aBook);
		return "{\"message\": \"book has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateBook(Long id, String bookToUpdate) {
		LOGGER.info("In BookDBRepository updateBook ");
		Book updatedBook = util.getObjectForJSON(bookToUpdate, Book.class);
		updatedBook.setId(id);
		if (bookToUpdate != null) {
			manager.merge(updatedBook);
			return "{\"message\": \"book sucessfully updated\"}";
		}
		return "{\"message\": \"book failed to update\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteBook(Long id) {
		LOGGER.info("In BookDBRepository deleteBook ");
		Book bookInDB = findBook(id);
		if (bookInDB != null) {
			manager.remove(bookInDB);
			return "{\"message\": \"book sucessfully deleted\"}";
		}
		return "{\"message\": \"book failed to delete\"}";
	}
	
	private Book findBook(Long id) {
		return manager.find(Book.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}
