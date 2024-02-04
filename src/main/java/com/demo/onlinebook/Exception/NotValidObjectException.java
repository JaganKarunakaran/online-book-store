package com.demo.onlinebook.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class NotValidObjectException extends RuntimeException {

	private static final long serialVersionUID = -1982314982439064545L;
	
	public NotValidObjectException(String msg) {
		super(msg);
	}

}
