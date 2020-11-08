package com.joaovictor.sicronizacaoreceita;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.joaovictor.sicronizacaoreceita.services.impl.DBServiceImpl;

@SpringBootApplication
public class SincronizacaoReceitaApplication {
	
	public static void main(String[] args) throws ParseException {
		SpringApplication.run(SincronizacaoReceitaApplication.class, args);
	}
}
