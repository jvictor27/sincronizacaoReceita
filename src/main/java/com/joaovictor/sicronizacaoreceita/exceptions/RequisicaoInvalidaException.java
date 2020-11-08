package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RequisicaoInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RequisicaoInvalidaException(String message) {
		super(message);
	}
	
	public RequisicaoInvalidaException(String message, Throwable cause) {
		super(message, cause);
	}
}
