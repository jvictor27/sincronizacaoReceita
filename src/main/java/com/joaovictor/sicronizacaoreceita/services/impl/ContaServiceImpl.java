package com.joaovictor.sicronizacaoreceita.services.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.domain.Conta;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroInternoException;
import com.joaovictor.sicronizacaoreceita.services.ContaService;
import com.joaovictor.sicronizacaoreceita.services.ReceitaService;
import com.joaovictor.sicronizacaoreceita.utils.ContaCSVUtil;
import com.joaovictor.sicronizacaoreceita.validation.utils.ContaValidationUtil;

@Service
public class ContaServiceImpl implements ContaService {

	@Autowired
	private ReceitaService receitaService;
	
	@Override
	public List<Conta> processarContas(MultipartFile contaCSV) {
		ContaCSVUtil.hasCSVFormat(contaCSV);
		List<Conta> contas = null;
		try {
			contas = ContaCSVUtil.csvToContas(contaCSV.getInputStream());
		} catch (IOException e) {
			throw new ErroInternoException("Erro ao manipular CSV");
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
		ContaValidationUtil.isValidAgencia(conta.getAgencia());
		ContaValidationUtil.isValidConta(conta.getConta());
		ContaValidationUtil.isValidStatus(conta.getStatus());
		
		conta = receitaService.atualizarConta(conta);
		return conta;
	}

}
