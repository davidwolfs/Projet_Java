package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ConsoleDAO;
import dao.JeuDAO;
import exo.Administrateur;
import exo.Console;
import exo.Jeu;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Ajouter_Console extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JButton btnAjouterConsole;
	private JLabel lblMsgError;
	private JButton btnRetour;
	private Connection connect;
	/**
	 * Create the frame.
	 */
	public Ajouter_Console(Connection connect, Administrateur currentAdministrateur) {
		this.connect=connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(87, 66, 79, 14);
		contentPane.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(317, 64, 130, 17);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		lblMsgError = new JLabel("");
		lblMsgError.setBounds(169, 277, 220, 14);
		contentPane.add(lblMsgError);
		
		btnAjouterConsole = new JButton("Ajouter");
		btnAjouterConsole.addActionListener(new ActionListener() {
				public boolean champsVide() {
					boolean valid = true;
					if (textFieldNom.getText().isEmpty()) {
						lblMsgError.setText("Veuillez remplir tous les champs.");
						valid = false;
					}

					return valid;
				}
				
				public void actionPerformed(ActionEvent e) {
					if (champsVide()) {
						ConsoleDAO consoleDAO = new ConsoleDAO(connect);
						/*if (jeuDAO.alreadyExist(textFieldEmail.getText())) {
							labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
						} else {*/
							
							Console console = new Console(textFieldNom.getText());
							consoleDAO.create(console);
							
							dispose();
							Gestion_Consoles gestion_Consoles = new Gestion_Consoles(connect, currentAdministrateur);
							gestion_Consoles.setVisible(true);
							gestion_Consoles.setResizable(false);
						//}
					}
					
				}
		});
		btnAjouterConsole.setBounds(53, 247, 113, 23);
		contentPane.add(btnAjouterConsole);
	
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect, currentAdministrateur);
				gestion_Jeux_Consoles.setVisible(true);
				gestion_Jeux_Consoles.setResizable(false);
				
			}
		});
		btnRetour.setBounds(393, 247, 89, 23);
		contentPane.add(btnRetour);
	}
}
