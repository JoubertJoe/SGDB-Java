package util.JoeBd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InsereArquivo {
	ParserTabela parser = new ParserTabela();

	private static final String novaLinha = System.getProperty("line.separator");

	private synchronized void EscreveNoArquivo(String linha, String arquivo) {

		PrintWriter printWriter = null;
		File file = new File(arquivo);
		try {
			printWriter = new PrintWriter(new FileOutputStream(arquivo, true));
			printWriter.write(novaLinha + linha);
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			} // if
		} // Finally
	}// Metodo

	public Boolean InsereInsert(String insert, String tabela) throws IOException {
		insert = insert.substring(1,insert.length()-1);
		if (!parser.validaInsert(insert, tabela.replace(".joetb", ".joett").replace("tabelas/", "tipos/"))) {
			return false;
		} else {
			//EscreveNoArquivo(insert, tabela);
			String aux[] = insert.split("[,]");
			System.out.println(insert);
			System.out.println(aux[0]);
			ArrayList<String> listaInsert = new ArrayList<>();
			for (int i = 0; i < aux.length; i++) {
				listaInsert.add(aux[i]);
			}

			parser.confereLinha(tabela, listaInsert);
			return true;
		}

	}
}// Classe
