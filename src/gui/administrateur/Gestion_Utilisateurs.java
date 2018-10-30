package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.AdministrateurDAO;
import dao.EmprunteurDAO;
import exo.Administrateur;
import exo.Emprunteur;
import exo.Jeu;
import gui.Connexion;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class Gestion_Utilisateurs extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the frame.
	 */
	public Gestion_Utilisateurs(Connection connect, Administrateur currentAdministrateur) {
		setTitle("Projet Jeux Video");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeAdministrateur = new JLabel("Liste des administrateurs");
		lblListeAdministrateur.setBounds(10, 11, 146, 14);
		contentPane.add(lblListeAdministrateur);

		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		List<Administrateur> listAdministrateur = administrateurDAO.findAll();

		Object[] donnees = new Object[listAdministrateur.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listAdministrateur.size(); i++) {
			System.out.println(listAdministrateur.get(i).toString());
			donnees[i] = listAdministrateur.get(i).getNom() + " " + listAdministrateur.get(i).getPrenom()
			 + " - " +  simpleDateFormat.format(listAdministrateur.get(i).getDateNaiss())
			+ " - " +  listAdministrateur.get(i).getEmail();
		}
		
		JList listAdministrateurs = new JList(donnees);
		listAdministrateurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAdministrateurs.setBounds(10, 36, 764, 150);
		contentPane.add(listAdministrateurs);
		
		
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion(connect);
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		btnDeconnexion.setBounds(10, 527, 118, 23);
		contentPane.add(btnDeconnexion);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(connect, currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				dashboard_Administrateur.setResizable(false);
			}
		});
		btnRetour.setBounds(685, 527, 89, 23);
		contentPane.add(btnRetour);

		JButton btnAjouterAdministrateur = new JButton("Ajouter un administrateur");
		btnAjouterAdministrateur.setBounds(10, 198, 200, 23);
		contentPane.add(btnAjouterAdministrateur);

		JButton btnModifierAdministrateur = new JButton("Modifier un administrateur");
		btnModifierAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = listAdministrateurs.getSelectedIndex();
				System.out.println(index);
				dispose();
				Modifier_Administrateur modifier_Administrateur = new Modifier_Administrateur(connect, currentAdministrateur, listAdministrateur.get(index));
				modifier_Administrateur.setVisible(true);
				modifier_Administrateur.setResizable(false);
			}
		});
		btnModifierAdministrateur.setBounds(318, 198, 185, 23);
		contentPane.add(btnModifierAdministrateur);

		JButton btnSupprimerAdministrateur = new JButton("Supprimer un administrateur");
		btnSupprimerAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de bien vouloir supprimer cette administrateur ?");
				if(input == 0)
				{
					int index = listAdministrateurs.getSelectedIndex();
					System.out.println(index);
					int id = listAdministrateur.get(index).getiD();
					System.out.println(id);
					administrateurDAO.delete(listAdministrateur.get(index));
					
					dispose();
					Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect, currentAdministrateur);
					gestion_Utilisateurs.setVisible(true);
					gestion_Utilisateurs.setResizable(false);
				}
			}
		});
		btnSupprimerAdministrateur.setBounds(560, 198, 200, 23);
		contentPane.add(btnSupprimerAdministrateur);

		JLabel lblListeParticipants = new JLabel("Liste des participants");
		lblListeParticipants.setBounds(10, 255, 146, 14);
		contentPane.add(lblListeParticipants);

		
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();

		Object[] donnees2 = new Object[listEmprunteurs.size()];

		for (int i = 0; i < listEmprunteurs.size(); i++) {
			System.out.println(listEmprunteurs.get(i).toString());
			donnees2[i] = listEmprunteurs.get(i).getNom() + " " + listEmprunteurs.get(i).getPrenom()
			 + " - " +  simpleDateFormat.format(listEmprunteurs.get(i).getDateNaiss())
			+ " - " +  listEmprunteurs.get(i).getEmail()
			+ " - " + listEmprunteurs.get(i).getUnite() + " U";
		}
		
		JList listEmprunteur = new JList(donnees2);
		listEmprunteur.setBounds(10, 296, 764, 150);
		contentPane.add(listEmprunteur);


		JButton btnNewButton = new JButton("Ajouter un participant");
		btnNewButton.setBounds(10, 474, 200, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Modifier un participant");
		btnNewButton_1.setBounds(318, 474, 185, 23);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Supprimer un participant");
		btnNewButton_2.setBounds(567, 474, 193, 23);
		contentPane.add(btnNewButton_2);

	}
}
