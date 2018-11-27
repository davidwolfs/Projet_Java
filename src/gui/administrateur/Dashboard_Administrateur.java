package gui.administrateur;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import exo.Administrateur;
import gui.Main;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Dashboard_Administrateur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4972326230545344791L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Administrateur currentAdministrateur;

	/**
	 * Create the frame.
	 */
	public Dashboard_Administrateur(Connection connect, Administrateur currentAdministrateur) {
		this.currentAdministrateur = currentAdministrateur;
		this.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBienvenue = new JLabel("Bienvenue " + currentAdministrateur.getPrenom() + " "
				+ currentAdministrateur.getNom() + ", vous êtes connecté en tant que : Administrateur");
		lblBienvenue.setBounds(10, 27, 414, 21);
		contentPane.add(lblBienvenue);

		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 251, 117, 23);
		contentPane.add(btnDeconnexion);

		JButton btnGestionDesUtilisateurs = new JButton("Gestion des utilisateurs");
		btnGestionDesUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect, currentAdministrateur);
				gestion_Utilisateurs.setVisible(true);
				gestion_Utilisateurs.setResizable(false);
			}
		});
		btnGestionDesUtilisateurs.setBounds(152, 88, 189, 28);
		contentPane.add(btnGestionDesUtilisateurs);

		JButton btnGestionJeuxConsoles = new JButton("Gestion des jeux/consoles");
		btnGestionJeuxConsoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect, currentAdministrateur);
				gestion_Jeux_Consoles.setVisible(true);
				gestion_Jeux_Consoles.setResizable(false);
			}
		});
		btnGestionJeuxConsoles.setBounds(152, 156, 187, 28);
		contentPane.add(btnGestionJeuxConsoles);
	}
}
