package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Administrateur;
import gui.Main;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Dashboard_Administrateur extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Administrateur currentAdministrateur;
	/**
	 * Create the frame.
	 */
	public Dashboard_Administrateur(Connection connect, Administrateur currentAdministrateur) {
		this.currentAdministrateur=currentAdministrateur;
		this.connect=connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + currentAdministrateur.getPrenom() + " " + currentAdministrateur.getNom() + ", vous êtes connecté en tant que : Administrateur");
		lblBienvenue.setBounds(10, 27, 414, 21);
		contentPane.add(lblBienvenue);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 209, 117, 23);
		contentPane.add(btnDeconnexion);
		
		JButton btnGestionDesConsoles = new JButton("Gestion des consoles");
		btnGestionDesConsoles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Gestion_Consoles gestion_Consoles = new Gestion_Consoles(connect, currentAdministrateur);
				gestion_Consoles.setVisible(true);
				gestion_Consoles.setResizable(false);
			}
		});
		btnGestionDesConsoles.setBounds(152, 73, 189, 28);
		contentPane.add(btnGestionDesConsoles);
		
		JButton btnGestionDesJeux = new JButton("Gestion des jeux");
		btnGestionDesJeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Jeux gestion_Jeux = new Gestion_Jeux(connect, currentAdministrateur);
				gestion_Jeux.setVisible(true);
				gestion_Jeux.setResizable(false);
				dispose();
			}
		});
		btnGestionDesJeux.setBounds(152, 122, 189, 28);
		contentPane.add(btnGestionDesJeux);
		
		JButton btnGestionDesUtilisateurs = new JButton("Gestion des utilisateurs");
		btnGestionDesUtilisateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect, currentAdministrateur);
				gestion_Utilisateurs.setVisible(true);
				gestion_Utilisateurs.setResizable(false);
			}
		});
		btnGestionDesUtilisateurs.setBounds(152, 168, 189, 28);
		contentPane.add(btnGestionDesUtilisateurs);
	}
}
