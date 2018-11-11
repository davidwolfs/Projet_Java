package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EmprunteurDAO;
import exo.Administrateur;
import exo.Emprunteur;
import gui.Main;
import gui.preteur.Coter_Emprunteurs;

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
		setBounds(100, 100, 470, 324);
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
		btnDeconnexion.setBounds(10, 239, 110, 24);
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
		
		JButton btnCoterEmprunteurs = new JButton("C\u00F4ter les pr\u00EAteurs");
		btnCoterEmprunteurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Coter_Preteurs coter_Preteurs = new Coter_Preteurs(connect, currentEmprunteur);
				coter_Preteurs.setVisible(true);
				coter_Preteurs.setResizable(false);
			}
		});
		btnCoterEmprunteurs.setBounds(132, 198, 169, 24);
		contentPane.add(btnCoterEmprunteurs);
		
		JLabel lblUnite = new JLabel("Unit\u00E9 : ");
		lblUnite.setBounds(132, 244, 118, 14);
		contentPane.add(lblUnite);
		
		JLabel lblNombreUnite = new JLabel("");
		lblNombreUnite.setBounds(255, 244, 46, 14);
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		Emprunteur emprunteur;
		emprunteur = emprunteurDAO.findEmprunteurById(currentEmprunteur);
		lblNombreUnite.setText(String.valueOf(emprunteur.getUnite()));
		contentPane.add(lblNombreUnite);
		
		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(10, 271, 434, 14);
		contentPane.add(lblMsgError);
		
		if(currentEmprunteur.getUnite() <= 0)
		{
			btnPasserReservation.setEnabled(false);
			lblMsgError.setText("Vous n'avez plus d'unité. Veuillez vous connecter en tant que prêteur.");
		}
	}
}
