package com.joaovictor.sicronizacaoreceita.domain;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@CsvBindByName(column = "agencia")
	private String agencia;
	
	@CsvBindByName(column = "conta")
	private String conta;

	@CsvBindByName(column = "saldo")
	private double saldo;

	@CsvBindByName(column = "status")
	private String status;
	
	@CsvBindByName(column = "resultado")
	private String resultado;

	public Conta(String agencia, String conta, double saldo, String status, String resultado) {
		super();
		this.agencia = agencia;
		this.conta = conta;
		this.saldo = saldo;
		this.status = status;
		this.resultado = resultado;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agencia == null) ? 0 : agencia.hashCode());
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (agencia == null) {
			if (other.agencia != null)
				return false;
		} else if (!agencia.equals(other.agencia))
			return false;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		return true;
	}
	
}
