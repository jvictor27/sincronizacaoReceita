package com.joaovictor.sicronizacaoreceita.services.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.components.Messages;
import com.joaovictor.sicronizacaoreceita.domain.Conta;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroInternoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.joaovictor.sicronizacaoreceita.services.ContaService;
import com.joaovictor.sicronizacaoreceita.services.ReceitaService;
import com.joaovictor.sicronizacaoreceita.utils.ContaCSVUtil;
import com.joaovictor.sicronizacaoreceita.validation.utils.ContaValidationUtil;

@Service
public class ContaServiceImpl implements ContaService {
	// TODO Implementar testes

	@Autowired
	private ReceitaService receitaService;
	
	@Autowired
    Messages messages;
	
	@Override
	public List<Conta> processarContas(MultipartFile contaCSV) {
		ContaCSVUtil.hasCSVFormat(contaCSV);
		List<Conta> contas = null;
		try {
			contas = ContaCSVUtil.csvToContas(contaCSV.getInputStream());
		} catch (IOException e) {
			throw new ErroInternoException(messages.get("csv.erro.manipular"));
		}
		contas = processarContas(contas);
		return contas;
	}

	@Override
	public List<Conta> processarContas(List<Conta> contas) {
		contas.forEach(conta -> processarConta(conta));
		return contas;
	}

	@Override
	public Conta processarConta(Conta conta) {
		try {
			ContaValidationUtil.isValidAgencia(conta.getAgencia());
			ContaValidationUtil.isValidConta(conta.getConta());
			ContaValidationUtil.isValidStatus(conta.getStatus());
			conta = receitaService.atualizarConta(conta);
		} catch (RequisicaoInvalidaException e) {
			conta.setResultado(e.getMessage());
		} 

		return conta;
	}

}
