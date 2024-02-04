package com.demo.onlinebook.Service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.onlinebook.Entity.Books;
import com.demo.onlinebook.Repository.AuthorRepository;
import com.demo.onlinebook.Repository.BookRepository;
import com.demo.onlinebook.ServiceImpl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private BookServiceImpl bookService;
	
	@Test
	public void BookService_CreateService_ReturnBook() {
		Books book = Books.builder().bookId(9L).authorId(16L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		
		when(bookRepository.save(Mockito.any(Books.class))).thenReturn(book);
		
		Books savedBook = bookService.createBook(book);
		
		Assertions.assertThat(savedBook).isNotNull();
	}
	
	@Test
	public void BookService_FindAllBooks_ReturnBooks() {
		Books book = Books.builder().bookId(1L).authorId(1L).isbnNum(1234567891011L)
				.title("Percy Jackson").build();
		Books book2 = Books.builder().bookId(2L).authorId(2L).isbnNum(1234567891012L)
				.title("Harry Potter").build();
		
		when(bookRepository.findAll()).thenReturn(List.of(book,book2));
		
		List<Books> bookList = bookService.listOfBooks();
		
		Assertions.assertThat(bookList).isNotNull();
		Assertions.assertThat(bookList.size()).isEqualTo(2);
	}

}
