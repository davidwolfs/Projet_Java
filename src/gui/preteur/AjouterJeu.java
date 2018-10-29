package gui.preteur;

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

import exo.Jeu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AjouterJeu extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldTarif;
	private JTextField textFieldAdapterTarif;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterJeu frame = new AjouterJeu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AjouterJeu() {
		setTitle("Ajouter un jeu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		lblTarif.setBounds(42, 119, 46, 14);
		contentPane.add(lblTarif);
		
		JLabel lblDateTarif = new JLabel("DateTarif");
		lblDateTarif.setBounds(42, 154, 46, 14);
		contentPane.add(lblDateTarif);
		
		JLabel lblAdapterTarif = new JLabel("AdapterTarif");
		lblAdapterTarif.setBounds(42, 185, 64, 14);
		contentPane.add(lblAdapterTarif);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(207, 42, 86, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(208, 78, 97, 23);
		contentPane.add(chckbxDisponibilite);
		
		textFieldTarif = new JTextField();
		textFieldTarif.setBounds(207, 116, 86, 20);
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);
		
		JDateChooser dateChooserDateTarif = new JDateChooser();
		dateChooserDateTarif.setBounds(207, 154, 95, 20);
		contentPane.add(dateChooserDateTarif);
		
		textFieldAdapterTarif = new JTextField();
		textFieldAdapterTarif.setBounds(207, 185, 86, 20);
		contentPane.add(textFieldAdapterTarif);
		textFieldAdapterTarif.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Jeu jeu = new Jeu(textFieldNom.getText(), chckbxDisponibilite.isSelected(), Double.parseDouble(textFieldTarif.getText()), dateChooserDateTarif.getDate(), textFieldAdapterTarif.getText());
				System.out.println(jeu.getNom() + " " + jeu.isDispo() + " " +  jeu.getTarif() + " " +  jeu.getDateTarif() + " " +  jeu.getAdapterTarif());
				jeu.create(jeu);
			}
		});
		btnAjouter.setBounds(38, 227, 89, 23);
		contentPane.add(btnAjouter);
	}
}
