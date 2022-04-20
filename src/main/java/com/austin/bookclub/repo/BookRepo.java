package com.austin.bookclub.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.austin.bookclub.model.Book;

@Repository
public interface BookRepo extends CrudRepository<Book, Long>{
	List<Book> findAll(); 
}
