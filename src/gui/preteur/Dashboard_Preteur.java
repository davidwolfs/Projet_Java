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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Dashboard_Preteur extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	/**
	 * Create the frame.
	 */
	public Dashboard_Preteur(Connection connect, Preteur preteur) {
		this.connect=connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 413);
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
		btnDeconnexion.setBounds(10, 327, 118, 28);
		contentPane.add(btnDeconnexion);
		
		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeu = jeuDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] jeu = listJeu.toArray();

		Object[] donnees = new Object[listJeu.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listJeu.size(); i++) {
			String dispo = " ";
			if(listJeu.get(i).isDispo())
			{
				dispo = "Disponible";
			}
			else
			{
				dispo = "Indisponible";
			}
			
			System.out.println(listJeu.get(i).toString());
			donnees[i] = listJeu.get(i).getNom() + " - "
					+ dispo + " - "
					+ listJeu.get(i).getTarif() + " - " 
					+ simpleDateFormat.format(listJeu.get(i).getDateTarif()) + " - "
					+ listJeu.get(i).getAdapterTarif();
		}
		
		JList listJeux = new JList(donnees);
		listJeux.setBounds(10, 46, 564, 214);
		contentPane.add(listJeux);
		
		JButton btnAjouterExemplaire = new JButton("Ajouter un exemplaire");
		btnAjouterExemplaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*Ajouter_Jeu ajouterJeu = new Ajouter_Jeu();
				ajouterJeu.setVisible(true);
				dispose();*/
			}
		});
		btnAjouterExemplaire.setBounds(196, 329, 195, 26);
		contentPane.add(btnAjouterExemplaire);
	}
}
