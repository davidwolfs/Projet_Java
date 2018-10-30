package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdministrateurDAO;
import exo.Administrateur;
import exo.Jeu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;

public class Gestion_Utilisateurs extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Gestion_Utilisateurs(Connection connect, Administrateur currentAdministrateur) {
		setTitle("Projet Jeux Video");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListeUtilisateurs = new JLabel("Liste des utilisateurs");
		lblListeUtilisateurs.setBounds(10, 11, 146, 14);
		contentPane.add(lblListeUtilisateurs);
		
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		List<Administrateur> listAdministrateur = administrateurDAO.findAll();
	
		
		
		
				//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] administrateur = listAdministrateur.toArray();
		
				//List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
	
		
		
		JList list = new JList(administrateur);
		list.setBounds(10, 46, 575, 67);
		contentPane.add(list);
		
		
		JList list2 = new JList();
		list2.setBounds(10, 46, 575, 67);
		contentPane.add(list2);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.setBounds(10, 406, 118, 23);
		contentPane.add(btnDeconnexion);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(481, 406, 89, 23);
		contentPane.add(btnRetour);
		
		JButton btnAjouterUtilisateur = new JButton("Ajouter un utilisateur");
		btnAjouterUtilisateur.setBounds(10, 277, 146, 23);
		contentPane.add(btnAjouterUtilisateur);
		
		JButton btnModifierUtilisateur = new JButton("Modifier un utilisateur");
		btnModifierUtilisateur.setBounds(10, 311, 146, 23);
		contentPane.add(btnModifierUtilisateur);
		
		JButton btnSupprimerUtilisateur = new JButton("Supprimer l'utilisateur");
		btnSupprimerUtilisateur.setBounds(10, 345, 146, 23);
		contentPane.add(btnSupprimerUtilisateur);
	}
}
