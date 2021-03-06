package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Administrateur;
import exo.Emprunteur;
import exo.Preteur;
import gui.administrateur.Dashboard_Administrateur;
import gui.emprunteur.Dashboard_Emprunteur;
import gui.preteur.Dashboard_Preteur;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Connexion extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5802302544469570734L;
	@SuppressWarnings("unused")
	private Connection connect;
	private JPanel contentPane;
	private JTextField textFieldUser;
	private Administrateur currentAdministrateur;
	private Preteur currentPreteur;
	private Emprunteur currentEmprunteur;
	private JPasswordField passwordField;

	/**
	 * Create the frame.
	 */
	public Connexion(Connection connect) {
		setTitle("Projet Jeux Video");
		this.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(73, 37, 46, 14);
		contentPane.add(lblUser);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 83, 83, 14);
		contentPane.add(lblPassword);

		textFieldUser = new JTextField();
		textFieldUser.setBounds(253, 31, 171, 27);
		contentPane.add(textFieldUser);
		textFieldUser.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(253, 80, 171, 27);
		contentPane.add(passwordField);
		
		JRadioButton rdbtnAdministrateur = new JRadioButton("Administrateur");
		rdbtnAdministrateur.setBounds(64, 131, 120, 23);
		contentPane.add(rdbtnAdministrateur);
		
		JRadioButton rdbtnPreteur = new JRadioButton("Pr\u00EAteur");
		rdbtnPreteur.setBounds(203, 131, 109, 23);
		contentPane.add(rdbtnPreteur);
		
		JRadioButton rdbtnEmprunteur = new JRadioButton("Emprunteur");
		rdbtnEmprunteur.setBounds(342, 131, 109, 23);
		contentPane.add(rdbtnEmprunteur);

		ButtonGroup personneRadio = new ButtonGroup();
		personneRadio.add(rdbtnAdministrateur);
		personneRadio.add(rdbtnPreteur);
		personneRadio.add(rdbtnEmprunteur);

		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(64, 212, 334, 24);
		contentPane.add(labelMsgErreur);

		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void choixTypePersonne() {
				labelMsgErreur.setText("Veuillez sélectionner un type de personne.");
			}

			@SuppressWarnings("deprecation")
			public boolean champsVide() {
				boolean vide = false;
				if (textFieldUser.getText().isEmpty() || passwordField.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					vide = true;
				}
				return vide;
			}

			public void loginpasswordIncorrect() {
				labelMsgErreur.setText("Login et/ou mot de passe incorrect.");

			}

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (rdbtnAdministrateur.isSelected()) {
					if (!champsVide()) {
						Administrateur administrateur = new Administrateur();
						if (administrateur.findByEmailPassword(textFieldUser.getText(),
								passwordField.getText(), connect)) {
							currentAdministrateur = administrateur.findAdministrateurByEmailPassword(
									textFieldUser.getText(), passwordField.getText(), connect);
							Dashboard_Administrateur dashboard_administrateur = new Dashboard_Administrateur(connect,
									currentAdministrateur);
							dispose();
							dashboard_administrateur.setVisible(true);
							dashboard_administrateur.setResizable(false);
						} else {
							loginpasswordIncorrect();
						}
					}
				} else if (rdbtnPreteur.isSelected()) {
					if (!champsVide()) {
						Preteur preteur = new Preteur();
						if (preteur.findByEmailPassword(textFieldUser.getText(), passwordField.getText(), connect)) {
							currentPreteur = preteur.findPreteurByEmailPassword(textFieldUser.getText(),
									passwordField.getText(), connect);
							Dashboard_Preteur dashboard_preteur = new Dashboard_Preteur(connect, currentPreteur);
							dispose();
							dashboard_preteur.setVisible(true);
							dashboard_preteur.setResizable(false);
						} else {
							loginpasswordIncorrect();
						}
					}
				} else if (rdbtnEmprunteur.isSelected()) {
					if (!champsVide()) {
						Emprunteur emprunteur = new Emprunteur();
						if (emprunteur.findByEmailPassword(textFieldUser.getText(), passwordField.getText(), connect)) {
							currentEmprunteur = emprunteur.findEmprunteurByEmailPassword(textFieldUser.getText(),
									passwordField.getText(), connect);
							Dashboard_Emprunteur dashboard_emprunteur = new Dashboard_Emprunteur(connect, currentEmprunteur);
							dispose();
							dashboard_emprunteur.setVisible(true);
							dashboard_emprunteur.setResizable(false);
						} else {
							loginpasswordIncorrect();
						}
					}
				} else {
					choixTypePersonne();
				}
			}
		});
		btnConnexion.setBounds(64, 178, 109, 23);
		contentPane.add(btnConnexion);

		JButton btnCreerCompte = new JButton("Cr\u00E9er un compte");
		btnCreerCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreerUser creerUser = new CreerUser(connect);
				dispose();
				creerUser.setVisible(true);
				creerUser.setResizable(false);
			}
		});
		btnCreerCompte.setBounds(271, 178, 153, 23);
		contentPane.add(btnCreerCompte);
	}
}
