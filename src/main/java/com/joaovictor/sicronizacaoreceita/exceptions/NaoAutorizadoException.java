package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class NaoAutorizadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoAutorizadoException(String message) {
		super(message);
	}
	
	public NaoAutorizadoException(String message, Throwable cause) {
		super(message, cause);
	}
}
