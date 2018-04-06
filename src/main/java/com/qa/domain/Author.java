package com.qa.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name="Author")
@Table(name="author")
public class Author {

	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	private String firstName;
	private String surname;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="author", fetch=FetchType.LAZY)
	private List<Book> bookList = new ArrayList<>();;
	
	public Author() {}
	
	public Author(Long id) {
		this.id = id;
	}
	
	public Author(String firstName, String surname, List<Book> bookList) {
		super();
		this.firstName = firstName;
		this.surname = surname;
		this.bookList = bookList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<Book> getBookList() {
		return new ArrayList<Book> (bookList);
	}
	
	public void addBook(Book book) {
		if (bookList.contains(book)){
			return ;
		} else {
			bookList.add(book);
			book.setAuthor(this);
		}
	}

	public void removeBook(Book book) {
		if (!bookList.contains(book)){
			return ;
		} else {
			bookList.remove(book);
			book.setAuthor(null);
		}
	}
}
