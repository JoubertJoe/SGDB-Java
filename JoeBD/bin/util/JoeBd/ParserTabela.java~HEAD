package util.JoeBd;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParserTabela {

	public void abrirArquivo(String nomeArquivo) throws IOException {
		BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo)));
		String linha;
		while ((linha = leitor.readLine()) != null) {
			System.out.println(linha);
		}
	}

	public boolean validaInsert(String insert, String tabela) throws IOException {
		abrirArquivo(tabela);

		// Objetivo : Verificar os tipos a serem inseridos na tabela, verificar se tudo
		// está dentro do padrão antes de escrever algo dentro do arquivo, além disso é
		// preciso criar as automações de ID.
		return true;
	}

	public void insere() {

	}
}