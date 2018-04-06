package com.qa.service.repository;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.domain.Author;
import com.qa.util.JSONUtil;

@Alternative
@ApplicationScoped
public class AuthorMapRepository implements AuthorRepository {

	private final Long INITIAL_COUNT = 1L;
	private Map<Long, Author> authorMap;
	private Long ID;

	@Inject
	private JSONUtil util;

	public AuthorMapRepository() {
		this.authorMap = new HashMap<Long, Author>();
		ID = INITIAL_COUNT;
		initAuthorMap();
	}

	@Override
	public String getAllAuthors() {
		return util.getJSONForObject(authorMap.values());
	}

	@Override
	public String createAuthor(String author) {
		ID++;
		Author newAuthor = util.getObjectForJSON(author, Author.class);
		authorMap.put(ID, newAuthor);
		return author;
	}

	@Override
	public String updateAuthor(Long id, String authorToUpdate) {
		Author newAuthor = util.getObjectForJSON(authorToUpdate, Author.class);
		authorMap.put(id, newAuthor);
		return authorToUpdate;
	}

	@Override
	public String deleteAuthor(Long id) {
		authorMap.remove(id);
		return "{\"message\": \"author sucessfully removed\"}";
	}

	private void initAuthorMap() {
		Author author = new Author("Joe", "Bloggs", null);
		authorMap.put(1L, author);
	}

}
