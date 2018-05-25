package util.JoeBd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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
		if (!parser.validaInsert(insert, tabela)) {
			return false;
		}else {
			EscreveNoArquivo(insert, tabela);
			return true;
		}
			
	}
}// Classe
