package util.JoeBd;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ParserTabela {

	public ArrayList<String> abrirArquivo(String nomeArquivo) throws IOException {

		BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo)));
		String linha;
		ArrayList<String> linhas = new ArrayList<String>();
		while ((linha = leitor.readLine()) != null) {
			linhas.add(linha);
		} // while
		return linhas;

	}

	public void confereLinha(String arquivo, ArrayList<String> listaInsert) throws IOException {
		ArrayList<String> linhas = abrirArquivo(arquivo);

		for (int i = 0; i < linhas.size(); i++) {
			// System.out.println(linhas.get(i).toString());
			System.out.println("\n---------------------------------------------\n");
			String[] linha = linhas.get(i).split("\t");
			System.out.println("Variável: " + nome(linha));
			System.out.println("Tipo: " + tipo(linha));
			System.out.println("Tamanho: " + tamanho(linha));
			System.out.println("Primary Key: " + primaryKey(linha));
			System.out.println("Unique: " + unique(linha));
			System.out.println("Auto Increment: " + autoIncrement(linha));
			System.out.println("Null: " + canBeNull(linha));
			System.out.println("Default Value: " + defaultValue(linha));
			System.out.println("Signed: " + signed(linha));

		} // for
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
		return parametros[0].replaceAll("\\s+", "");
	}

	public String tipo(String[] parametros) {
		return parametros[1].replaceAll("\\s+", "");
	}

	public double tamanhoDouble(String[] parametros) {
		return Double.parseDouble(parametros[2]);
	}

	public int tamanho(String[] parametros) {
		if (parametros[2].replaceAll("\\s+", "").equals("null")) {
			return 0;
		} else {
			return Integer.parseInt(parametros[2]);
		} // else

	}

	public boolean primaryKey(String[] parametros) {
		if (parametros[3].replaceAll("\\s+", "") == "null") {
			return false;
		} else {
			return true;
		}

	}

	public boolean unique(String[] parametros) {

		if (parametros[4].replaceAll("\\s+", "") == "null") {
			return false;
		} else {
			return true;
		}
	}

	public boolean autoIncrement(String[] parametros) {
		if (parametros[5].replaceAll("\\s+", "") == "null") {
			return false;
		} else {
			return true;
		}
	}

	public boolean canBeNull(String[] parametros) {
		if (parametros[6].replaceAll("\\s+", "") == "null") {
			return false;
		} else {
			return true;
		}

	}

	public String defaultValue(String[] parametros) {
		return parametros[7].replaceAll("\\s+", "");
	}

	public boolean signed(String[] parametros) {
		return false;
	}

}
