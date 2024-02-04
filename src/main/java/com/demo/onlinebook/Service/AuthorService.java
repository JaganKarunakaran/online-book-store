package com.demo.onlinebook.Service;

import org.springframework.stereotype.Service;

import com.demo.onlinebook.Entity.Author;

@Service
public interface AuthorService {

	public Author findAuthorById(Long authorId);

}
