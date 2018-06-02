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

	public void confereLinha(String arquivo, ArrayList<String> listaInsert, ArrayList<String> listaVar)
			throws IOException {

		ArrayList<String> linhas = abrirArquivo(arquivo);
		ArrayList<String> linhaTabela = abrirArquivo(arquivo.replace("tipos/", "tabelas/").replace(".joett", ".joetb"));
		String ultimoInsert[] = linhaTabela.get(linhaTabela.size() - 1).split(",");
		StringBuilder insert = new StringBuilder();

		for (int j = 0; j <= linhas.size(); j++) { // j é a linha
			// System.out.println(linhas.get(j));
			String[] linha = linhas.get(j).split("\t");

			if (autoIncrement(linha) == true) {
				int aInc = Integer.parseInt(ultimoInsert[j]);
				String aIncS = aInc + "";
				for (int i = 0; i < (6 - aIncS.length()); i++) {
					insert.append("0");
				}
				insert.append(aIncS);
				System.out.println("INSERT " + insert.toString());

			} // if AutoIncrement
			for (int i = 0; i < listaVar.size() - 1; i++) { // i é a variavel a ser inserida
				System.out.println(listaVar.get(i));

				if (nome(linha).equalsIgnoreCase(listaVar.get(i).toString())) {
					System.out.println(nome(linha));

				} // Se a variável bater com a linha;

			} // FOR J
		} // FOR I

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
		if (parametros[3].replaceAll("\\s+", "").equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}

	}

	public boolean unique(String[] parametros) {

		if (parametros[4].replaceAll("\\s+", "").equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean autoIncrement(String[] parametros) {
		if (parametros[5].replaceAll("\\s+", "").equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canBeNull(String[] parametros) {
		if (parametros[6].replaceAll("\\s+", "").equalsIgnoreCase("null")) {
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
