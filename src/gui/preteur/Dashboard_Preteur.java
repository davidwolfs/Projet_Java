package gui.preteur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Jeu;
import exo.Preteur;
import gui.Main;
import gui.administrateur.Ajouter_Jeu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Dashboard_Preteur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_Preteur frame = new Dashboard_Preteur();
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
	public Dashboard_Preteur(Preteur preteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + preteur.getNom() + " " + preteur.getPrenom() + ", vous êtes connecté en tant que : Preteur");
		lblBienvenue.setBounds(10, 25, 414, 20);
		contentPane.add(lblBienvenue);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 203, 118, 28);
		contentPane.add(btnDeconnexion);
		
		List<Jeu> listJeu = new ArrayList<>();
		Jeu jeu = new Jeu("GTA V", true, 50, new Date("26/10/2018"), "Tarif");
		listJeu.add(jeu);
		
		
				//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] jeux = listJeu.toArray();
		
		JList list = new JList(jeux);
		list.setBounds(10, 81, 414, 100);
		contentPane.add(list);
		
		JButton btnAjouterExemplaire = new JButton("Ajouter un exemplaire");
		btnAjouterExemplaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Ajouter_Jeu ajouterJeu = new Ajouter_Jeu();
				ajouterJeu.setVisible(true);
				dispose();*/
			}
		});
		btnAjouterExemplaire.setBounds(168, 206, 149, 25);
		contentPane.add(btnAjouterExemplaire);
	}
}
