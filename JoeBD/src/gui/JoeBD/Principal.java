package gui.JoeBD;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class Principal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 299, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnCriarNovaTabela = new JButton("Criar Nova Tabela");
		frame.getContentPane().add(btnCriarNovaTabela, BorderLayout.SOUTH);

		JButton btnVisualisarInserir = new JButton("Visualisar / Inserir");
		frame.getContentPane().add(btnVisualisarInserir, BorderLayout.NORTH);

		JLabel logo = new JLabel("");

		logo.setIcon(
				new ImageIcon(new ImageIcon("logo.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
		frame.getContentPane().add(logo, BorderLayout.CENTER);

		btnCriarNovaTabela.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PainelTipos tipos = new PainelTipos();
				tipos.main(null);
			}
		});

		btnVisualisarInserir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PainelTabela tabela = new PainelTabela();
				tabela.main(null);
			}
		});
	}

}
