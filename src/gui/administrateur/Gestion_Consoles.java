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
import gui.Connexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class Gestion_Consoles extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Gestion_Consoles(Connection connect, Administrateur currentAdministrateur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	

	

		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion(connect);
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		btnDeconnexion.setBounds(10, 401, 116, 23);
		contentPane.add(btnDeconnexion);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(connect,
						currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				dashboard_Administrateur.setResizable(false);
			}
		});
		btnRetour.setBounds(499, 401, 89, 23);
		contentPane.add(btnRetour);

	

	

	
		
		JButton btnAjouterJeu = new JButton("Ajouter un jeu");
		btnAjouterJeu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();

				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner une console.");
				} else {
					System.out.println(index);
					dispose();
					dispose();
					Ajouter_Jeu ajouter_Jeu = new Ajouter_Jeu(connect, currentAdministrateur);
					ajouter_Jeu.setVisible(true);
					ajouter_Jeu.setResizable(false);
				}
				
			
			}
		});
		btnAjouterJeu.setBounds(11, 354, 174, 23);
		contentPane.add(btnAjouterJeu);
	}
}
