package com.demo.onlinebook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.onlinebook.Entity.Books;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

	Optional<Books> findByIsbnNum(Long isbnNum);

	Optional<Books> findByTitle(String title);

}
