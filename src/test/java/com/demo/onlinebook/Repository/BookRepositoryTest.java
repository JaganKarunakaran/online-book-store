package com.demo.onlinebook.Repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.onlinebook.Entity.Books;
import com.demo.onlinebook.Repository.BookRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;
	
	@Test
	public void bookRepository_SaveAll_ReturnSavedBook() {
		
		Books book = Books.builder().bookId(9L).authorId(16L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		
		Books savedBook = bookRepository.save(book);
		
		Assertions.assertThat(savedBook).isNotNull();
		Assertions.assertThat(savedBook.getBookId()).isGreaterThan(0);
	}
	
	@Test
	public void bookRepository_FindAll_ReturnMoreThanOneBook() {
		Books book = Books.builder().bookId(1L).authorId(1L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		Books book2 = Books.builder().bookId(2L).authorId(2L).isbnNum(1234567891012L)
				.title("Harry Potter").build();
		
		bookRepository.save(book);
		bookRepository.save(book2);
		
		List<Books> bookList =  bookRepository.findAll();
		
		Assertions.assertThat(bookList).isNotNull();
		Assertions.assertThat(bookList.size()).isGreaterThanOrEqualTo(2);
		
	}
	
	@Test
	public void bookRepository_FindById_ReturnsOneBook() {
		Books book = Books.builder().bookId(1L).authorId(1L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		bookRepository.save(book);

		Books savedBook = bookRepository.findById(book.getBookId()).get();
		
		Assertions.assertThat(savedBook).isNotNull();

	}
	
	@Test
	public void bookRepository_FindByIsbn_ReturnsOneBook() {
		Books book = Books.builder().bookId(1L).authorId(1L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		bookRepository.save(book);

		Books savedBook = bookRepository.findByIsbnNum(book.getIsbnNum()).get();
		
		Assertions.assertThat(savedBook).isNotNull();

	}
	
	@Test
	public void bookRepository_UpdateBooks_ReturnsBookNotNull() {
		Books book = Books.builder().bookId(1L).authorId(1L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		bookRepository.save(book);

		Books savedBook = bookRepository.findById(book.getBookId()).get();
		
		savedBook.setTitle("Harry Potter");
		savedBook.setIsbnNum(1234567891012L);
		savedBook.setReview("Good Book");
		
		Books updatedBook = bookRepository.save(savedBook);
		
		Assertions.assertThat(updatedBook).isNotNull();
		Assertions.assertThat(updatedBook.getReview()).isNotNull();

	}
	
	@Test
	public void bookRepository_DeleteBook_ReturnsEmpty() {
		Books book = Books.builder().bookId(1L).authorId(1L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		
		bookRepository.save(book);

		bookRepository.deleteById(book.getBookId());
		
		
		Optional<Books> bookReturn = bookRepository.findById(book.getBookId());
		
		Assertions.assertThat(bookReturn).isEmpty();
	}
}
