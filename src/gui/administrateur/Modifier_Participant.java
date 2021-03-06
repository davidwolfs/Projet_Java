package gui.administrateur;

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

import exo.Administrateur;
import exo.Emprunteur;
import exo.Preteur;

import javax.swing.JPasswordField;

public class Modifier_Participant extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7808022342712250116L;
	@SuppressWarnings("unused")
	private Connection connect;
	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JDateChooser dateChooserDateNaiss;
	private JTextField textFieldEmail;
	private JButton btnRetour;
	private JLabel labelMsgErreur;
	private JPasswordField passwordField;
	@SuppressWarnings("unused")
	private Administrateur currentAdministrateur;
	@SuppressWarnings("unused")
	private Emprunteur emprunteurAModifier;
	@SuppressWarnings("unused")
	private Preteur preteurAModifier;
	private JLabel lblConfirmPassword;
	private JPasswordField confirmPasswordField;

	/**
	 * Create the frame.
	 */
	public Modifier_Participant(Connection connect, Administrateur currentAdministrateur,
			Emprunteur emprunteurAModifier, Preteur preteurAModifier) {
		setTitle("Projet Jeux Video");
		this.connect = connect;
		this.currentAdministrateur = currentAdministrateur;
		this.emprunteurAModifier = emprunteurAModifier;
		this.preteurAModifier = preteurAModifier;
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
		textFieldNom.setText(emprunteurAModifier.getNom());
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);

		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(233, 68, 162, 20);
		textFieldPrenom.setText(emprunteurAModifier.getPrenom());
		contentPane.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);

		dateChooserDateNaiss = new JDateChooser();
		dateChooserDateNaiss.setBounds(233, 115, 162, 20);
		dateChooserDateNaiss.setDate(emprunteurAModifier.getDateNaiss());
		contentPane.add(dateChooserDateNaiss);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(233, 160, 162, 20);
		textFieldEmail.setText(emprunteurAModifier.getEmail());
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(233, 209, 162, 20);
		passwordField.setText(emprunteurAModifier.getPassword());
		contentPane.add(passwordField);

		lblConfirmPassword = new JLabel("Confirmer password (*)");
		lblConfirmPassword.setBounds(24, 263, 137, 14);
		contentPane.add(lblConfirmPassword);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(233, 260, 162, 20);
		confirmPasswordField.setText(emprunteurAModifier.getPassword());
		contentPane.add(confirmPasswordField);

		JButton btnModifierAdministrateur = new JButton("Modifier");
		btnModifierAdministrateur.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public boolean champsVide() {
				String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
				String regex2 = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(janv.|f�vr.|mars|avr.|mai|juin|juil.|ao�t|sept.|oct.|nov.|d�c.)\\-\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Pattern pattern2 = Pattern.compile(regex2);
				Matcher matcher = pattern.matcher(textFieldEmail.getText());
				Matcher matcher2 = pattern2
						.matcher(((JTextField) dateChooserDateNaiss.getDateEditor().getUiComponent()).getText());
				
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
					labelMsgErreur.setText("Les mots de passes doivent �tre identiques.");
					valid = false;
				}

				return valid;
			}

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					Emprunteur emprunteur = new Emprunteur();
					Preteur preteur = new Preteur();
					emprunteurAModifier.setNom(textFieldNom.getText());
					emprunteurAModifier.setPrenom(textFieldPrenom.getText());
					emprunteurAModifier.setDateNaiss(dateChooserDateNaiss.getDate());
					emprunteurAModifier.setEmail(textFieldEmail.getText());
					emprunteurAModifier.setPassword(passwordField.getText());

					preteurAModifier.setNom(textFieldNom.getText());
					preteurAModifier.setPrenom(textFieldPrenom.getText());
					preteurAModifier.setDateNaiss(dateChooserDateNaiss.getDate());
					preteurAModifier.setEmail(textFieldEmail.getText());
					preteurAModifier.setPassword(passwordField.getText());
					if (emprunteurAModifier.alreadyExist(emprunteurAModifier, connect)) {
						labelMsgErreur.setText("Cet adresse e-mail existe d�j�.");

					} else {

						emprunteur.update(emprunteurAModifier, connect);
						preteur.update(preteurAModifier, connect);

						dispose();
						Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect,
								currentAdministrateur);
						gestion_Utilisateurs.setVisible(true);
						gestion_Utilisateurs.setResizable(false);
					}
				}
			}
		});
		btnModifierAdministrateur.setBounds(41, 341, 89, 23);
		contentPane.add(btnModifierAdministrateur);

		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect, currentAdministrateur);
				gestion_Utilisateurs.setVisible(true);
				gestion_Utilisateurs.setResizable(false);
			}
		});
		btnRetour.setBounds(277, 341, 89, 23);
		contentPane.add(btnRetour);

		labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(41, 375, 325, 23);
		contentPane.add(labelMsgErreur);
	}
}
