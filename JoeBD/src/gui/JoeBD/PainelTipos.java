package gui.JoeBD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class PainelTipos extends JFrame {

	private JPanel contentPane, panelTabelaInfo;
	private JTextField txtNomeDaTabela;
	private JTable tabelaDeTipos;
	private JLabel lblNomeDaTabela, lblqtElementos;
	private JSpinner spnIVariaveis;
	private JButton btnComecar, btnSalvarTipoDe;
	private JScrollPane scrollPane;
	private JComboBox comboTipo;
	private DefaultTableModel modelTipos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PainelTipos frame = new PainelTipos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PainelTipos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		criaComponentes();
		criaAcoes();

	}

	void criaComponentes() {
		// Painel

		panelTabelaInfo = new JPanel();
		panelTabelaInfo.setForeground(Color.BLACK);
		panelTabelaInfo.setBackground(Color.WHITE);
		contentPane.add(panelTabelaInfo, BorderLayout.NORTH);

		// Label
		lblNomeDaTabela = new JLabel("NOME DA TABELA: ");
		lblqtElementos = new JLabel("ADICIONAR ELEMENTOS ");
		lblqtElementos.setDisplayedMnemonic(KeyEvent.VK_ENTER);
		lblqtElementos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblqtElementos.setHorizontalAlignment(SwingConstants.CENTER);
		lblqtElementos.setFont(new Font("FreeMono", Font.BOLD, 14));
		lblqtElementos.setForeground(Color.BLACK);

		// Campos de texto.
		txtNomeDaTabela = new JTextField();
		txtNomeDaTabela.setColumns(10);

		// Spinners
		spnIVariaveis = new JSpinner();
		spnIVariaveis.setFont(new Font("FreeMono", Font.BOLD, 14));
		spnIVariaveis.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));

		// Botões
		btnComecar = new JButton("COMEÇAR");

		btnSalvarTipoDe = new JButton("SALVAR TIPO DE TABELA A SER USADO");

		// Scroll
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		// Tabela e seus componentes

		// ComboBox contendo tipos de objeto a ser adicionado.
		comboTipo = new JComboBox();
		comboTipo.setModel(new DefaultComboBoxModel(
				new String[] { "INT", "FLOAT", "CHAR", "VARCHAR", "TEXT", "DATE", "DATETIME" }));

		tabelaDeTipos = new JTable();
		tabelaDeTipos.setCellSelectionEnabled(true);
		tabelaDeTipos.setFillsViewportHeight(true);
		tabelaDeTipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Modelo da tabela
		//
		modelTipos = new DefaultTableModel(new Object[][] { { "", null, null, null, null, null, null, null, null }, },
				new String[] { "NOME", "TIPO", "TAMANHO", "PK", "UNIQUE", "AI", "Null", "Default Value", "SIGNED" }) {
			Class[] columnTypes = new Class[] { String.class, Object.class, Float.class, Boolean.class, Boolean.class,
					Boolean.class, Boolean.class, Object.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		tabelaDeTipos.setModel(modelTipos);
		tabelaDeTipos.getColumnModel().getColumn(0).setPreferredWidth(200);
		tabelaDeTipos.getColumnModel().getColumn(1).setPreferredWidth(193);

		// Essa pequena Gambiarra insere meu comboBox em uma coluna da tabela
		TableColumn colunaTipo = tabelaDeTipos.getColumnModel().getColumn(1);
		colunaTipo.setCellEditor(new DefaultCellEditor(comboTipo));

		// Caso o número de elementos da table ultrapasse a tela, um Scroll vai ajudar;
		scrollPane.setViewportView(tabelaDeTipos);

		// Adicionando elementos, devido ao Layout, a ordem de adição faz diferença.
		panelTabelaInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelTabelaInfo.add(lblNomeDaTabela);
		panelTabelaInfo.add(txtNomeDaTabela);
		panelTabelaInfo.add(lblqtElementos);
		panelTabelaInfo.add(spnIVariaveis);
		panelTabelaInfo.add(btnComecar);
		contentPane.add(btnSalvarTipoDe, BorderLayout.SOUTH);
	}

	public void criaAcoes() {
		// Ao clicar nesse botão, será pego a quantidade de elementos no Spinner.
		btnComecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nElementos = Integer.parseInt((spnIVariaveis.getValue()).toString());
				for (int i = 0; i < nElementos; i++) {
					modelTipos.addRow(new Object[] { null, null, null, null, null, null, null, null, null });

				}
			}
		});

		// Salvando a tabela
		btnSalvarTipoDe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int salva = JOptionPane.showConfirmDialog(btnSalvarTipoDe,
						"Realmente deseja salvar a tabela " + txtNomeDaTabela.getText() + "?");
				if (salva == 0) {

					// NOME TIPO TAMA NHO Null
					// verificaTamanhoElemento(tabelaDeTipos);
					try {
						salvarTable(tabelaDeTipos, txtNomeDaTabela.getText());
						JOptionPane.showMessageDialog(null, "Salvo com sucesso");
					} catch (Exception e1) {
						// TODO Auto-generated catch block

						JOptionPane.showMessageDialog(btnSalvarTipoDe, e1);
					}

				} else {
					JOptionPane.showMessageDialog(null, "Elemento ultrapassando tamanho suportado");
				}

			}
		});

	}

	public void salvarTable(JTable table, String nomeArquivo) throws Exception {
		FileWriter fw = new FileWriter("tipos/" + nomeArquivo + ".joett");
		BufferedWriter bfw = new BufferedWriter(fw);
		for (int i = 0; i < table.getColumnCount(); i++) {
			// bfw.write(table.getColumnName(i));
			// bfw.write("\t");
		} // for i

		for (int i = 0; i < table.getRowCount(); i++) {
			bfw.newLine();
			for (int j = 0; j < table.getColumnCount(); j++) {
				bfw.write(table.getValueAt(i, j) + "");
				if ((table.getValueAt(i, j) + "").length() == 0) {
					bfw.write("null");
				} // null
				bfw.write("\t");
			} // for j
		} // for i
		bfw.close();
		fw.close();
		fw = new FileWriter("tabelas/" + nomeArquivo + ".joetb");
		fw.close();
	}// salvarTable

	public void verificaTamanhoElemento(JTable table) {

		for (int i = 0; i < table.getRowCount() - 1; i++) {
			String tipo = (String) table.getValueAt(i, 1);

			//
			String tamanho[] = new String[3];

			System.out.println(tamanho[0] + tamanho.length);
			tamanho = table.getValueAt(i, 2).toString().split(",");
			int tamanhoInteiro = Integer.parseInt(tamanho[0]);
			System.out.println(tamanhoInteiro);

			if ((tipo == "INT" && tamanhoInteiro > 6)) {
				table.setValueAt(6, i, 2);
			} else {
				if (tamanho.length == 1) {
					int tamanhoFloat = Integer.parseInt(tamanho[1]);

					if ((tipo.equalsIgnoreCase("FLOAT") && tamanhoInteiro > 6) || (tipo.equalsIgnoreCase("FLOAT") && tamanhoFloat > 6)) {
						table.setValueAt(6.6, i, 2);
					}
				}
			}

		}
	}
}
