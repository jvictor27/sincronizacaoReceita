package com.joaovictor.sicronizacaoreceita.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.domain.Conta;

public interface ContaService {

	public List<Conta> processarContas(MultipartFile contaCSV);
	
	public List<Conta> processarContas(List<Conta> contas);
	
	public Conta processarConta(Conta conta);
}
