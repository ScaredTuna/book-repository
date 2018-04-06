package com.qa.service.business;

public interface AuthorService {

	String getAllAuthors();
	
	String addAuthor(String author);
	
	String updateAuthor(Long id, String author);
	
	String deleteAuthor(Long id);
	
}
