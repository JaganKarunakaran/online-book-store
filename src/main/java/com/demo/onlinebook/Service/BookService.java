package com.demo.onlinebook.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.onlinebook.Entity.Books;

@Service
public interface BookService {

	public Books createBook(Books book);

	public List<Books> listOfBooks();

	public Books findByIsbnNum(Long isbnNum);

	public Books updateBook(Long isbnNum, Books book);

	public void deleteBook(Long isbnNum);

	public Books submitReview(Long isbnNum, String review);
}
