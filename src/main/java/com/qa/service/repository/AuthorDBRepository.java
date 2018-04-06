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

import com.qa.domain.Author;
import com.qa.service.business.AuthorService;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AuthorDBRepository implements AuthorRepository{
	
	private static final Logger LOGGER = Logger.getLogger(AuthorService.class);
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllAuthors() {
		LOGGER.info("In AuthorDBRepository getAllAuthors ");
		Query query = manager.createQuery("SELECT a FROM Author a left join fetch a.bookList");
		Collection<Author> authors = (Collection<Author>) query.getResultList();
		return util.getJSONForObject(authors);
	}

	@Override
	@Transactional(REQUIRED)
	public String createAuthor(String author) {
		LOGGER.info("In AuthorDBRepository createAuthor ");
		Author anAuthor = util.getObjectForJSON(author, Author.class);
		manager.persist(anAuthor);
		return "{\"message\": \"author has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAuthor(Long id, String authorToUpdate) {
		LOGGER.info("In AuthorDBRepository updateAuthor ");
		Author updatedAuthor = util.getObjectForJSON(authorToUpdate, Author.class);
		updatedAuthor.setId(id);
		if (authorToUpdate != null) {
			manager.merge(updatedAuthor);
			return "{\"message\": \"author sucessfully updated\"}";
		}
		return "{\"message\": \"author failed to update\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAuthor(Long id) {
		LOGGER.info("In AuthorDBRepository deleteAuthor ");
		Author authorInDB = findAuthor(id);
		if (authorInDB != null) {
			manager.remove(authorInDB);
			return "{\"message\": \"author sucessfully deleted\"}";
		}
		return "{\"message\": \"author failed to delete\"}";
	}
	
	private Author findAuthor(Long id) {
		return manager.find(Author.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}
}
