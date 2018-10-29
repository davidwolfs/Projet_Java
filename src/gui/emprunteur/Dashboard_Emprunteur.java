package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Emprunteur;
import gui.Main;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard_Emprunteur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_Emprunteur frame = new Dashboard_Emprunteur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Dashboard_Emprunteur(Emprunteur emprunteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + emprunteur.getPrenom() + " " + emprunteur.getNom() + ", vous êtes connecté en tant que : Emprunteur");
		lblBienvenue.setBounds(10, 26, 414, 24);
		contentPane.add(lblBienvenue);
		System.out.println(emprunteur.getUnite());
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 208, 110, 24);
		contentPane.add(btnDeconnexion);
		
		JButton btnNewButton = new JButton("Passer une r\u00E9servation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PasserReservation passerReservation = new PasserReservation();
				passerReservation.setVisible(true);
			}
		});
		btnNewButton.setBounds(130, 75, 171, 24);
		contentPane.add(btnNewButton);
	}
}
