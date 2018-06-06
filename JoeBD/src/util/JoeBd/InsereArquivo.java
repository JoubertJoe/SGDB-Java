package util.JoeBd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

public class InsereArquivo {
	ParserTabela parser = new ParserTabela();

	private static final String novaLinha = System.getProperty("line.separator");

	private synchronized void EscreveNoArquivo(String linha, String arquivo) {

		PrintWriter printWriter = null;
		File file = new File(arquivo);
		ArrayList<String> linhasArquivo = new ArrayList<String>();
		try {
			linhasArquivo = parser.abrirArquivo(arquivo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			printWriter = new PrintWriter(new FileOutputStream(arquivo, true));
			if (linhasArquivo.size() > 0 && linhasArquivo.get(0).length() > 1) {
				printWriter.write(novaLinha + linha);	
				
			}else {
				printWriter.write(linha);
			}
			
		} catch (IOException ioex) {
			ioex.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.flush();
				printWriter.close();
			} // if
		} // Finally
	}// Metodo

	public Boolean InsereInsert(String insert, String tabela, String variaveis) throws IOException, ParseException {
		insert = insert.substring(1, insert.length() - 1);

		// EscreveNoArquivo(insert, tabela);
		String aux[] = insert.split("[,]");
		String aux2[] = variaveis.split("[\t]");
		System.out.println(insert);
		System.out.println(aux[0]);
		ArrayList<String> listaInsert = new ArrayList<>();
		ArrayList<String> listaVar = new ArrayList<>();

		for (int i = 0; i < aux.length; i++) {
			listaInsert.add(aux[i]);
		}
		for (int i = 0; i < aux2.length; i++) {
			listaVar.add(aux2[i].replaceAll("\\s+", ""));
		}
		// System.out.println(listaInsert.toString());
		// System.out.println(listaVar.toString());

		EscreveNoArquivo(parser.confereLinha(tabela, listaInsert, listaVar),
				tabela.replace("tipos/", "tabelas/").replace(".joett", ".joetb"));
		return true;
	}

}// Classe
