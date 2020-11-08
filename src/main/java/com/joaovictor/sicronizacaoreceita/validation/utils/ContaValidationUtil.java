package com.joaovictor.sicronizacaoreceita.validation.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.joaovictor.sicronizacaoreceita.components.Messages;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;

public abstract class ContaValidationUtil {
	
	// Tipos de status validos:
	private static final List<String> TIPOS_STATUS = new ArrayList<>(Arrays.asList(
		    "A",
		    "I",
		    "B",
		    "P"
		));

	public static boolean isValidAgencia(final String agencia) {
		boolean agenciaValida = true;
		
		// Formato agencia: 0000
        if (agencia == null || agencia.length() != 4) {
        	throw new RequisicaoInvalidaException("Agência inválida");
        }
        
        return agenciaValida;
    }
	
	public static boolean isValidConta(final String conta) {
		boolean contaValida = true;
		String contaAux = conta.replaceAll("[^0-9]", "");
		
		if (contaAux == null || contaAux.length() != 6) {
			throw new RequisicaoInvalidaException("Conta inválida");
        }
		
		return contaValida;
	}
	
	public static boolean isValidStatus(final String status) {
		boolean statusValido = true;
		
		if (status == null || !TIPOS_STATUS.contains(status)) {
			throw new RequisicaoInvalidaException("Statu da conta inválido");
        }
		
		return statusValido;
	}
}
