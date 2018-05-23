package tabelas.bancoDeDados;

public class JoeVariables {

	/**
	 * 
	 * INT - 6 dg.
	 * 
	 * FLOAT - 6digitos,6digitos
	 * 
	 * DATE - (YYYY-MM-DD)
	 * 
	 * DATETIME - (YYYY-MM-DD HH:MM:SS)
	 * 
	 * CHAR - (variável / FULL SPACE)
	 * 
	 * VARCHAR (variável)
	 * 
	 * TEXT - variável
	 */

	public String instaciaInt(String variavel, int tamanho, int inteiro) {
		String novoInt = inteiro + "";
		if (novoInt.length() < tamanho) {
			for (int i = novoInt.length(); i < tamanho; i++) {
				novoInt = "0" + novoInt;
			}
		}

		novoInt = "INTEIRO + |" + variavel + "|" + tamanho + "|" + inteiro;

		return novoInt;
	}

	public String instaciaFloat(String variavel, int tamanho, int inteiro) {
		String novoInt = inteiro + "";
		if (novoInt.length() < tamanho) {
			for (int i = novoInt.length(); i < tamanho; i++) {
				novoInt = "0" + novoInt;
			}
		}

		novoInt = "[" + variavel + "]" + "[" + tamanho + "]" + ":" + inteiro;

		return novoInt;
	}

}
