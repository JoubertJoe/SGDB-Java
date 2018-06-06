package util.JoeBd;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ParserTabela {

	DateFormat date = new SimpleDateFormat("yyyy-mm-dd");
	DateFormat dateTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

	public ArrayList<String> abrirArquivo(String nomeArquivo) throws IOException {

		BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo)));
		String linha;
		ArrayList<String> linhas = new ArrayList<String>();
		while ((linha = leitor.readLine()) != null) {
			linhas.add(linha);
		} // while
		return linhas;

	}

	public String confereLinha(String arquivo, ArrayList<String> listaInsert, ArrayList<String> listaVar)
			throws IOException, ParseException {

		ArrayList<String> linhas = abrirArquivo(arquivo);
		ArrayList<String> linhaTabela = abrirArquivo(arquivo.replace("tipos/", "tabelas/").replace(".joett", ".joetb"));
		String ultimoInsert[] = { "0" };
		if (linhaTabela.size() > 0) {
			ultimoInsert = linhaTabela.get(linhaTabela.size() - 1).split(",");
		}

		StringBuilder insert = new StringBuilder();

		for (int j = 0; j <= linhas.size() - 1; j++) { // j é a linha

			String[] linha = linhas.get(j).split("\t");

			if (autoIncrement(linha) == true) {

				int aInc = Integer.parseInt(ultimoInsert[j]) + 1;
				String aIncS = aInc + "";
				for (int t = 0; t < (6 - aIncS.length()); t++) {
					insert.append("0");
				}
				insert.append(removeEspaco(aIncS));

			} // if AutoIncrement
			else {

				for (int i = 0; i < listaVar.size(); i++) { // i é a variavel a ser inserida

					if (nome(linha).equalsIgnoreCase(listaVar.get(i).toString())) {

						if (tipo(linha).equalsIgnoreCase("date")) {
							date.parse(listaInsert.get(i));
							insert.append(removeEspaco("," + listaInsert.get(i)));
						} // Se o tipo for data.
						else if (tipo(linha).equalsIgnoreCase("int")) {

							if (autoIncrement(linha) == true) {
								int aInc = Integer.parseInt(ultimoInsert[j]) + 1;
								String aIncS = aInc + "";
								for (int t = 0; t < (6 - aIncS.length()); t++) {
									insert.append("0");
								}
								insert.append(removeEspaco(aIncS));

							} // if AutoIncrement
							else {

								Integer.parseInt(listaInsert.get(i));
								insert.append(",");
								for (int t = 0; t < (6 - listaInsert.get(i).length()); t++) {
									insert.append("0");
								} // For numero de 0s no INT

								insert.append(removeEspaco(listaInsert.get(i)));
							}
						} // Se o tipo for Inteiro

						else if (tipo(linha).equalsIgnoreCase("float")) {
							
							String tipoFloat[] = listaInsert.get(i).split("\\.");
							System.out.println("FLOAT " + listaInsert.get(i));
							insert.append(",");
							if (tipoFloat.length > 0) {
								for (int t = 0; t < (6 - tipoFloat[0].length()); t++) {
									insert.append("0");
								} // for
								insert.append(removeEspaco(tipoFloat[0] + "." + tipoFloat[1]));
								for (int t = 0; t < (6 - tipoFloat[1].length()); t++) {
									insert.append("0");
								} // for
								System.out.println(insert.toString());

							} // Se tiver numero depois da virgula.
							else {
								for (int t = 0; t < (6 - listaInsert.get(i).length()); t++) {
									insert.append("0");
								}
								insert.append(listaInsert.get(i) + ".000000");
							} // se não tiver.

						} // Se o tipo for Double
						else if ((tipo(linha).equalsIgnoreCase("varchar")) || tipo(linha).equalsIgnoreCase("text")) {
							// int tamanho = listaInsert.get(i).length();
							// insert.append(removeEspaco("[" + tamanho + "]");
							insert.append("," + removeEspaco(listaInsert.get(i)));
						} // VARCHAR & TEXT
						else if (tipo(linha).equalsIgnoreCase("char")) {
							insert.append(removeEspaco("," + listaInsert.get(i).replaceAll("[']", "")));
						} // CHAR
						else if (tipo(linha).equalsIgnoreCase("datetime")) {

							dateTime.parse(listaInsert.get(i));
							insert.append(removeEspaco("," + listaInsert.get(i)));
						} // Se o tipo for date time.

					} // Se a variável bater com a linha;

				} // FOR J

			}

		} // FOR I

		return (insert.toString());

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

	public String removeEspaco(String stringComEspaco) {
		return stringComEspaco.replaceAll(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", "");
	}

	// Métodos para controlar variáveis da tabela.
	public String nome(String[] parametros) {
		return removeEspaco(parametros[0]);
	}

	public String tipo(String[] parametros) {
		return removeEspaco(parametros[1]);
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
		if (removeEspaco(parametros[3]).equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}

	}

	public boolean unique(String[] parametros) {

		if (removeEspaco(parametros[4]).equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean autoIncrement(String[] parametros) {
		if (removeEspaco(parametros[5]).equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}
	}

	public boolean canBeNull(String[] parametros) {
		if (removeEspaco(parametros[6]).equalsIgnoreCase("null")) {
			return false;
		} else {
			return true;
		}

	}

	public String defaultValue(String[] parametros) {
		return removeEspaco(parametros[7]);
	}

	public boolean signed(String[] parametros) {
		return false;
	}

}
