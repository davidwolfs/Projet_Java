package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.AdministrateurDAO;
import dao.JeuDAO;
import exo.Administrateur;
import exo.Jeu;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;

public class Gestion_Jeux extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection connect;

	/**
	 * Create the frame.
	 */
	public Gestion_Jeux(Connection connect, Administrateur currentAdministrateur) {
		this.connect = connect;
		setTitle("Projet Jeux Video");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		/*
		 * table = new JTable(); table.setBackground(Color.BLUE); Object[] columns =
		 * {"ID", "Nom", "Dispo", "Tarif", "DateTarif", "AdapterTarif"};
		 * DefaultTableModel model = new DefaultTableModel();
		 * model.setColumnIdentifiers(columns); table.setModel(model);
		 * table.setVisible(true);
		 * 
		 * 
		 * table.setBounds(10, 84, 414, -72); contentPane.add(table);
		 */

		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeu = jeuDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] jeu = listJeu.toArray();

		JList list = new JList(jeu);
		list.setBounds(10, 46, 575, 67);
		contentPane.add(list);

		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.setBounds(10, 227, 115, 23);
		contentPane.add(btnDeconnexion);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(connect,
						currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				;
				dispose();
			}
		});
		btnRetour.setBounds(315, 227, 89, 23);
		contentPane.add(btnRetour);

		JButton btnAjouterJeu = new JButton("Ajouter");
		btnAjouterJeu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Ajouter_Jeu ajouter_Jeu = new Ajouter_Jeu(connect, currentAdministrateur);
				ajouter_Jeu.setVisible(true);
				ajouter_Jeu.setResizable(false);
				dispose();
			}
		});
		btnAjouterJeu.setBounds(10, 163, 89, 23);
		contentPane.add(btnAjouterJeu);

		JButton btnModifierJeu = new JButton("Modifier");
		btnModifierJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModifierJeu.setBounds(158, 163, 89, 23);
		contentPane.add(btnModifierJeu);

		JButton btnSupprimerJeu = new JButton("Supprimer");
		btnSupprimerJeu.setBounds(315, 163, 89, 23);
		contentPane.add(btnSupprimerJeu);

		JLabel lblListeJeu = new JLabel("Liste des jeux");
		lblListeJeu.setBounds(20, 11, 126, 14);
		contentPane.add(lblListeJeu);

	}
}
