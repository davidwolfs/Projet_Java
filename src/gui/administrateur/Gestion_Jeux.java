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

		JButton btnAjouterJeu = new JButton("Ajouter");
		btnAjouterJeu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				Ajouter_Jeu ajouter_Jeu = new Ajouter_Jeu(connect, currentAdministrateur);
				ajouter_Jeu.setVisible(true);
				ajouter_Jeu.setResizable(false);
			}
		});
		btnAjouterJeu.setBounds(20, 287, 102, 23);
		contentPane.add(btnAjouterJeu);

		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(145, 361, 319, 30);
		contentPane.add(lblMsgError);
		
		JButton btnModifierJeu = new JButton("Modifier");
		btnModifierJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();

				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un jeu.");
				} else {
					System.out.println(index);
					dispose();
					Modifier_Jeu modifier_Jeu = new Modifier_Jeu(connect,
							currentAdministrateur, listJeu.get(index));
					modifier_Jeu.setVisible(true);
					modifier_Jeu.setResizable(false);
				}
				
			}
		});
		btnModifierJeu.setBounds(245, 287, 102, 23);
		contentPane.add(btnModifierJeu);
		
		JButton btnSupprimerJeu = new JButton("Supprimer");
		btnSupprimerJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un jeu.");
				} else {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer ce jeu ?");
					if (input == 0) {
						int id = listJeu.get(index).getId();
						System.out.println(id);
						jeuDAO.delete(listJeu.get(index));

						dispose();
						Gestion_Jeux gestion_Jeux = new Gestion_Jeux(connect,
								currentAdministrateur);
						gestion_Jeux.setVisible(true);
						gestion_Jeux.setResizable(false);
					}
				}
			}
		});
		btnSupprimerJeu.setBounds(472, 287, 102, 23);
		contentPane.add(btnSupprimerJeu);
	}
}
