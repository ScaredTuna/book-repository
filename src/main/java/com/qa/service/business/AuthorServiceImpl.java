package com.qa.service.business;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.service.repository.AuthorRepository;

public class AuthorServiceImpl implements AuthorService {

	private static final Logger LOGGER = Logger.getLogger(AuthorService.class);

	@Inject
	private AuthorRepository repo;

	@Override
	public String getAllAuthors() {
		LOGGER.info("In AuthorServiceImpl getAllAuthors ");
		return repo.getAllAuthors();
	}

	@Override
	public String addAuthor(String author) {
		LOGGER.info("In AuthorServiceImpl createAuthor ");
		return repo.createAuthor(author);
	}

	@Override
	public String updateAuthor(Long id, String author) {
		LOGGER.info("In AuthorServiceImpl updateAuthor ");
		return repo.updateAuthor(id, author);
	}

	@Override
	public String deleteAuthor(Long id) {
		LOGGER.info("In AuthorServiceImpl deleteAuthor ");
		return repo.deleteAuthor(id);

	}

	public void setRepo(AuthorRepository repo) {
		this.repo = repo;
	}

}
