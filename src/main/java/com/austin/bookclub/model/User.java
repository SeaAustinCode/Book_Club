package com.austin.bookclub.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "name is required.")
	@Size(min = 3, max = 50, message = "name must be between 3 and 50 characters.")
	private String name;
	
	@NotEmpty(message = "Email is required.")
	@Email(message = "Must provide a valid email address.")
	private String email;
	
	@NotEmpty(message = "Password is required.")
	@Size(min = 8, max = 128, message = "Password must be between 8 - 128 characters")
	private String password;
	
	@Transient // not stored anywhere
	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirm;
	
	// ONE to Many relationship - ONE user may have MANY books
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Book> books;
	
	// Constructor
	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Book> getBooks() { //might not need these last two
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
