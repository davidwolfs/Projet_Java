package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import com.toedter.calendar.JDateChooser;

import dao.AdministrateurDAO;
import dao.EmprunteurDAO;
import dao.JeuDAO;
import dao.PreteurDAO;
import exo.Administrateur;
import exo.Emprunteur;
import exo.Jeu;
import exo.Preteur;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Modifier_Jeu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldTarif;
	private JTextField textFieldAdapterTarif;
	private Connection connect;
	private Jeu jeuAModifier;
	/**
	 * Create the frame.
	 */
	public Modifier_Jeu(Connection connect, Administrateur currentAdministrateur, Jeu jeuAModifier) {
		this.connect=connect;
		this.jeuAModifier=jeuAModifier;
		setTitle("Modifier un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 439);
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
		textFieldNom.setBounds(251, 42, 131, 20);
		textFieldNom.setText(jeuAModifier.getNom());
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(252, 78, 130, 20);
		chckbxDisponibilite.setSelected(jeuAModifier.isDispo());
		contentPane.add(chckbxDisponibilite);
		
		textFieldTarif = new JTextField();
		textFieldTarif.setBounds(251, 114, 131, 20);
		textFieldTarif.setText(jeuAModifier.getNom());
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);
		
		JDateChooser dateChooserDateTarif = new JDateChooser();
		dateChooserDateTarif.setBounds(251, 160, 131, 20);
		dateChooserDateTarif.setDate(jeuAModifier.getDateTarif());
		contentPane.add(dateChooserDateTarif);
		
		textFieldAdapterTarif = new JTextField();
		textFieldAdapterTarif.setBounds(251, 209, 131, 20);
		textFieldAdapterTarif.setText(jeuAModifier.getAdapterTarif());
		contentPane.add(textFieldAdapterTarif);
		textFieldAdapterTarif.setColumns(10);
		
		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(69, 360, 313, 29);
		contentPane.add(labelMsgErreur);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			
			public boolean champsVide() {
				boolean valid = true;
				if (textFieldNom.getText().isEmpty() || chckbxDisponibilite.getText().isEmpty()
						|| textFieldTarif.getText().isEmpty() || ((JTextField) dateChooserDateTarif.getDateEditor().getUiComponent()).getText().isEmpty()
						|| textFieldAdapterTarif.getText().isEmpty()) {
					labelMsgErreur.setText("Veuillez remplir tous les champs.");
					valid = false;
				}

				return valid;
			}
			
			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					JeuDAO jeuDAO = new JeuDAO(connect);
					/*if (jeuDAO.alreadyExist(textFieldEmail.getText())) {
						labelMsgErreur.setText("Cet adresse e-mail existe déjà.");
					
					} else {*/
						jeuAModifier.setNom(textFieldNom.getText());
						jeuAModifier.setDispo((chckbxDisponibilite.isSelected()));
						jeuAModifier.setTarif((Double.parseDouble(textFieldTarif.getText())));
						jeuAModifier.setDateTarif((dateChooserDateTarif.getDate()));
						jeuAModifier.setAdapterTarif((textFieldAdapterTarif.getText()));
						
						jeuDAO.update(jeuAModifier);

						dispose();
						Gestion_Jeux gestion_Jeux = new Gestion_Jeux(connect, currentAdministrateur);
						gestion_Jeux.setVisible(true);
						gestion_Jeux.setResizable(false);
					}
				//}
				
			}
		});
		btnModifier.setBounds(69, 329, 89, 23);
		contentPane.add(btnModifier);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Jeux gestion_Consoles = new Gestion_Jeux(connect, currentAdministrateur);
				gestion_Consoles.setVisible(true);
				gestion_Consoles.setResizable(false);
				dispose();
			}
		});
		btnRetour.setBounds(264, 329, 100, 23);
		contentPane.add(btnRetour);
	}
}
