package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoEncontradoException(String message) {
		super(message);
	}
	
	public NaoEncontradoException(String message, Throwable cause) {
		super(message, cause);
	}
}
