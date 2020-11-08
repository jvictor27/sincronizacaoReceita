package com.joaovictor.sicronizacaoreceita.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joaovictor.sicronizacaoreceita.exceptions.ErroGenericoException;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroInternoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoAutenticadoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoAutorizadoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoEncontradoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.joaovictor.sicronizacaoreceita.exceptions.ServicoIndisponivelException;
import com.joaovictor.sicronizacaoreceita.exceptions.TempoExpiradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<StandardError> naoEncontrado(NaoEncontradoException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(RequisicaoInvalidaException.class)
	public ResponseEntity<StandardError> requisicaoInvalida(RequisicaoInvalidaException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(NaoAutorizadoException.class)
	public ResponseEntity<StandardError> naoAutorizado(NaoAutorizadoException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
	}
	
	@ExceptionHandler(NaoAutenticadoException.class)
	public ResponseEntity<StandardError> naoAutenticado(NaoAutenticadoException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(TempoExpiradoException.class)
	public ResponseEntity<StandardError> tempoExpirado(TempoExpiradoException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.REQUEST_TIMEOUT.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(err);
	}

	@ExceptionHandler(ErroInternoException.class)
	public ResponseEntity<StandardError> erroInterno(ErroInternoException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
	
	@ExceptionHandler(ServicoIndisponivelException.class)
	public ResponseEntity<StandardError> servicoIndisponivel(ServicoIndisponivelException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.SERVICE_UNAVAILABLE.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
	}
	
	@ExceptionHandler(ErroGenericoException.class)
	public ResponseEntity<StandardError> erroGenerico(ErroGenericoException e, HttpServletRequest request) {
		
		StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}