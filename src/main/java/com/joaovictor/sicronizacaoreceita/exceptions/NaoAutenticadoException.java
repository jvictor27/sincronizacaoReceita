package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class NaoAutenticadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoAutenticadoException(String message) {
		super(message);
	}
	
	public NaoAutenticadoException(String message, Throwable cause) {
		super(message, cause);
	}
}
