package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Administrateur;
import exo.Emprunteur;
import gui.Main;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class Dashboard_Emprunteur extends JFrame {

	private JPanel contentPane;
	private Emprunteur currentEmprunteur;
	private Connection connect;

	/**
	 * Create the frame.
	 */
	public Dashboard_Emprunteur(Connection connect, Emprunteur currentEmprunteur) {
		this.connect = connect;
		this.currentEmprunteur = currentEmprunteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBienvenue = new JLabel("Bienvenue " + currentEmprunteur.getPrenom() + " " + currentEmprunteur.getNom()
				+ ", vous êtes connecté en tant que : Emprunteur");
		lblBienvenue.setBounds(10, 26, 414, 24);
		contentPane.add(lblBienvenue);
		System.out.println(currentEmprunteur.getUnite());
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 208, 110, 24);
		contentPane.add(btnDeconnexion);

		JButton btnPasserReservation = new JButton("Passer une r\u00E9servation");
		btnPasserReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Passer_Reservation passerReservation = new Passer_Reservation(connect, currentEmprunteur);
				passerReservation.setVisible(true);
				passerReservation.setResizable(false);
			}
		});
		btnPasserReservation.setBounds(130, 75, 171, 24);
		contentPane.add(btnPasserReservation);
		
		JButton btnVoirReservations = new JButton("Voir mes r\u00E9servations");
		btnVoirReservations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Voir_Reservation voir_Reservation = new Voir_Reservation(connect, currentEmprunteur);
				voir_Reservation.setVisible(true);
				voir_Reservation.setResizable(false);
			}
		});
		btnVoirReservations.setBounds(130, 138, 171, 24);
		contentPane.add(btnVoirReservations);
	}
}
