package util.JoeBd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectTexto {

	public ArrayList<String> selectTabela(String tabela, String select) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(tabela));
		ArrayList<String> resposta = new ArrayList<String>();
		while (scan.hasNext()) {
			String linha = scan.nextLine().toString();
			if ((linha.toLowerCase()).contains(select.toLowerCase())) {
				resposta.add(linha);
			}
		} // whiler
		return resposta;
	}

}
