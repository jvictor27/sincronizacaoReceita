package com.joaovictor.sicronizacaoreceita.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
public class ServicoIndisponivelException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServicoIndisponivelException(String message) {
		super(message);
	}
	
	public ServicoIndisponivelException(String message, Throwable cause) {
		super(message, cause);
	}
}
