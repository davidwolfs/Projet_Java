package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import dao.ConsoleDAO;
import dao.EmprunteurDAO;
import dao.JeuDAO;
import dao.PreteurDAO;
import exo.Administrateur;
import exo.Console;
import exo.Emprunteur;
import exo.Jeu;
import exo.Preteur;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Ajouter_Jeu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldTarif;
	private JTextField textFieldAdapterTarif;
	private Connection connect;

	/**
	 * Create the frame.
	 */
	public Ajouter_Jeu(Connection connect, Administrateur currentAdministrateur) {
		this.connect = connect;
		setTitle("Ajouter un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom du jeu");
		lblNom.setBounds(69, 48, 89, 14);
		contentPane.add(lblNom);

		JLabel lblDisponibilite = new JLabel("Disponibilite");
		lblDisponibilite.setBounds(69, 91, 89, 14);
		contentPane.add(lblDisponibilite);

		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setBounds(69, 129, 89, 14);
		contentPane.add(lblTarif);

		JLabel lblDateTarif = new JLabel("DateTarif");
		lblDateTarif.setBounds(69, 166, 89, 14);
		contentPane.add(lblDateTarif);

		JLabel lblAdapterTarif = new JLabel("AdapterTarif");
		lblAdapterTarif.setBounds(69, 212, 89, 14);
		contentPane.add(lblAdapterTarif);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(250, 45, 131, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		contentPane.add(chckbxDisponibilite);

		textFieldTarif = new JTextField();
		textFieldTarif.setBounds(251, 126, 131, 20);
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);

		JDateChooser dateChooserDateTarif = new JDateChooser();
		dateChooserDateTarif.setBounds(251, 166, 131, 20);
		contentPane.add(dateChooserDateTarif);

		textFieldAdapterTarif = new JTextField();
		textFieldAdapterTarif.setBounds(251, 209, 131, 20);
		contentPane.add(textFieldAdapterTarif);
		textFieldAdapterTarif.setColumns(10);

		
		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setBounds(69, 257, 77, 14);
		contentPane.add(lblListeConsoles);
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		List<Console> listConsole = consoleDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] console = listConsole.toArray();

		Object[] donnees = new Object[listConsole.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listConsole.size(); i++) {
			System.out.println(listConsole.get(i).toString());
			donnees[i] = listConsole.get(i).getNom();
		}

		JList listConsoles = new JList(donnees);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(250, 240, 131, 180);
		contentPane.add(listConsoles);
		
		
		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(69, 360, 313, 29);
		contentPane.add(labelMsgErreur);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {

			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || chckbxDisponibilite.getText().isEmpty()
						|| textFieldTarif.getText().isEmpty()
						|| ((JTextField) dateChooserDateTarif.getDateEditor().getUiComponent()).getText().isEmpty()
						|| textFieldAdapterTarif.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					JeuDAO jeuDAO = new JeuDAO(connect);
					int index = listConsoles.getSelectedIndex();
					Console consoleChoisie = listConsole.get(index);
					Jeu jeu = new Jeu(textFieldNom.getText(), chckbxDisponibilite.isSelected(),
							Double.parseDouble(textFieldTarif.getText()), dateChooserDateTarif.getDate(),
							textFieldAdapterTarif.getText(), consoleChoisie);
					jeu.setId(-1);
					if (jeuDAO.alreadyExist(jeu)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");
					} else {

						System.out.println(jeu.getNom() + " " + jeu.isDispo() + " " + jeu.getTarif() + " "
								+ jeu.getDateTarif() + " " + jeu.getAdapterTarif() + " " + jeu.getConsole());
						jeuDAO.create(jeu);
						int lastId = jeuDAO.findLastIdJeu();
						jeu.setId(lastId);
						jeuDAO.create_Ligne_Jeu(jeu);
						dispose();
						Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect,
								currentAdministrateur);
						gestion_Jeux_Consoles.setVisible(true);
						gestion_Jeux_Consoles.setResizable(false);
					}
				}

			}
		});
		btnAjouter.setBounds(69, 448, 89, 23);
		contentPane.add(btnAjouter);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect, currentAdministrateur);
				gestion_Jeux_Consoles.setVisible(true);
				gestion_Jeux_Consoles.setResizable(false);
				dispose();
			}
		});
		btnRetour.setBounds(270, 448, 100, 23);
		contentPane.add(btnRetour);
	}
}
