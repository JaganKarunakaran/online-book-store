package com.demo.onlinebook.Repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.onlinebook.Entity.Author;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class AuthorRepositoryTest {


	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	public void authorRepository_SaveAll_ReturnSavedAuthor() {
		
		Author author = Author.builder().authorId(9L).authorName("Rick jordan").build();
		
		Author savedAuthor = authorRepository.save(author);
		
		Assertions.assertThat(savedAuthor).isNotNull();
		Assertions.assertThat(savedAuthor.getAuthorId()).isGreaterThan(0);
	}
	
	@Test
	public void authorRepository_FindAll_ReturnMoreThanOneAuthors() {
		Author author = Author.builder().authorId(9L).authorName("Rick jordan").build();
		Author author2 = Author.builder().authorId(9L).authorName("Michael jordan").build();
		 
		authorRepository.save(author);
		authorRepository.save(author2);
		
		List<Author> authorList =  authorRepository.findAll();
		
		Assertions.assertThat(authorList).isNotNull();
		Assertions.assertThat(authorList.size()).isGreaterThanOrEqualTo(2);
		
	}
	
	@Test
	public void authorRepository_FindById_ReturnsOneAuthor() {
		Author author = Author.builder().authorId(9L).authorName("Rick jordan").build();
		authorRepository.save(author);

		Author savedBook = authorRepository.findById(author.getAuthorId()).get();
		
		Assertions.assertThat(savedBook).isNotNull();

	}
	
	@Test
	public void authorRepository_FindByAuthorName_ReturnsOneBook() {
		Author author = Author.builder().authorId(9L).authorName("Rick jordan").build();
		
		authorRepository.save(author);

		Author savedAuthor = authorRepository.findByAuthorName(author.getAuthorName()).get();
		
		Assertions.assertThat(savedAuthor).isNotNull();

	}


}
