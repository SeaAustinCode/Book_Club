package com.austin.bookclub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.austin.bookclub.model.Book;
import com.austin.bookclub.repo.BookRepo;

@Service
public class BookService {
	
	// CRUD
	
		@Autowired // a simpler and easier way to do dependency injection
		private BookRepo bookRepo;

		// READ ALL Book //
		public List<Book> allBooks() {
			return bookRepo.findAll(); 
		}

		// CREATE Book
		public Book createBook(Book book) {

			return bookRepo.save(book);
		}
		
		// Retrieve one Book by id
		public Book findBook(Long id) {
			Optional<Book> optionalBook = bookRepo.findById(id);
			if (optionalBook.isPresent()) {
				return optionalBook.get();
			} else {
				return null;
			}
		}
		
		// UPDATE Book
			public Book updateBook(Book book) {
				return bookRepo.save(book);
			}

		// DELETE Book
		public void deleteBook(Long id) {
			bookRepo.deleteById(id);
		}
}
			


