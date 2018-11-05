package gui.preteur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.JeuDAO;
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
		btnListeJeux.setBounds(202, 109, 180, 28);
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
		btnListeJeuxAPreter.setBounds(199, 188, 183, 28);
		contentPane.add(btnListeJeuxAPreter);
	}
}

