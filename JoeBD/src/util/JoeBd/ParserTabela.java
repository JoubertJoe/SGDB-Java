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

	// Métodos para controlar variáveis da tabela.
	public String nome(String[] parametros) {
		return parametros[0];
	}

	public String tipo(String[] parametros) {
		return parametros[1];
	}

	public double tamanhoDouble(String[] parametros) {
		return Double.parseDouble(parametros[2]);
	}

	public int tamanho(String[] parametros) {
		return Integer.parseInt(parametros[2]);
	}

	public boolean primaryKey(String[] parametros) {
		if (parametros[3] == "null") {
			return false;
		} else {
			return true;
		}

	}

	public boolean unique(String[] parametros) {

		if (parametros[4] == "null") {
			return false;
		} else {
			return true;
		}
	}

	public boolean autoIncrement(String[] parametros) {
		if (parametros[5] == "null") {
			return false;
		} else {
			return true;
		}
	}

	public boolean canBeNull(String[] parametros) {
		if (parametros[6] == "null") {
			return false;
		} else {
			return true;
		}

	}

	public String Default(String[] parametros) {
		return parametros[7];
	}

	public boolean signed(String[] parametros) {
		return false;
	}

}
