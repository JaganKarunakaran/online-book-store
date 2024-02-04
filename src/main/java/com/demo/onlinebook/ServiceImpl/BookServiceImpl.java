package com.demo.onlinebook.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.onlinebook.Entity.Author;
import com.demo.onlinebook.Entity.Books;
import com.demo.onlinebook.Exception.NotValidObjectException;
import com.demo.onlinebook.Exception.ResourceNotFoundException;
import com.demo.onlinebook.Repository.AuthorRepository;
import com.demo.onlinebook.Repository.BookRepository;
import com.demo.onlinebook.Service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Books createBook(Books book) {
		log.info("bookService -> createBook service Starts");
		if(validBook(book)) {
			Author author = authorRepository.findById(book.getAuthorId()).orElse(null);
			if (author == null && book.getAuthor() != null ) {
				author = book.getAuthor();
				authorRepository.save(author);
				book.setAuthorId(author.getAuthorId());
			}
			book.setAuthorId(book.getAuthorId());
			bookRepository.save(book);
			log.info("bookService -> createBook service Ends");
		}
		return book;
	}

	@Override
	public List<Books> listOfBooks() {
		log.info("bookService -> listOfBooks service Starts");
		List<Books> bookList = bookRepository.findAll();
		log.info("bookService -> listOfBooks service Ends");
		return bookList;
	}

	@Override
	public Books findByIsbnNum(Long isbnNum) {
		log.info("bookService -> findByIsbnNum service Starts");
		Books book = bookRepository.findByIsbnNum(isbnNum).orElseThrow(() -> new ResourceNotFoundException("Book", "ISBN Number", isbnNum));
		log.info("bookService -> findByIsbnNum service Ends");
		return book;
	}

	@Override
	public Books updateBook(Long isbnNum, Books bookDetails) {
		log.info("bookService -> updateBook service Starts");
		Books book = bookRepository.findByIsbnNum(isbnNum).orElseThrow(() -> new ResourceNotFoundException("Book", "ISBN Number", isbnNum));
		if(bookDetails.getTitle() != null) {
			book.setTitle(bookDetails.getTitle());
		}
		if(bookDetails.getIsbnNum() != null) {
			book.setIsbnNum(bookDetails.getIsbnNum());
		}
		if(bookDetails.getAuthor() != null) {
			if(bookDetails.getAuthor().getAuthorId() != null) {
				Author author = authorRepository.findById(bookDetails.getAuthor().getAuthorId()).orElse(null);
				if(author == null) {
					author = bookDetails.getAuthor();
					authorRepository.save(author);
				}else {
					author.setAuthorName(bookDetails.getAuthor().getAuthorName());
					authorRepository.save(author);
				}
				book.setAuthorId(author.getAuthorId());
			}
			book.setAuthor(bookDetails.getAuthor());
		}
		if(bookDetails.getAuthorId() != null) {
			book.setAuthorId(bookDetails.getAuthorId());
		}
		Books updateBook = bookRepository.save(book);
		log.info("bookService -> updateBook service Ends");
		return updateBook;
	}

	@Override
	public void deleteBook(Long isbnNum) {
		log.info("bookService -> deleteBook service Starts");

		Books book = bookRepository.findByIsbnNum(isbnNum).orElseThrow(() -> new ResourceNotFoundException("Book", "ISBN Number", isbnNum));
		log.info("bookService -> deleteBook service Ends");
		bookRepository.delete(book);
	}

	@Override
	public Books submitReview(Long isbnNum, String review) {
		log.info("bookService -> submitReview service Starts");
		Books book = bookRepository.findByIsbnNum(isbnNum).orElseThrow(() -> new ResourceNotFoundException("Book", "ISBN Number", isbnNum));

		book.setReview(review);
		Books updateBook = bookRepository.save(book);
		log.info("bookService -> submitReview service Ends");
		return updateBook;
	}
	
	public boolean validBook(Books book) {
		boolean valid = true;
		Books existBook = bookRepository.findByIsbnNum(book.getIsbnNum()).orElse(null);
		if(existBook != null) {
			log.info("Existing ISBN number");
			valid = false;
			throw new NotValidObjectException("Book with this ISBN number already EXISTS.");
		}
		if(book.getIsbnNum().toString().trim().length() != 13) {
			log.info("Invalid ISBN number");
			valid = false;
			throw new NotValidObjectException("create Book with Valid ISBN number");
		}
		if(book.getAuthor() == null) {
			log.info("Enter Author Details");
			valid = false;
			throw new NotValidObjectException("create Book with Author Details");
		}
		if(book.getAuthorId() == null) {
			log.info("Enter Author Id");
			valid = false;
			throw new NotValidObjectException("create Book with Author Id");
		}
		return valid;
	}
}
