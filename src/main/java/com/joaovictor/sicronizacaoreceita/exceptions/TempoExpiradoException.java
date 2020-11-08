package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT)
public class TempoExpiradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TempoExpiradoException(String message) {
		super(message);
	}
	
	public TempoExpiradoException(String message, Throwable cause) {
		super(message, cause);
	}
}
