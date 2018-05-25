package util.JoeBd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	public void abrirArquivo(String nomeArquivo) throws IOException {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo)));
		String linha;
		while ((linha = leitor.readLine()) != null) {
			System.out.println(linha);
		}
	}

	public boolean validaInsert(String insert, String tabela) throws IOException {
		abrirArquivo(tabela);
		return false;
	}
}