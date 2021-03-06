package gui.JoeBD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.TreeModel;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

import util.JoeBd.InsereArquivo;
import util.JoeBd.ParserTabela;
import util.JoeBd.SelectTexto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;

public class PainelTabela extends JFrame {

	private JPanel contentPane;
	private TreeModel model;
	private RSyntaxTextArea txtCodigo;
	private JTable tbResultados;
	private JTree treeTabelas;
	private JButton btnRodar;
	private JLabel lblDigiteSeusComandos, lblVisao, lblNome, lblTabela;
	private String[] selects, froms, wheres;
	private JPanel pnlTreeBtns;
	private JButton btnSelecionar, btnVoltar;
	private DefaultTableModel modeloBD, modeloPesquisa;
	private JTable tabelaVisao;
	private JScrollPane scrollTabelaVisao;
	private Object[] colunas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelTabela frame = new PainelTabela();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ParserTabela parserTipos = new ParserTabela();
	InsereArquivo insere = new InsereArquivo();
	SelectTexto selectText = new SelectTexto();

	public PainelTabela() {
		setTitle("JOEBD");
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 300);

		criaJanela();
		criaAcoes();
	}

	private void criaJanela() {

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel NomeTabelapnl = new JPanel();
		NomeTabelapnl.setForeground(Color.BLACK);
		NomeTabelapnl.setBackground(new Color(255, 255, 204));
		contentPane.add(NomeTabelapnl, BorderLayout.NORTH);
		NomeTabelapnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblTabela = new JLabel("TABELA :");
		NomeTabelapnl.add(lblTabela);

		lblNome = new JLabel("");
		NomeTabelapnl.add(lblNome);

		JPanel visaoPnl = new JPanel();
		visaoPnl.setBackground(Color.WHITE);
		contentPane.add(visaoPnl, BorderLayout.WEST);
		visaoPnl.setLayout(new BorderLayout(0, 0));

		lblVisao = new JLabel("Visao de tabelas");
		lblVisao.setForeground(Color.BLACK);
		visaoPnl.add(lblVisao, BorderLayout.NORTH);

		model = new FileTreeModel(new File("tabelas/"));
		treeTabelas = new JTree();
		treeTabelas.setForeground(Color.WHITE);
		treeTabelas.setBackground(Color.WHITE);
		treeTabelas.setModel(model);
		visaoPnl.add(treeTabelas, BorderLayout.CENTER);

		pnlTreeBtns = new JPanel();
		visaoPnl.add(pnlTreeBtns, BorderLayout.SOUTH);
		pnlTreeBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnSelecionar = new JButton("Selecionar");
		pnlTreeBtns.add(btnSelecionar);

		btnVoltar = new JButton("Voltar");
		pnlTreeBtns.add(btnVoltar);

		JTabbedPane abaspnl = new JTabbedPane(JTabbedPane.TOP);
		abaspnl.setBackground(Color.WHITE);
		contentPane.add(abaspnl, BorderLayout.CENTER);

		JPanel pnlCodigos = new JPanel();
		pnlCodigos.setBackground(UIManager.getColor("TabbedPane.selected"));
		abaspnl.addTab("Codigos", null, pnlCodigos, null);
		pnlCodigos.setLayout(new BorderLayout(0, 0));

		lblDigiteSeusComandos = new JLabel("Digite seus comandos aqui :");
		lblDigiteSeusComandos.setForeground(Color.BLACK);
		pnlCodigos.add(lblDigiteSeusComandos, BorderLayout.NORTH);

		btnRodar = new JButton("RODAR");
		btnRodar.setBorder(null);
		btnRodar.setForeground(UIManager.getColor("Table.dropLineShortColor"));
		btnRodar.setBackground(UIManager.getColor("TabbedPane.selected"));
		pnlCodigos.add(btnRodar, BorderLayout.SOUTH);

		txtCodigo = new RSyntaxTextArea();
		txtCodigo.setCurrentLineHighlightColor(UIManager.getColor("TabbedPane.light"));
		txtCodigo.setBackground(UIManager.getColor("TabbedPane.shadow"));
		txtCodigo.setText(
				"INSERT INTO `testeDefinitivo`\n(produto,categoria,preco)\nVALUES\n(\"Produto\",\"produtos\",20.99)\n");
		txtCodigo.setMarginLineEnabled(true);
		txtCodigo.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_SQL);
		txtCodigo.setAutoIndentEnabled(true);
		txtCodigo.setCodeFoldingEnabled(true);

		RTextScrollPane sp = new RTextScrollPane(txtCodigo);
		sp.getGutter().setForeground(Color.WHITE);
		sp.getGutter().setBackground(SystemColor.scrollbar);
		sp.getGutter().setBorderColor(UIManager.getColor("Table.dropLineShortColor"));
		sp.getGutter().setActiveLineRangeColor(new Color(128, 0, 0));
		pnlCodigos.add(sp, BorderLayout.CENTER);

		JPanel pnlResultados = new JPanel();
		abaspnl.addTab("RESULTADOS DE PESQUISA", null, pnlResultados, null);
		pnlResultados.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollResultados = new JScrollPane();
		pnlResultados.add(scrollResultados, BorderLayout.CENTER);

		tbResultados = new JTable();
		scrollResultados.setViewportView(tbResultados);

		JPanel pnlVSL = new JPanel();
		abaspnl.addTab("Visualizar Tabelas", null, pnlVSL, null);
		pnlVSL.setLayout(new BorderLayout(0, 0));

		scrollTabelaVisao = new JScrollPane();
		pnlVSL.add(scrollTabelaVisao, BorderLayout.CENTER);

		tabelaVisao = new JTable();
		tabelaVisao.setFillsViewportHeight(true);
		scrollTabelaVisao.setViewportView(tabelaVisao);

	}

	private String[] filtraInsert(String texto) throws Exception {
		String[] novoTexto;

		// Passando tudo pra caixa baixa, removendo todos os espaços em branco, e
		// filtrando o Select
		novoTexto = texto.replaceAll("\n", "").split("(?i)insert into");
		System.out.println(novoTexto[0]);
		novoTexto = novoTexto[1].split("(?i)values"); // Isolando o Select
		System.out.println(novoTexto[1]);
		String dados = novoTexto[1].toString().replaceAll("\n", null);
		novoTexto = novoTexto[0].replaceAll("`", "").split("\\(");

		novoTexto[1] = novoTexto[1].replaceAll(",", "\t").replaceAll("\\)", "");

		String[] aux = new String[] { novoTexto[0].replaceAll("\\s+", ""), novoTexto[1], dados };
		return aux;

	}

	private String[] filtraSelect(String texto) throws Exception {
		String[] novoTexto;
		// Passando tudo pra caixa baixa, removendo todos os espaços em branco, e
		// filtrando o Select
		novoTexto = texto.replaceAll("\\s+", "").split("(?i)select");
		novoTexto = novoTexto[1].split("from"); // Isolando o Select
		novoTexto = novoTexto[0].split(",");
		return novoTexto;
	}

	private String[] filtraFrom(String texto) throws Exception {
		String[] novoTexto;
		// Passando tudo pra caixa baixa, removendo todos os espaços em branco, e
		// filtrando o Select
		novoTexto = texto.toLowerCase(getLocale()).replaceAll("\\s+", "").split("(?i)select");
		novoTexto = novoTexto[1].split("(?i)from");
		novoTexto = novoTexto[1].split("(?i)where"); // Isolando o From
		novoTexto = novoTexto[0].split(",");

		return novoTexto;
	}

	private String[] filtraWhere(String texto) throws Exception {
		String[] novoTexto;
		// Passando tudo pra caixa baixa, removendo todos os espaços em branco, e
		// filtrando o Select
		novoTexto = texto.replaceAll("\\s+", "").split("(?i)select");
		novoTexto = novoTexto[1].split("(?i)from");
		novoTexto = novoTexto[1].split("(?i)where"); // Isolando o Where
		novoTexto = novoTexto[1].split(",");
		return novoTexto;

	}

	private static int busca(int[] array, int chave) {
		return buscaBinariaRecursiva(array, 0, array.length - 1, chave);
	}

	private static int buscaBinariaRecursiva(int[] array, int menor, int maior, int chave) {
		int media = (maior + menor) / 2;
		int valorMeio = array[media];

		if (menor > maior)
			return -1;
		else if (valorMeio == chave)
			return media;
		else if (valorMeio < chave)
			return buscaBinariaRecursiva(array, media + 1, maior, chave);
		else
			return buscaBinariaRecursiva(array, menor, media - 1, chave);
	}

	public void insert(String nomeArquivo, String insertData) {
		// NOME TIPO TAMANHO PK UNIQUE AI Null Default Value SIGNED
		try {
			importaArquivo(nomeArquivo);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void visualizaTabela(String tabela) {
		ArrayList<String[]> listaElementos;
		ArrayList<String> coluna = new ArrayList<String>();
		ArrayList<String> linha = new ArrayList<String>();

		try {
			listaElementos = importaArquivo(tabela.replace("tabelas", "tipos").replace(".joetb", ".joett"));

			for (int i = 0; i < listaElementos.size(); i++) {
				// System.out.println("coluna 01 " + listaElementos.get(i)[0]);
				coluna.add(listaElementos.get(i)[0]);

			} // for
			String[] colunas = new String[coluna.size()];
			boolean trueOrFalse;

			coluna.toArray(colunas);
			modeloBD = new DefaultTableModel(colunas, 0);
			try {
				listaElementos = importaArquivo(tabela);

				for (int i = 0; i < listaElementos.size(); i++) {
					// System.out.println("linha 01 " + listaElementos.get(i)[0]);
					String[] arrayLinha = listaElementos.get(i)[0].split(",");
					modeloBD.addRow(arrayLinha);

				} // for
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 2trycatch

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1trycatch

	}

	public void visualizaTabela(String tabela, ArrayList<String> dados) {

		ArrayList<String[]> listaElementos;
		ArrayList<String> listaElementos2;
		ArrayList<String> coluna = new ArrayList<String>();
		ArrayList<String> linha = new ArrayList<String>();

		try {
			listaElementos = importaArquivo(tabela.replace("tabelas", "tipos").replace(".joetb", ".joett"));

			for (int i = 0; i < listaElementos.size(); i++) {
				// System.out.println("coluna 01 " + listaElementos.get(i)[0]);
				coluna.add(listaElementos.get(i)[0]);

			} // for
			String[] colunas = new String[coluna.size()];
			

			coluna.toArray(colunas);
			
			modeloPesquisa = new DefaultTableModel(colunas, 0);
			System.out.println(dados.toString());
			listaElementos2 = dados;
			System.out.println(listaElementos2.size());

			for (int i = 0; i < listaElementos2.size(); i++) {
				
				System.out.println("linha 01 " + dados.get(i).toString());
				String[] arrayLinha = listaElementos2.get(i).split(",");
				modeloPesquisa.addRow(arrayLinha);

			} // for

			tbResultados.setModel(modeloPesquisa);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 1trycatch

	}

	public String[] separaAtributos(String linhatb) {
		// NOME TIPO TAMANHO PK UNIQUE AI Null Default Value SIGNED
		String[] atributos = linhatb.split("\t");
		return atributos;

	}

	public ArrayList<String[]> importaArquivo(String nomeArquivo) throws IOException {
		ArrayList<String[]> listaVariaveis = new ArrayList<String[]>();
		BufferedReader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo)));
		String linha;
		while ((linha = leitor.readLine()) != null) {
			// System.out.println(linha);
			String[] coluna = separaAtributos(linha);

			// System.out.println("coluna " + 0 + ": " + coluna[0].toString());

			listaVariaveis.add(coluna);
		} // while
		return listaVariaveis;

	}

	public void verificaInsertTabela(String novoInsert) throws IOException {

	}

	private void criaAcoes() {

		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeTabela = treeTabelas.getLastSelectedPathComponent().toString();
				// System.out.println(nomeTabela);
				tabelaVisao.setEnabled(true);
				visualizaTabela(nomeTabela);
				tabelaVisao.setModel(modeloBD);
				tabelaVisao.setEnabled(false);
			}
		});

		btnRodar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String comandos = txtCodigo.getText();
				if (comandos.toLowerCase().contains("insert")) {

					try {
						String insert[] = filtraInsert(comandos);

						for (int i = 0; i < insert.length; i++) {
							// System.out.println(i + ":" + insert[i]);
						}
						insere.InsereInsert(insert[2], "tipos/" + insert[0] + ".joett", insert[1]);

					} catch (Exception syntaxError) {
						JOptionPane.showMessageDialog(txtCodigo, "Erro de Syntaxe:\n" + syntaxError);
					} // catch

				} // if

				if (comandos.toLowerCase().contains("select")) {
					try {
						selects = filtraSelect(comandos);
						froms = filtraFrom(comandos);
						wheres = filtraWhere(comandos);
						// tbResultados
						ArrayList<String> dado = selectText.selectTabela("tabelas/" + froms[0] + ".joetb", wheres[0]);
						visualizaTabela("tipos/" + froms[0] + ".joett",dado);

					} catch (Exception syntaxError) {
						JOptionPane.showMessageDialog(txtCodigo, "Erro de Syntaxe:\n" + syntaxError);
					} // tryCatch
				} // if
			}// metodo
		});// listener

		btnVoltar.setEnabled(false);
	}
}
