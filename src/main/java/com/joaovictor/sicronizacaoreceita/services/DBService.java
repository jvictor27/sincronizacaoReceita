package com.joaovictor.sicronizacaoreceita.services;

import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.domain.Conta;

public interface DBService {

	public void instanciateTestDataBase() throws ParseException;
}
