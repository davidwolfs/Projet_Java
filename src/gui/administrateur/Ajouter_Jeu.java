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

import dao.JeuDAO;
import exo.Administrateur;
import exo.Jeu;

import java.awt.event.ActionListener;
import java.sql.Connection;
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
		this.connect=connect;
		setTitle("Ajouter un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom du jeu");
		lblNom.setBounds(42, 45, 64, 14);
		contentPane.add(lblNom);
		
		JLabel lblDisponibilite = new JLabel("Disponibilite");
		lblDisponibilite.setBounds(42, 82, 64, 14);
		contentPane.add(lblDisponibilite);
		
		JLabel lblTarif = new JLabel("Tarif");
		lblTarif.setBounds(42, 117, 46, 14);
		contentPane.add(lblTarif);
		
		JLabel lblDateTarif = new JLabel("DateTarif");
		lblDateTarif.setBounds(42, 160, 46, 14);
		contentPane.add(lblDateTarif);
		
		JLabel lblAdapterTarif = new JLabel("AdapterTarif");
		lblAdapterTarif.setBounds(42, 199, 64, 14);
		contentPane.add(lblAdapterTarif);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(207, 42, 100, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(208, 78, 100, 20);
		contentPane.add(chckbxDisponibilite);
		
		textFieldTarif = new JTextField();
		textFieldTarif.setBounds(207, 114, 100, 20);
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);
		
		JDateChooser dateChooserDateTarif = new JDateChooser();
		dateChooserDateTarif.setBounds(207, 160, 100, 20);
		contentPane.add(dateChooserDateTarif);
		
		textFieldAdapterTarif = new JTextField();
		textFieldAdapterTarif.setBounds(207, 196, 100, 20);
		contentPane.add(textFieldAdapterTarif);
		textFieldAdapterTarif.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JeuDAO jeuDAO = new JeuDAO(connect);
				Jeu jeu = new Jeu(textFieldNom.getText(), chckbxDisponibilite.isSelected(), Double.parseDouble(textFieldTarif.getText()), dateChooserDateTarif.getDate(), textFieldAdapterTarif.getText());
				System.out.println(jeu.getNom() + " " + jeu.isDispo() + " " +  jeu.getTarif() + " " +  jeu.getDateTarif() + " " +  jeu.getAdapterTarif());
				jeuDAO.create(jeu);
			}
		});
		btnAjouter.setBounds(42, 259, 89, 23);
		contentPane.add(btnAjouter);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gestion_Jeux gestion_Consoles = new Gestion_Jeux(connect, currentAdministrateur);
				gestion_Consoles.setVisible(true);
				gestion_Consoles.setResizable(false);
				dispose();
			}
		});
		btnRetour.setBounds(207, 259, 100, 23);
		contentPane.add(btnRetour);
	}
}
