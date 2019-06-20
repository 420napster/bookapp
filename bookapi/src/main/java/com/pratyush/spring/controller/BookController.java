package com.pratyush.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import com.pratyush.spring.model.Book;
import com.pratyush.spring.service.BookService;

@CrossOrigin("*")
@RestController
public class BookController {

	@Autowired
	private BookService bookService;
	
	// Get all the books
	@GetMapping("/api/book")
	public ResponseEntity<List<Book>> list(){
		List<Book> list = bookService.list();
		return ResponseEntity.ok().body(list);
	}
	
	//Get a single book
	@GetMapping("/api/book/{id}")
	public ResponseEntity<Book> get(@PathVariable("id") long id){
		Book book = bookService.get(id);
		return ResponseEntity.ok().body(book);
	}
	
	//Save the book
	@PostMapping("/api/book")
	public ResponseEntity<?> save(@RequestBody Book book){
		long id = bookService.save(book);
		return ResponseEntity.ok().body("Book created with id: " + id);
	}
	
	//Update a book
	@PutMapping("/api/book/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Book book){
		bookService.update(id, book);
		return ResponseEntity.ok().body("Book has been updated");
	}
	
	//Delete a book
	@DeleteMapping("/api/book/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		bookService.delete(id);
		return ResponseEntity.ok().body("Book has been deleted");
	}
	
	// Get all the fake books
	@GetMapping("/api/fakebook")
	public ResponseEntity<Book> getFakeBook(){
		Faker faker = new Faker();
		Book book = new Book();
		book.setId(585l);
		book.setAuthor(faker.book().author());
		book.setTitle(faker.book().title());
		return ResponseEntity.ok().body(book);
	}
}
