package com.demo.onlinebook.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.onlinebook.Entity.Author;
import com.demo.onlinebook.Exception.ResourceNotFoundException;
import com.demo.onlinebook.Repository.AuthorRepository;
import com.demo.onlinebook.Service.AuthorService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author findAuthorById(Long authorId) {
		log.info("authorSevice -> findAuthorById service Starts");

		Author author = authorRepository.findById(authorId).orElseThrow(() ->  new ResourceNotFoundException("Author", "Author Id", authorId));
		
		log.info("authorSevice -> findAuthorById service Ends");
		return author;
	}

}
