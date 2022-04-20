package com.austin.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.austin.bookclub.model.Book;
import com.austin.bookclub.model.User;
import com.austin.bookclub.service.BookService;
import com.austin.bookclub.service.UserService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	// SHOW --- CREATE BOOK PAGE 
	@GetMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User loggedInUser = userService.findOne(userId);
			model.addAttribute("loggedInUser", loggedInUser);
			List<Book>allBooks = bookService.allBooks();
			model.addAttribute("allBooks", allBooks);
			return "addbook.jsp";
		}
		}
	// PROCESS --- Book Form
	@PostMapping("/books/new/method")
	public String createBookMethod(@Valid @ModelAttribute("book") Book book, 
			BindingResult result, 
			HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
		if (result.hasErrors()) {
			return "addbook.jsp";
		} else {
			bookService.createBook(book);
			return "redirect:/books";
			}
		}
	}
	
	// SHOW --- Books dashboard with all books and all users 
	@GetMapping("/books")
	public String dashboard(@ModelAttribute("book") Book book, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else {
			User loggedInUser = userService.findOne(userId);
			model.addAttribute("loggedInUser", loggedInUser);
			List<Book>allBooks = bookService.allBooks();
			model.addAttribute("allBooks", allBooks);
			return "books.jsp";
		}
	}
	
	// SHOW --- Individual books page with a book and all it's details
	@RequestMapping("/books/{id}")
	public String showIndividualBook(@PathVariable("id")Long id, Model model, HttpSession session) {
			Long userId = (Long) session.getAttribute("user_id");
			if (userId == null) {
				return "redirect:/";
			} else {
			User loggedInUser = userService.findOne(userId);
			model.addAttribute("loggedInUser", loggedInUser);
			Book book = bookService.findBook(id);
			model.addAttribute("book", book);
			
			return "showbook.jsp";
		}
	}
	
	// RENDER EDIT PAGE -- this one shows the edit page and that's really it 
	@GetMapping("/books/{id}/edit")
	public String editIndividualBook(@PathVariable("id")Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";
		} else { //routeguard for edit
			Book book = bookService.findBook(id);
			if (userId.equals(book.getUser().getId())){
				model.addAttribute("book", book);
				return "editbook.jsp";
			}
				return "redirect:/books";
		}
	}
	
	// Update the book
	@PutMapping("/books/{id}/method")
	public String updateBook(@Valid @ModelAttribute("book")Book book, BindingResult result, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		if (userId == null) {
			return "redirect:/";	
		} else if  
			(result.hasErrors()) {
			return "editbook.jsp";
		} else {
			bookService.updateBook(book);
			User loggedInUser = userService.findOne(userId);
			model.addAttribute("loggedInUser", loggedInUser);
			return "redirect:/books";
		}
	}
	// DELETE ---> if you remove line 140 (commented line) it will remove the user but not the book. 
//	@GetMapping("/remove/book/{id}/method")
//	public String removeFromUser(@PathVariable("id") Long bookId, HttpSession session) {
//		//find the book 
//		Book thisBookToRemove = bookService.findBook(bookId);
////		thisBookToRemove.setUser(null); // this is setting the user to null (checking out a book for example) 
//		bookService.updateBook(thisBookToRemove);
//		
//		//find the user
//		Long userId = (Long) session.getAttribute("user_id");
////		User user = userService.findOne(userId);
////		user.getBooks().remove(thisBookToRemove);
////		userService.update(user);
//		bookService.deleteBook(bookId); // this will delete the book 
//		return "redirect:/books";
//	}
		
//	// DELETE
//		@GetMapping("/remove/book/{id}/method")
//		public String deleteBook(@PathVariable("id") Long bookId, HttpSession session) {
//			if (userId == null) {
//				return "redirect:/";
//			} else { //routeguard for edit
//			//find the book 
//			Book thisBookToRemove = bookService.findBook(bookId);
//			bookService.updateBook(thisBookToRemove);
//			//find the user
//			Long userId = (Long) session.getAttribute("user_id");
//			bookService.deleteBook(bookId); // this will delete the book 
//			return "redirect:/books";
		
//	}
	
    @DeleteMapping("/remove/book/{id}/method")
    public String destroy(@PathVariable("id") Long id, HttpSession session) {
    	Long userId = (Long) session.getAttribute("user_id");
    	if (userId == null) {
			return "redirect:/";
    	} else {
    		bookService.deleteBook(id);
    		return "redirect:/books";
    }


			}
}
