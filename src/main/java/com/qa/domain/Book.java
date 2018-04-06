package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	private String title;
	private String isbn;
	private String genre;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "Author_id")
	private Author author;
	
	public Book() {}

	public Book(String title, String isbn, String genre) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.genre = genre;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
}
