package gui.preteur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EmprunteurDAO;
import dao.JeuDAO;
import exo.Emprunteur;
import exo.Jeu;
import exo.Preteur;
import gui.Main;
import gui.administrateur.Ajouter_Jeu;
import gui.administrateur.Modifier_Jeu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class Dashboard_Preteur extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;
	
	/**
	 * Create the frame.
	 */
	public Dashboard_Preteur(Connection connect, Preteur currentPreteur) {
		this.connect=connect;
		this.currentPreteur=currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + currentPreteur.getNom() + " " + currentPreteur.getPrenom() + ", vous êtes connecté en tant que : Preteur");
		lblBienvenue.setBounds(10, 25, 414, 20);
		contentPane.add(lblBienvenue);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 327, 118, 28);
		contentPane.add(btnDeconnexion);
		
		JButton btnListeJeux = new JButton("Voir la liste des jeux");
		btnListeJeux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Liste_Jeux Liste_Jeux = new Liste_Jeux(connect, currentPreteur);
				Liste_Jeux.setVisible(true);
				Liste_Jeux.setResizable(false);
			}
		});
		btnListeJeux.setBounds(199, 76, 180, 28);
		contentPane.add(btnListeJeux);
		
		JButton btnListeJeuxAPreter = new JButton("Voir les jeux a pr\u00EAter");
		btnListeJeuxAPreter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Liste_Jeux_A_Preter liste_Jeux_A_Preter = new Liste_Jeux_A_Preter(connect, currentPreteur);
				liste_Jeux_A_Preter.setVisible(true);
				liste_Jeux_A_Preter.setResizable(false);
			}
		});
		btnListeJeuxAPreter.setBounds(199, 138, 183, 28);
		contentPane.add(btnListeJeuxAPreter);
		
		JButton btnVoirReservations = new JButton("Voir les r\u00E9servations");
		btnVoirReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Liste_Reservations liste_Reservations = new Liste_Reservations(connect, currentPreteur);
				liste_Reservations.setVisible(true);
				liste_Reservations.setResizable(false);
			}
		});
		btnVoirReservations.setBounds(199, 204, 182, 28);
		contentPane.add(btnVoirReservations);
		
		JButton btnCoterEmprunteurs = new JButton("C\u00F4ter les emprunteurs");
		btnCoterEmprunteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Coter_Emprunteurs coter_Emprunteurs = new Coter_Emprunteurs(connect, currentPreteur);
				coter_Emprunteurs.setVisible(true);
				coter_Emprunteurs.setResizable(false);
			}
		});
		btnCoterEmprunteurs.setBounds(199, 264, 183, 28);
		contentPane.add(btnCoterEmprunteurs);
		
		JLabel lblUnite = new JLabel("Unit\u00E9 :");
		lblUnite.setBounds(200, 334, 123, 14);
		contentPane.add(lblUnite);
		
		JLabel lblNombreUnite = new JLabel("");
		lblNombreUnite.setBounds(333, 334, 46, 14);
		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setiD(currentPreteur.getiD());
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteur = emprunteurDAO.findEmprunteurById(emprunteur);
		lblNombreUnite.setText(String.valueOf(emprunteur.getUnite()));
		contentPane.add(lblNombreUnite);
	}
}

