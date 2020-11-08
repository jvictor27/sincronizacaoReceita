package com.joaovictor.sicronizacaoreceita.feing.utils;

import org.springframework.http.HttpStatus;

import com.joaovictor.sicronizacaoreceita.exceptions.ErroGenericoException;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroInternoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoAutenticadoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoAutorizadoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoEncontradoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.joaovictor.sicronizacaoreceita.exceptions.ServicoIndisponivelException;
import com.joaovictor.sicronizacaoreceita.exceptions.TempoExpiradoException;

// TO DO Classe simula um FeingErrorDecoder, a ideia é simular a captura de erros vindos de requisições feitas com uso de feing clients.
// Exemplo: Um feing client para se comunicar com o serviço da Receita para onde seria enviado o csv
public class FeingErrorDecoderSimulado {
	
	public Exception decode(int statusCode) {
		
		// TO DO mensagens setadas apensa para exemplicar, mas a idéia é que usando o feingErrorDecoder seja pego as mensagens retornadas na resposta da requisição
		final String ERRO_RECEITA_REQUISICAO_INVALIDA = "Requisiçao inválida na API Receita";
		final String ERRO_RECEITA_REQUISICAO_NAO_AUTORIZADO = "Não autorizado na API Receita";
		final String ERRO_RECEITA_REQUISICAO_NAO_AUTENTICADO = "Não autenticado na API Receita";
		final String ERRO_RECEITA_REQUISICAO_NAO_ENCONTRADO = "Não encontrado na API Receita";
		final String ERRO_RECEITA_REQUISICAO_TEMPO_EXPIRADO = "Tempo expirado na API Receita";
		final String ERRO_RECEITA_REQUISICAO_ERRO_INTERNO = "Erro interno na API Receita";
		final String ERRO_RECEITA_REQUISICAO_SERVICO_INDISPONIVEL = "Serviço indisponível na API Receita";
		
		switch (statusCode) {
			case 400:
				return new RequisicaoInvalidaException(ERRO_RECEITA_REQUISICAO_INVALIDA);
			case 401:
				return new NaoAutorizadoException(ERRO_RECEITA_REQUISICAO_NAO_AUTORIZADO);
			case 403:
				return new NaoAutenticadoException(ERRO_RECEITA_REQUISICAO_NAO_AUTENTICADO);
			case 404:
				return new NaoEncontradoException(ERRO_RECEITA_REQUISICAO_NAO_ENCONTRADO);
			case 408:
				return new TempoExpiradoException(ERRO_RECEITA_REQUISICAO_TEMPO_EXPIRADO);
			case 500:
				return new ErroInternoException(ERRO_RECEITA_REQUISICAO_ERRO_INTERNO);
			case 503:
				return new ServicoIndisponivelException(ERRO_RECEITA_REQUISICAO_SERVICO_INDISPONIVEL);
			default:
				return new ErroGenericoException(HttpStatus.valueOf(statusCode), ERRO_RECEITA_REQUISICAO_ERRO_INTERNO, ERRO_RECEITA_REQUISICAO_ERRO_INTERNO);
		}
	}
}
