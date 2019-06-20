package com.prtayush.spring.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pratyush.spring.model.Book;
import com.pratyush.spring.service.BookService;

public class BookCache {

	@Autowired
	private static BookService bookService;

	public static Map<Long, Book> bookCache = new HashMap<Long, Book>();

	public static Book getBook(Long bookId) {
		Book book = bookCache.get(bookId);
		if (book == null) {
			book = bookService.get(bookId);
			bookCache.put(book.getId(), book);
		}
		return book;
	}

	public static void setBook(Book book) {
		bookCache.put(book.getId(), book);
	}

	public static void removeBook(Book book) {
		bookCache.remove(book.getId());
	}

	public static void updateBook(Book book) {
		bookCache.remove(book.getId());
		bookCache.put(book.getId(), book);
	}

	public static List<Book> getBooks() {
		List<Book> bookList;

		if (bookCache.isEmpty()) {
			bookList = bookService.list();
			bookList.forEach(book -> bookCache.put(book.getId(), book));
		} else {
			bookList = new ArrayList<Book>(bookCache.values());
		}
		return bookList;
	}
}
