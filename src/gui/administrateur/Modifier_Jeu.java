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
import com.toedter.calendar.JDateChooser;

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

public class Modifier_Jeu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1359226715577926028L;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldTarif;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Jeu jeuAModifier;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Modifier_Jeu(Connection connect, Administrateur currentAdministrateur, Jeu jeuAModifier) {
		this.connect = connect;
		this.jeuAModifier = jeuAModifier;
		setTitle("Modifier un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 457, 630);
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
		textFieldNom.setBounds(251, 45, 131, 20);
		textFieldNom.setText(jeuAModifier.getNom());
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		chckbxDisponibilite.setSelected(jeuAModifier.isDispo());
		contentPane.add(chckbxDisponibilite);

		textFieldTarif = new JTextField();
		textFieldTarif.setBounds(251, 126, 131, 20);
		textFieldTarif.setText(String.valueOf(jeuAModifier.getTarif()));
		textFieldTarif.setEnabled(false);
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);

		JDateChooser dateChooserDateTarif = new JDateChooser();
		dateChooserDateTarif.setBounds(251, 166, 131, 20);
		dateChooserDateTarif.setDate(jeuAModifier.getDateTarif());
		dateChooserDateTarif.setEnabled(false);
		contentPane.add(dateChooserDateTarif);

		JSpinner spinnerDiminuerTarif = new JSpinner();
		spinnerDiminuerTarif.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		spinnerDiminuerTarif.setBounds(251, 209, 46, 20);
		if (jeuAModifier.getTarif() <= 1) {
			spinnerDiminuerTarif.setEnabled(false);
		}
		contentPane.add(spinnerDiminuerTarif);

		JLabel lblListeConsoles = new JLabel("Console");
		lblListeConsoles.setBounds(69, 257, 77, 14);
		contentPane.add(lblListeConsoles);
		Console c = new Console();
		List<Console> listConsole = c.findAll(connect);

		Object[] donnees = new Object[listConsole.size()];

		for (int i = 0; i < listConsole.size(); i++) {
			donnees[i] = listConsole.get(i).getNom();
		}

		JList listConsoles = new JList(donnees);
		listConsoles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listConsoles.setBounds(251, 256, 131, 199);
		int index = -1;
		for (int i = 0; i < listConsole.size(); i++) {
			if (jeuAModifier.getConsole().getId() == listConsole.get(i).getId()) {
				index = i;
			}
		}
		listConsoles.setSelectedIndex(index);
		contentPane.add(listConsoles);

		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(69, 494, 313, 29);
		contentPane.add(labelMsgErreur);

		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {

			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || textFieldTarif.getText().isEmpty()
						|| ((JTextField) dateChooserDateTarif.getDateEditor().getUiComponent()).getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				} else if (listConsoles.getSelectedIndex() == -1) {
					labelMsgErreur.setText("Veuillez choisir une console.");
					valid = false;
				} else if ((double) spinnerDiminuerTarif.getValue() >= jeuAModifier.getTarif()) {
					labelMsgErreur.setText("Le nouveau tarif serait négatif ou égal à 0.");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					java.util.Date date = new java.util.Date();
					if ((double) spinnerDiminuerTarif.getValue() == 0) {
						date = jeuAModifier.getDateTarif();
					}
					
					Jeu jeu = new Jeu();
					jeuAModifier.setNom(textFieldNom.getText());
					jeuAModifier.setDispo((chckbxDisponibilite.isSelected()));
					jeuAModifier.setTarif((Double.parseDouble(textFieldTarif.getText())));
					jeuAModifier.setDateTarif(new Timestamp(date.getTime()));
					jeuAModifier.setConsole(listConsole.get(listConsoles.getSelectedIndex()));
					if (jeuAModifier.alreadyExist(jeuAModifier, connect)) {
						labelMsgErreur.setText("Ce jeu existe déjà pour cette console.");

					} else {

						jeuAModifier.adapterTarif((double) spinnerDiminuerTarif.getValue());
						jeu.update(jeuAModifier, connect);

						dispose();
						Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect,
								currentAdministrateur);
						gestion_Jeux_Consoles.setVisible(true);
						gestion_Jeux_Consoles.setResizable(false);
					}
				}

			}
		});
		btnModifier.setBounds(69, 557, 89, 23);
		contentPane.add(btnModifier);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Gestion_Jeux_Consoles gestion_Jeux_Consoles = new Gestion_Jeux_Consoles(connect, currentAdministrateur);
				gestion_Jeux_Consoles.setVisible(true);
				gestion_Jeux_Consoles.setResizable(false);
			}
		});
		btnRetour.setBounds(251, 557, 100, 23);
		contentPane.add(btnRetour);
	}
}
