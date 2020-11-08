package com.joaovictor.sicronizacaoreceita.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.joaovictor.sicronizacaoreceita.components.Messages;
import com.joaovictor.sicronizacaoreceita.domain.Conta;
import com.joaovictor.sicronizacaoreceita.exceptions.ErroInternoException;
import com.joaovictor.sicronizacaoreceita.exceptions.RequisicaoInvalidaException;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvException;

public class ContaCSVUtil {
	
	public static String TYPE = "text/csv";
	private static final List<String> HEADERS = new ArrayList<>(Arrays.asList(
		    "agencia",
		    "conta",
		    "saldo",
		    "status"
		));

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			throw new RequisicaoInvalidaException("Arquivo não é um CSV válido");
		}

		return true;
	}

	public static List<Conta> csvToContas(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			CSVParser csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withIgnoreSurroundingSpaces().withDelimiter(';').withEscape('"'));) {
			List<Conta> contas = new ArrayList<Conta>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			int indice = 0;
			for (CSVRecord csvRecord : csvRecords) {
				if (indice == 0)
					isValidContasCsv(csvRecord);
				
				Conta conta = csvRecordToConta(csvRecord);
				if (Objects.nonNull(conta))
					contas.add(conta);
				indice++;
			}

			return contas;
		} catch (IOException e) {
			throw new ErroInternoException("Erro ao manipular CSV");
		}
	}
	
	public static Conta csvRecordToConta(CSVRecord csvRecord) {
		Conta conta = null;
		boolean isValid = true;
		for (int indice = 0; indice < csvRecord.size(); indice++) {
			if (HEADERS.contains(csvRecord.get(indice))) {
				isValid = false;
				break;
			}
		}
		
		if (isValid)
			conta = new Conta(csvRecord.get(0), csvRecord.get(1),
				Double.parseDouble(csvRecord.get(2).replaceAll(",", ".")), csvRecord.get(3), null);
		
		return conta; 
	}
	
	public static void ContasToCsv(PrintWriter writer, List<Conta> contas) {
		try {
            ColumnPositionMappingStrategy<Conta> mapStrategy
                    = new ColumnPositionMappingStrategy<>();

            mapStrategy.setType(Conta.class);
            
            StatefulBeanToCsv<Conta> btcsv = new StatefulBeanToCsvBuilder<Conta>(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(';')
                    .withOrderedResults(true)
                    .build();
            
            btcsv.write(contas);
            

        } catch (CsvException ex) {
        	throw new ErroInternoException("Problemas ao tentar criar o CSV");
        }
	}
	
	public static void isValidContasCsv(CSVRecord csvRecord) {

		if (csvRecord.size() > 4)
			throw new RequisicaoInvalidaException("Arquivo não é um CSV válido");
		
		for (int indice = 0; indice < csvRecord.size(); indice++) {
				
			if (!HEADERS.contains(csvRecord.get(indice))) {
				throw new RequisicaoInvalidaException("Arquivo não é um CSV válido");
			}
		}

	}

}