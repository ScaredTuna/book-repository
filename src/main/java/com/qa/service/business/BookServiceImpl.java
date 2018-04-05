package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.BookRepository;

public class BookServiceImpl implements BookService {

	private static final Logger LOGGER = Logger.getLogger(BookService.class);

	@Inject
	private BookRepository repo;

	public String getAllBooks() {
		LOGGER.info("In BookServiceImpl getAllBooks ");
		return repo.getAllBooks();
	}

	public String addBook(String book) {
		LOGGER.info("In BookServiceImpl createBook ");
		return repo.createBook(book);
	}

	public String updateBook(Long id, String book) {
		LOGGER.info("In BookServiceImpl updateBook ");
		return repo.updateBook(id, book);
	}

	public String deleteBook(Long id) {
		LOGGER.info("In BookServiceImpl deleteBook ");
		return repo.deleteBook(id);

	}

	public void setRepo(BookRepository repo) {
		this.repo = repo;
	}

}
