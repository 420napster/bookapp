package com.pratyush.spring.dao;

import java.util.List;

import com.pratyush.spring.model.Book;

public interface BookDAO {
	//Save the record
	long save(Book book);
	
	//Get a single record
	Book get(long id);
	
	//Get a all records
	List<Book> list();
	
	//Update a record
	void update(long id, Book book);
	
	//Delete a record
	void delete(long id);

}
