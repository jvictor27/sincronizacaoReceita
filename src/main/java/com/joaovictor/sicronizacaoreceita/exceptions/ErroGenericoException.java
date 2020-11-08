package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ErroGenericoException extends ResponseStatusException {

	private String message;

	public ErroGenericoException(String message) {
		this(HttpStatus.INTERNAL_SERVER_ERROR, message, message);
	}
	
	public ErroGenericoException(HttpStatus status, String reason, String cause) {
		super(status, reason);
		this.message = reason;
	}
}
