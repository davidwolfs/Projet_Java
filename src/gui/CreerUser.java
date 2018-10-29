package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dao.AdministrateurDAO;
import dao.EmprunteurDAO;
import dao.PreteurDAO;
import exo.Administrateur;
import exo.Emprunteur;
import exo.Preteur;
import gui.administrateur.Dashboard_Administrateur;
import gui.emprunteur.Dashboard_Emprunteur;
import gui.preteur.Dashboard_Preteur;

public class CreerUser extends JFrame {
	private Connection connect;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JDateChooser dateChooserDateNaiss;
	private JTextField textFieldEmail;
	private JTextField textFieldPassword;
	private JRadioButton rdbtnAdministrateur;
	private JRadioButton rdbtnPreteur;
	private JRadioButton rdbtnEmprunteur;
	private JButton btnRetour;
	private JLabel labelMsgErreur;

	/**
	 * Create the frame.
	 */
	public CreerUser(Connection connect) {
		setTitle("Projet Jeux Video");
		this.connect = connect;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNom = new JLabel("Nom (*)");
		lblNom.setBounds(24, 24, 46, 14);
		contentPane.add(lblNom);

		JLabel lblPrenom = new JLabel("Prenom (*)");
		lblPrenom.setBounds(24, 71, 72, 14);
		contentPane.add(lblPrenom);

		JLabel lblDateNaiss = new JLabel("Date de naissance (*)");
		lblDateNaiss.setBounds(24, 116, 137, 17);
		contentPane.add(lblDateNaiss);

		JLabel lblEmail = new JLabel("Email (*)");
		lblEmail.setBounds(24, 163, 61, 14);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password (*)");
		lblPassword.setBounds(24, 213, 89, 17);
		contentPane.add(lblPassword);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(233, 21, 162, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(233, 68, 162, 20);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		dateChooserDateNaiss = new JDateChooser();
		dateChooserDateNaiss.setBounds(233, 113, 162, 20);
		contentPane.add(dateChooserDateNaiss);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(233, 160, 162, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(233, 210, 162, 20);
		contentPane.add(textFieldPassword);
		textFieldPassword.setColumns(10);

		rdbtnAdministrateur = new JRadioButton("Administrateur");
		rdbtnAdministrateur.setBounds(21, 275, 120, 23);
		contentPane.add(rdbtnAdministrateur);

		rdbtnPreteur = new JRadioButton("Preteur");
		rdbtnPreteur.setBounds(163, 275, 109, 23);
		contentPane.add(rdbtnPreteur);

		rdbtnEmprunteur = new JRadioButton("Emprunteur");
		rdbtnEmprunteur.setBounds(286, 275, 109, 23);
		contentPane.add(rdbtnEmprunteur);

		ButtonGroup personneRadio = new ButtonGroup();
		personneRadio.add(rdbtnAdministrateur);
		personneRadio.add(rdbtnPreteur);
		personneRadio.add(rdbtnEmprunteur);
		
		JButton btnCreerUser = new JButton("Cr\u00E9er");
		btnCreerUser.addActionListener(new ActionListener() {
			public void choixTypePersonne() {
				labelMsgErreur.setText("Veuillez sélectionner un type de personne.");
			}

			public boolean champsVide() {
				String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
				String regex2 = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(janv.|févr.|mars|avr.|mai|juin|juil.|août|sept.|oct.|nov.|déc.)\\-\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher = pattern.matcher(textFieldEmail.getText());
				Matcher matcher2 = pattern2.matcher(((JTextField)dateChooserDateNaiss.getDateEditor().getUiComponent()).getText());
				System.out.println();
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || textFieldPrenom.getText().isEmpty()
						|| ((JTextField)dateChooserDateNaiss.getDateEditor().getUiComponent()).getText().isEmpty() || textFieldEmail.getText().isEmpty()
						|| textFieldPassword.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				else if (!(matcher.matches())) {
					labelMsgErreur.setText("Veuillez entrer un e-mail valide.");
					valid = false;
				}
				
				else if (!(matcher2.matches())) {
					labelMsgErreur.setText("Veuillez entrer une date de naissance sous le format \"dd-MMM-yyyy\"");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				if (rdbtnAdministrateur.isSelected()) {
					if (champsVide()) {
						AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
						Administrateur administrateur = new Administrateur(textFieldNom.getText(),
								textFieldPrenom.getText(), dateChooserDateNaiss.getDate(), textFieldEmail.getText(),
								textFieldPassword.getText());
						System.out.println("DATE STRING : " + ((JTextField)dateChooserDateNaiss.getDateEditor().getUiComponent()).getText());
						//System.out.println("DATE FORMAT STRING IS EMPTY : " + dateChooserDateNaiss.getValue().isEmpty());
						System.out.println("DATE IS VALID : " + dateChooserDateNaiss.getDateFormatString());
						if (administrateurDAO.alreadyExist(textFieldEmail.getText())) {
							labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
						} else {

							administrateurDAO.create(administrateur);
							dispose();
							Dashboard_Administrateur dashboard_administrateur = new Dashboard_Administrateur(
									administrateur);
							dashboard_administrateur.setVisible(true);
						}
					}
				} else if (rdbtnPreteur.isSelected()) {
					if (champsVide()) {
						PreteurDAO preteurDAO = new PreteurDAO(connect);
						Preteur preteur = new Preteur(textFieldNom.getText(), textFieldPrenom.getText(),
								dateChooserDateNaiss.getDate(), textFieldEmail.getText(), textFieldPassword.getText());
						System.out.println("DATE DAO : " + dateChooserDateNaiss.getDate());
						if (preteurDAO.alreadyExist(textFieldEmail.getText())) {
							labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
						} else {
							preteurDAO.create(preteur);
							dispose();
							Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(preteur);
							dashboard_Preteur.setVisible(true);
						}
					}
				}

				else if (rdbtnEmprunteur.isSelected()) {
					if (champsVide()) {
						EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
						Emprunteur emprunteur = new Emprunteur(textFieldNom.getText(), textFieldPrenom.getText(),
								dateChooserDateNaiss.getDate(), textFieldEmail.getText(), textFieldPassword.getText());
						System.out.println("DATE DAO : " + dateChooserDateNaiss.getDate());
						if (emprunteurDAO.alreadyExist(textFieldEmail.getText())) {
							labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
						} else {
							emprunteurDAO.create(emprunteur);
							dispose();
							Dashboard_Emprunteur dashboard_Emprunteur = new Dashboard_Emprunteur(emprunteur);
							dashboard_Emprunteur.setVisible(true);
						}
					}
				} else {
					choixTypePersonne();
				}
			}
		});
		btnCreerUser.setBounds(41, 341, 89, 23);
		contentPane.add(btnCreerUser);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion(connect);
				connexion.setVisible(true);
			}
		});
		btnRetour.setBounds(277, 341, 89, 23);
		contentPane.add(btnRetour);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(41, 375, 325, 23);
		contentPane.add(labelMsgErreur);
	}
}
