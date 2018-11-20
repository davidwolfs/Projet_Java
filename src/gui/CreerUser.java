package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import dao.EmprunteurDAO;
import dao.PreteurDAO;
import exo.Emprunteur;
import exo.Preteur;
import javax.swing.JPasswordField;

public class CreerUser extends JFrame {
	private Connection connect;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JDateChooser dateChooserDateNaiss;
	private JTextField textFieldEmail;
	private JButton btnRetour;
	private JLabel labelMsgErreur;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;

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

		JLabel lblConfirmPassword = new JLabel("Confirmer password (*)");
		lblConfirmPassword.setBounds(24, 265, 137, 14);
		contentPane.add(lblConfirmPassword);

		textFieldNom = new JTextField();
		textFieldNom.setBounds(233, 21, 162, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(233, 68, 162, 20);
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		dateChooserDateNaiss = new JDateChooser();
		dateChooserDateNaiss.setBounds(233, 115, 162, 20);
		contentPane.add(dateChooserDateNaiss);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(233, 160, 162, 20);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(233, 209, 162, 20);
		contentPane.add(passwordField);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(233, 262, 162, 20);
		contentPane.add(confirmPasswordField);

		JButton btnCreerUser = new JButton("Cr\u00E9er");
		btnCreerUser.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public boolean champsVide() {
				String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
				String regex2 = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(janv.|févr.|mars|avr.|mai|juin|juil.|août|sept.|oct.|nov.|déc.)\\-\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher = pattern.matcher(textFieldEmail.getText());
				Matcher matcher2 = pattern2
						.matcher(((JTextField) dateChooserDateNaiss.getDateEditor().getUiComponent()).getText());
				System.out.println();
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || textFieldPrenom.getText().isEmpty()
						|| ((JTextField) dateChooserDateNaiss.getDateEditor().getUiComponent()).getText().isEmpty()
						|| textFieldEmail.getText().isEmpty() || passwordField.getText().isEmpty()
						|| confirmPasswordField.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				else if (!(matcher.matches())) {
					labelMsgErreur.setText("Veuillez entrer un e-mail valide.");
					valid = false;
				}

				else if (!(matcher2.matches())) {
					labelMsgErreur.setText("Format de date de naissance attendu \"dd-MMM-yyyy\".");
					valid = false;
				}

				else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
					labelMsgErreur.setText("Les mots de passes doivent être identiques.");
					valid = false;
				}

				return valid;
			}

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					PreteurDAO preteurDAO = new PreteurDAO(connect);
					Preteur preteur = new Preteur(textFieldNom.getText(), textFieldPrenom.getText(),
							dateChooserDateNaiss.getDate(), textFieldEmail.getText(), passwordField.getText());
					preteur.setiD(-1);
					System.out.println("DATE DAO : " + dateChooserDateNaiss.getDate());
					EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
					Emprunteur emprunteur = new Emprunteur(textFieldNom.getText(), textFieldPrenom.getText(),
							dateChooserDateNaiss.getDate(), textFieldEmail.getText(), passwordField.getText());
					emprunteur.setiD(-1);
					System.out.println("DATE DAO : " + dateChooserDateNaiss.getDate());
					if (preteurDAO.alreadyExist(preteur)) {
						labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					} else if (emprunteurDAO.alreadyExist(emprunteur)) {
						labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					} else {
						preteurDAO.create(preteur);

						emprunteurDAO.create(emprunteur);
						dispose();
						Connexion connexion = new Connexion(connect);
						connexion.setVisible(true);
						connexion.setResizable(false);
					}
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
				connexion.setResizable(false);
			}
		});
		btnRetour.setBounds(277, 341, 89, 23);
		contentPane.add(btnRetour);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(41, 375, 325, 23);
		contentPane.add(labelMsgErreur);
	}
}
