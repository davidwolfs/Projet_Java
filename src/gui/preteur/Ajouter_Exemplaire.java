package gui.preteur;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import exo.Console;
import exo.Exemplaire;
import exo.Jeu;
import exo.Preteur;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Ajouter_Exemplaire extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8557487527970742323L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Preteur currentPreteur;
	@SuppressWarnings("unused")
	private Jeu jeuAModifier;

	/**
	 * Create the frame.
	 */
	public Ajouter_Exemplaire(Connection connect, Preteur currentPreteur, Jeu jeuAModifier) {
		this.connect = connect;
		this.currentPreteur = currentPreteur;
		this.jeuAModifier = jeuAModifier;
		setTitle("Ajouter un exemplaire");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 645);
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

		JLabel lblNom2 = new JLabel("Nom du jeu");
		lblNom2.setBounds(251, 45, 131, 20);
		lblNom2.setText(jeuAModifier.getNom());
		contentPane.add(lblNom2);

		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		chckbxDisponibilite.setSelected(true);
		chckbxDisponibilite.setEnabled(false);
		contentPane.add(chckbxDisponibilite);

		JLabel lblTarif2 = new JLabel("Tarif");
		lblTarif2.setBounds(251, 126, 131, 20);
		lblTarif2.setText(String.valueOf(jeuAModifier.getTarif()));
		contentPane.add(lblTarif2);

		JDateChooser dateChooserDateTarif = new JDateChooser();
		dateChooserDateTarif.setBounds(251, 166, 131, 20);
		dateChooserDateTarif.setDate(jeuAModifier.getDateTarif());
		dateChooserDateTarif.setEnabled(false);
		contentPane.add(dateChooserDateTarif);

		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setBounds(69, 213, 77, 14);
		contentPane.add(lblListeConsoles);
		Console c = new Console();
		List<Console> listConsole = c.findAll(connect);

		Object[] donnees = new Object[listConsole.size()];
		for (int i = 0; i < listConsole.size(); i++) {
			donnees[i] = listConsole.get(i).getNom();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList listConsoles = new JList(donnees);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(251, 224, 131, 199);
		listConsoles.setEnabled(false);

		int index = -1;
		for (int i = 0; i < listConsole.size(); i++) {
			if (jeuAModifier.getConsole().getId() == listConsole.get(i).getId()) {
				index = i;
			}
		}
		
		listConsoles.setSelectedIndex(index);
		contentPane.add(listConsoles);

		JLabel lblNombreExemplaires = new JLabel("Nombre d'exemplaires");
		lblNombreExemplaires.setBounds(69, 468, 146, 20);
		contentPane.add(lblNombreExemplaires);

		JSpinner spinnerNombreExemplaires = new JSpinner();
		spinnerNombreExemplaires.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNombreExemplaires.setBounds(250, 468, 40, 20);
		contentPane.add(spinnerNombreExemplaires);

		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(69, 532, 313, 29);
		contentPane.add(labelMsgErreur);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {

			public boolean champsVide() {
				boolean valid = true;

				if ((int) spinnerNombreExemplaires.getValue() <= 0) {
					labelMsgErreur.setText("Veuillez entrez un nombre supérieur à 0.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					Jeu jeu = new Jeu();
					Exemplaire ex = new Exemplaire();
					jeuAModifier.setNom(lblNom2.getText());
					jeuAModifier.setDispo((chckbxDisponibilite.isSelected()));
					jeuAModifier.setTarif((Double.parseDouble(lblTarif2.getText())));
					jeuAModifier.setDateTarif((dateChooserDateTarif.getDate()));
					jeuAModifier.setConsole(listConsole.get(listConsoles.getSelectedIndex()));

					int nombreExemplaires = (int) spinnerNombreExemplaires.getValue();

					Exemplaire exemplaire = null;
					for (int i = 0; i < nombreExemplaires; i++) {
						exemplaire = new Exemplaire(jeuAModifier);
						currentPreteur.AjouterExemplaire(exemplaire);
						ex.create_Exemplaire(exemplaire, currentPreteur, connect);
					}
					jeuAModifier.setDispo(true);
					jeu.update_Dispo(jeuAModifier, connect);

					if (jeuAModifier.alreadyExist(jeuAModifier, connect)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");

					} else {
						dispose();
						Liste_Jeux liste_Jeux = new Liste_Jeux(connect, currentPreteur);
						liste_Jeux.setVisible(true);
						liste_Jeux.setResizable(false);
					}
				}

			}
		});

		btnValider.setBounds(69, 572, 89, 23);
		contentPane.add(btnValider);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(connect, currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
		btnRetour.setBounds(251, 572, 100, 23);
		contentPane.add(btnRetour);
	}
}
