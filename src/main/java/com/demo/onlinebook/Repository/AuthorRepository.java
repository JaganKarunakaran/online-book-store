package com.demo.onlinebook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.onlinebook.Entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	Optional<Author> findByAuthorName(String authorName);

}
