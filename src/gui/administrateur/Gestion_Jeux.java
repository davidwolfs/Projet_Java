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
import gui.Connexion;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
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
		setBounds(100, 100, 600, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeJeu = new JLabel("Liste des jeux");
		lblListeJeu.setBounds(20, 11, 126, 14);
		contentPane.add(lblListeJeu);
		
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


		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion(connect);
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		btnDeconnexion.setBounds(20, 342, 115, 23);
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
		btnRetour.setBounds(474, 342, 89, 23);
		contentPane.add(btnRetour);

		

		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(220, 361, 185, 30);
		contentPane.add(lblMsgError);
		
	
		
	
	}
}
