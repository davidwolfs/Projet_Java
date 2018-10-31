package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ConsoleDAO;
import dao.JeuDAO;
import exo.Administrateur;
import exo.Console;
import exo.Jeu;
import gui.Connexion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class Gestion_Consoles extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Gestion_Consoles(Connection connect, Administrateur currentAdministrateur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 614, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeConsoles = new JLabel("Liste des consoles");
		lblListeConsoles.setBounds(23, 30, 116, 14);
		contentPane.add(lblListeConsoles);

		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		List<Console> listConsole = consoleDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] jeu = listConsole.toArray();

		Object[] donnees = new Object[listConsole.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listConsole.size(); i++) {
			System.out.println(listConsole.get(i).toString());
			donnees[i] = listConsole.get(i).getNom();
		}

		JList listConsoles = new JList(donnees);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(10, 46, 578, 226);
		contentPane.add(listConsoles);

		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(194, 401, 230, 23);
		contentPane.add(lblMsgError);

		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion(connect);
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		btnDeconnexion.setBounds(10, 401, 116, 23);
		contentPane.add(btnDeconnexion);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(connect,
						currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				dashboard_Administrateur.setResizable(false);
			}
		});
		btnRetour.setBounds(499, 401, 89, 23);
		contentPane.add(btnRetour);

		JButton btnAjouterConsole = new JButton("Ajouter une console");
		btnAjouterConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Ajouter_Console ajouter_Console = new Ajouter_Console(connect, currentAdministrateur);
				ajouter_Console.setVisible(true);
				ajouter_Console.setResizable(false);
			}
		});
		btnAjouterConsole.setBounds(10, 315, 175, 23);
		contentPane.add(btnAjouterConsole);

		JButton btnModifierConsole = new JButton("Modifier une console");
		btnModifierConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();

				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner une console.");
				} else {
					System.out.println(index);
					dispose();
					Modifier_Console modifier_Console = new Modifier_Console(connect,
							currentAdministrateur, listConsole.get(index));
					modifier_Console.setVisible(true);
					modifier_Console.setResizable(false);
				}
			}
		});
		btnModifierConsole.setBounds(218, 315, 175, 23);
		contentPane.add(btnModifierConsole);

		JButton btnSupprimerConsole = new JButton("Supprimer une console");
		btnSupprimerConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner une console.");
				} else {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer cette console ?");
					if (input == 0) {
						int id = listConsole.get(index).getId();
						System.out.println(id);
						consoleDAO.delete(listConsole.get(index));

						dispose();
						Gestion_Consoles gestion_Consoles = new Gestion_Consoles(connect, currentAdministrateur);
						gestion_Consoles.setVisible(true);
						gestion_Consoles.setResizable(false);
					}
				}
			}
		});
		btnSupprimerConsole.setBounds(411, 315, 177, 23);
		contentPane.add(btnSupprimerConsole);
	}
}
