package com.demo.onlinebook.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.onlinebook.Entity.Author;
import com.demo.onlinebook.Entity.Books;
import com.demo.onlinebook.Exception.ResourceNotFoundException;
import com.demo.onlinebook.Repository.AuthorRepository;
import com.demo.onlinebook.Repository.BookRepository;
import com.demo.onlinebook.Service.BookService;

import lombok.extern.slf4j.Slf4j;

@Controller
@ResponseBody
@Slf4j
//@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping("/books")
	public ResponseEntity<List<Books>> getAllBooks() {
		log.info("Book Controller -> getAllBooks is accessed");
		return ResponseEntity.status(HttpStatus.OK).body(bookService.listOfBooks());
	}

	@PostMapping("/books")
	public ResponseEntity<Map<String, Object>> createBook(@RequestBody Books book) {
		log.info("Book Controller -> createBook is accessed");
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Books newBook = bookService.createBook(book);

		responseMap.put("Message", "The book is added to the inventory.");
		responseMap.put("createdBook", newBook);
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

	@GetMapping("/books/{isbn}")
	public ResponseEntity<Books> getBookByIsbnNum(@PathVariable(value = "isbn") Long isbnNum) {
		log.info("Book Controller -> getBookByIsbnNum is accessed");
		Books book = bookService.findByIsbnNum(isbnNum);

		return ResponseEntity.status(HttpStatus.OK).body(book);
	}

	@PutMapping("/books/{isbn}")
	public ResponseEntity<Map<String, Object>> updateBook(@PathVariable(value = "isbn") Long isbnNum,
			@RequestBody Books bookDetails) {
		log.info("Book Controller -> updateBook is accessed");
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Books updatedBook = bookService.updateBook(isbnNum, bookDetails);

		responseMap.put("Message", "The book is Updated");
		responseMap.put("createdBook", updatedBook);
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

	@DeleteMapping("/books/{isbn}")
	public ResponseEntity<Map<String, Object>> deleteBook(@PathVariable(value = "isbn") Long isbnNum) {
		log.info("Book Controller -> deleteBook is accessed");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		bookService.deleteBook(isbnNum);

		responseMap.put("Message", "The book is removed from the inventory.!!");
		return ResponseEntity.ok().body(responseMap);
	}

	@PostMapping("/books/{isbn}/reviews")
	public ResponseEntity<Map<String, Object>> submitReviewForBook(@PathVariable(value = "isbn") Long isbnNum,
			@RequestBody String review) {
		log.info("Book Controller -> submitReviewForBook is accessed");
		Map<String, Object> responseMap = new HashMap<String, Object>();

		Books updatedBook = bookService.submitReview(isbnNum, review);

		responseMap.put("Message", "The review is Submitted");
		responseMap.put("Book", updatedBook);
		return ResponseEntity.status(HttpStatus.OK).body(responseMap);
	}

}
