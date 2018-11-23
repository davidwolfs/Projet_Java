package gui.administrateur;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
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
import javax.swing.JScrollPane;

public class Gestion_Jeux_Consoles extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	@SuppressWarnings("unused")
	private Administrateur currentAdministrateur;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Gestion_Jeux_Consoles(Connection connect, Administrateur currentAdministrateur) {
		this.connect = connect;
		this.currentAdministrateur = currentAdministrateur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Liste des consoles");
		label.setBounds(12, 11, 116, 14);
		contentPane.add(label);
		Console c = new Console();
		List<Console> listConsole = c.findAll(connect);

		Object[] console = listConsole.toArray();

		Object[] donnees = new Object[listConsole.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listConsole.size(); i++) {
			System.out.println(listConsole.get(i).toString());
			donnees[i] = listConsole.get(i).getNom();
		}

		JLabel lblMsgErrorConsole = new JLabel("");
		lblMsgErrorConsole.setBounds(282, 266, 230, 23);
		contentPane.add(lblMsgErrorConsole);

		JButton btnAjouterConsole = new JButton("Ajouter une console");
		btnAjouterConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Ajouter_Console ajouter_Console = new Ajouter_Console(connect, currentAdministrateur);
				ajouter_Console.setVisible(true);
				ajouter_Console.setResizable(false);
			}
		});
		btnAjouterConsole.setBounds(12, 232, 175, 23);
		contentPane.add(btnAjouterConsole);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 764, 150);
		contentPane.add(scrollPane);

		JList listConsoles = new JList(donnees);
		scrollPane.setViewportView(listConsoles);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnModifierConsole = new JButton("Modifier une console");
		btnModifierConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorConsole.setText("Veuillez sélectionner une console.");
				} else {
					System.out.println(index);
					dispose();
					Modifier_Console modifier_Console = new Modifier_Console(connect, currentAdministrateur,
							listConsole.get(index));
					modifier_Console.setVisible(true);
					modifier_Console.setResizable(false);
				}
			}
		});
		btnModifierConsole.setBounds(289, 232, 175, 23);
		contentPane.add(btnModifierConsole);

		JButton btnSupprimerConsole = new JButton("Supprimer une console");
		btnSupprimerConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listConsoles.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgErrorConsole.setText("Veuillez sélectionner une console.");
				} else {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer cette console ?");
					if (input == 0) {
						int id = listConsole.get(index).getId();
						System.out.println(id);
						c.delete(listConsole.get(index), connect);

						dispose();
						Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect,
								currentAdministrateur);
						gestion_Jeux_Consoles.setVisible(true);
						gestion_Jeux_Consoles.setResizable(false);
					}
				}
			}
		});
		btnSupprimerConsole.setBounds(597, 232, 177, 23);
		contentPane.add(btnSupprimerConsole);

		JLabel lblListeJeu = new JLabel("Liste des jeux");
		lblListeJeu.setBounds(12, 288, 146, 14);
		contentPane.add(lblListeJeu);

		Jeu j = new Jeu();
		List<Jeu> listJeu = j.findAll(connect);

		Object[] jeu = listJeu.toArray();

		Object[] donnees2 = new Object[listJeu.size()];
		SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listJeu.size(); i++) {
			String dispo = " ";
			if (listJeu.get(i).isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}

			System.out.println(listJeu.get(i).toString());
			donnees2[i] = listJeu.get(i).getNom() + " - " + dispo + " - " + listJeu.get(i).getTarif() + " - "
					+ simpleDateFormat2.format(listJeu.get(i).getDateTarif()) + " - "
					+ listJeu.get(i).getConsole().getNom();
		}

		JLabel lblMsgErrorJeux = new JLabel("");
		lblMsgErrorJeux.setBounds(282, 527, 230, 23);
		contentPane.add(lblMsgErrorJeux);

		JButton btnAjouterJeu = new JButton("Ajouter un jeu");
		btnAjouterJeu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dispose();
				Ajouter_Jeu ajouter_Jeu = new Ajouter_Jeu(connect, currentAdministrateur);
				ajouter_Jeu.setVisible(true);
				ajouter_Jeu.setResizable(false);

			}
		});
		btnAjouterJeu.setBounds(12, 493, 175, 23);
		contentPane.add(btnAjouterJeu);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 313, 764, 150);
		contentPane.add(scrollPane_1);

		JList listJeux = new JList(donnees2);
		scrollPane_1.setViewportView(listJeux);
		listJeux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnModifierJeu = new JButton("Modifier un jeu");
		btnModifierJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorJeux.setText("Veuillez sélectionner un jeu.");
				} else {
					System.out.println(index);
					dispose();
					Modifier_Jeu modifier_Jeu = new Modifier_Jeu(connect, currentAdministrateur, listJeu.get(index));
					modifier_Jeu.setVisible(true);
					modifier_Jeu.setResizable(false);
				}

			}
		});
		btnModifierJeu.setBounds(290, 493, 174, 23);
		contentPane.add(btnModifierJeu);

		JButton btnSupprimerJeu = new JButton("Supprimer un jeu");
		btnSupprimerJeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgErrorJeux.setText("Veuillez sélectionner un jeu.");
				} else {
					int input = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de bien vouloir supprimer ce jeu ?");
					if (input == 0) {
						int id = listJeu.get(index).getId();
						System.out.println(id);
						j.delete(listJeu.get(index), connect);

						dispose();
						Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect,
								currentAdministrateur);
						gestion_Jeux_Consoles.setVisible(true);
						gestion_Jeux_Consoles.setResizable(false);
					}
				}
			}
		});
		btnSupprimerJeu.setBounds(600, 493, 174, 23);
		contentPane.add(btnSupprimerJeu);

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
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(connect,
						currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				dashboard_Administrateur.setResizable(false);
			}
		});
		btnRetour.setBounds(685, 527, 89, 23);
		contentPane.add(btnRetour);
	}
}
