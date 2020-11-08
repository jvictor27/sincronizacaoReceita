package com.joaovictor.sicronizacaoreceita.resources;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.domain.Conta;
import com.joaovictor.sicronizacaoreceita.services.ContaService;
import com.joaovictor.sicronizacaoreceita.utils.ContaCSVUtil;

@RestController
@RequestMapping(value="/contas")
public class ContaResource {

	@Autowired
	private ContaService contaService;
	
	@RequestMapping(value="/upload-csv", method=RequestMethod.POST, produces = "text/csv")
	public void uploadCsv(@RequestParam("file") MultipartFile contaCsv, HttpServletResponse response) throws IOException {
		List<Conta> contas = contaService.processarContas(contaCsv);
		response.setContentType("text/csv");
		String filename = "contasProcessadas.csv";
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
        "attachment; filename=\"" + filename + "\"");
		ContaCSVUtil.ContasToCsv(response.getWriter(), contas);
		// TO DO Ajustar para retornar mensagens em casos de erros capturados @ControllerAdvice
	}
	
}
