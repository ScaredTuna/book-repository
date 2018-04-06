package com.qa.service.repository;

public interface AuthorRepository {

	String getAllAuthors();
	
	String createAuthor(String author);
	
	String updateAuthor(Long id, String authorToUpdate);
	
	String deleteAuthor(Long id);
	
}
