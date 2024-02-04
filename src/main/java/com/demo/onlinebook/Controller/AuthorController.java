package com.demo.onlinebook.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.onlinebook.Entity.Author;
import com.demo.onlinebook.Service.AuthorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@ResponseBody
@Slf4j
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;

	@GetMapping("/author/{authorId}")
	public ResponseEntity<Author> getAuthorByAuthorId(@PathVariable(value = "authorId") Long authorId) {
		log.info("Author Controller -> getAuthorByAuthorId is accessed");
		return ResponseEntity.status(HttpStatus.OK).body(authorService.findAuthorById(authorId));
	}

}
