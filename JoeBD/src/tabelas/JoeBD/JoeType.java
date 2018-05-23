package tabelas.JoeBD;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class JoeType {

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

	public boolean escreveTipos(String nomeTabela, String tring) {
		try {
			// create a temporary file

			BufferedWriter writer = new BufferedWriter(new FileWriter(nomeTabela + ".joett"));
			writer.write(tring);

			// Close writer
			writer.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
