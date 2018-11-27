package gui.administrateur;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import exo.Administrateur;
import exo.Console;
import exo.Jeu;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Ajouter_Jeu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3562576914533804942L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	@SuppressWarnings("unused")
	private Connection connect;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Ajouter_Jeu(Connection connect, Administrateur currentAdministrateur) {
		this.connect = connect;
		setTitle("Ajouter un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 600);
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

		textFieldNom = new JTextField();
		textFieldNom.setBounds(250, 45, 131, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		contentPane.add(chckbxDisponibilite);

		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setBounds(69, 179, 77, 14);
		contentPane.add(lblListeConsoles);
		Console c = new Console();
		List<Console> listConsole = c.findAll(connect);

		Object[] donnees = new Object[listConsole.size()];

		for (int i = 0; i < listConsole.size(); i++) {
			donnees[i] = listConsole.get(i).getNom();
		}

		JList listConsoles = new JList(donnees);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(250, 178, 131, 180);
		contentPane.add(listConsoles);

		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(69, 459, 313, 29);
		contentPane.add(labelMsgErreur);

		JSpinner spinnerAjouterTarif = new JSpinner();
		spinnerAjouterTarif.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spinnerAjouterTarif.setBounds(250, 126, 44, 20);
		contentPane.add(spinnerAjouterTarif);

		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {

			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || chckbxDisponibilite.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				} else if ((double) spinnerAjouterTarif.getValue() <= 0.0) {
					labelMsgErreur.setText("Le tarif ne peut pas être égal à 0.");
					valid = false;
				} else if (listConsoles.getSelectedIndex() == -1) {
					labelMsgErreur.setText("Veuillez choisir une console.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					java.util.Date date = new java.util.Date();
					int index = listConsoles.getSelectedIndex();
					Console consoleChoisie = listConsole.get(index);
					Jeu jeu = new Jeu(textFieldNom.getText(), chckbxDisponibilite.isSelected(),
							(double) spinnerAjouterTarif.getValue(), new Timestamp(date.getTime()), consoleChoisie);
					jeu.setId(-1);
					if (jeu.alreadyExist(jeu, connect)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");
					} else {

						jeu.create(jeu, currentAdministrateur, connect);
						int lastId = jeu.findLastIdJeu(connect);
						jeu.setId(lastId);
						jeu.create_Ligne_Jeu(jeu, currentAdministrateur, connect);
						dispose();
						Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect,
								currentAdministrateur);
						gestion_Jeux_Consoles.setVisible(true);
						gestion_Jeux_Consoles.setResizable(false);
					}
				}

			}
		});
		btnAjouter.setBounds(69, 510, 89, 23);
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
		btnRetour.setBounds(266, 510, 100, 23);
		contentPane.add(btnRetour);
	}
}
