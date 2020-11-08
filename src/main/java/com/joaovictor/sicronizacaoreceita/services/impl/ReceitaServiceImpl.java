package com.joaovictor.sicronizacaoreceita.services.impl;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.joaovictor.sicronizacaoreceita.domain.Conta;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroGenericoException;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroInternoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoAutenticadoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoAutorizadoException;
import com.joaovictor.sicronizacaoreceita.exceptions.NaoEncontradoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.joaovictor.sicronizacaoreceita.exceptions.ServicoIndisponivelException;
import com.joaovictor.sicronizacaoreceita.exceptions.TempoExpiradoException;
import com.joaovictor.sicronizacaoreceita.feing.utils.FeingErrorDecoderSimulado;
import com.joaovictor.sicronizacaoreceita.services.ReceitaService;

@Service
public class ReceitaServiceImpl implements ReceitaService {
	
	@Override
	public Conta atualizarConta(Conta conta) {
		String resultado = null;
		try {
			// O bloco de código dentro do try simula uma chamda usando um feing client que se comunica com a API da Receita
			int randomStatuCode = 404;
	        // Simula tempo de resposta do serviço (entre 1 e 5 segundos)
	        long wait = Math.round(Math.random() * 4000) + 1000;
	        Thread.sleep(wait);
	        
	        if (wait > 5000)
	        	randomStatuCode = 503;
	        // Simula cenário de erro no serviço (0,1% de erro)
//	        randomStatuCode = (int) Math.round(Math.random() * 1000);
	        if ((randomStatuCode < 200 || randomStatuCode > 299) && Objects.nonNull(HttpStatus.valueOf(randomStatuCode))) {
	            throw new FeingErrorDecoderSimulado().decode(randomStatuCode);
	        }
	        
	        resultado = "Sucesso";
		} catch (RequisicaoInvalidaException | NaoAutorizadoException | NaoAutenticadoException | NaoEncontradoException 
				| TempoExpiradoException | ErroInternoException | ServicoIndisponivelException | ErroGenericoException e) {
			resultado = e.getMessage();
		} finally {
			conta.setResultado(resultado);
	        return conta;
		}
		
    }
}
