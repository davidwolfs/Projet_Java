package gui.preteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import dao.AdministrateurDAO;
import dao.ConsoleDAO;
import dao.EmprunteurDAO;
import dao.ExemplaireDAO;
import dao.JeuDAO;
import dao.PreteurDAO;
import exo.Administrateur;
import exo.Console;
import exo.Emprunteur;
import exo.Exemplaire;
import exo.Jeu;
import exo.Preteur;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Ajouter_Exemplaire extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNom;
	private JTextField textFieldTarif;
	private JTextField textFieldAdapterTarif;
	private Connection connect;
	private Preteur currentPreteur;
	private Jeu jeuAModifier;
	/**
	 * Create the frame.
	 */
	public Ajouter_Exemplaire(Connection connect, Preteur currentPreteur, Jeu jeuAModifier) {
		this.connect=connect;
		this.currentPreteur=currentPreteur;
		this.jeuAModifier=jeuAModifier;
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
		
		JLabel lblAdapterTarif = new JLabel("AdapterTarif");
		lblAdapterTarif.setBounds(69, 212, 89, 14);
		contentPane.add(lblAdapterTarif);
		
		JLabel lblNom2 = new JLabel("Nom du jeu");
		lblNom2.setBounds(251, 45, 131, 20);
		lblNom2.setText(jeuAModifier.getNom());
		contentPane.add(lblNom2);
		
		/*textFieldNom = new JTextField();
		textFieldNom.setBounds(251, 45, 131, 20);
		textFieldNom.setText(jeuAModifier.getNom());
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);*/
		
		JCheckBox chckbxDisponibilite = new JCheckBox("Disponible");
		chckbxDisponibilite.setBounds(251, 88, 130, 20);
		chckbxDisponibilite.setSelected(true);
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
		
		JLabel lblAdapterTarif2 = new JLabel("AdapterTarif");
		lblAdapterTarif2.setBounds(251, 209, 131, 20);
		lblAdapterTarif2.setText(jeuAModifier.getAdapterTarif());
		contentPane.add(lblAdapterTarif2);
		
		/*textFieldTarif = new JTextField();
		textFieldTarif.setBounds(251, 126, 131, 20);
		textFieldTarif.setText(String.valueOf(jeuAModifier.getTarif()));
		contentPane.add(textFieldTarif);
		textFieldTarif.setColumns(10);*/
		
	
		
		/*textFieldAdapterTarif = new JTextField();
		textFieldAdapterTarif.setBounds(251, 209, 131, 20);
		textFieldAdapterTarif.setText(jeuAModifier.getAdapterTarif());
		contentPane.add(textFieldAdapterTarif);
		textFieldAdapterTarif.setColumns(10);*/
		
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
		listConsoles.setBounds(251, 256, 131, 199);
		listConsoles.setEnabled(false);
		
		int index = -1;
		for(int i = 0; i < listConsole.size();i++)
		{
			if(jeuAModifier.getConsole().getId() == listConsole.get(i).getId())
			{
				index = i;
			}
		}
		System.out.println("Index : " + index);
		listConsoles.setSelectedIndex(index);
		contentPane.add(listConsoles);
		
		JLabel lblNombreExemplaires = new JLabel("Nombre d'exemplaires");
		lblNombreExemplaires.setBounds(69, 499, 146, 20);
		contentPane.add(lblNombreExemplaires);
		
		JSpinner spinnerNombreExemplaires = new JSpinner();
		spinnerNombreExemplaires.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinnerNombreExemplaires.setBounds(251, 499, 40, 20);
		contentPane.add(spinnerNombreExemplaires);
		
		
		JLabel labelMsgErreur = new JLabel("");
		labelMsgErreur.setBounds(69, 532, 313, 29);
		contentPane.add(labelMsgErreur);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			
			public boolean champsVide() {
				boolean valid = true;
				
				if((int)spinnerNombreExemplaires.getValue() <= 0)
				{
					labelMsgErreur.setText("Veuillez entrez un nombre sup�rieur � 0.");
					valid = false;
				}

				return valid;
			}
			
			public void actionPerformed(ActionEvent e) {
				if (champsVide()) {
					System.out.println(spinnerNombreExemplaires.getValue());
					JeuDAO jeuDAO = new JeuDAO(connect);
					ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
					jeuAModifier.setNom(lblNom2.getText());
					jeuAModifier.setDispo((chckbxDisponibilite.isSelected()));
					jeuAModifier.setTarif((Double.parseDouble(lblTarif2.getText())));
					jeuAModifier.setDateTarif((dateChooserDateTarif.getDate()));
					jeuAModifier.setAdapterTarif((lblAdapterTarif2.getText()));
					jeuAModifier.setConsole(listConsole.get(listConsoles.getSelectedIndex()));
					
					int nombreExemplaires = (int)spinnerNombreExemplaires.getValue();
					
					Exemplaire exemplaire = null;
					for(int i=0;i<nombreExemplaires;i++)
					{
						exemplaire= new Exemplaire(jeuAModifier);
						currentPreteur.addExemplaire(exemplaire);
						exemplaireDAO.create_Exemplaire(exemplaire, currentPreteur);
					}
					
					
					if (jeuDAO.alreadyExist(jeuAModifier)) {
						labelMsgErreur.setText("Ce jeu existe d�j� pour cette console.");
					
					} else {
						System.out.println("Nombre d'exemplaires : " + currentPreteur.getListExemplaire().size());
						System.out.println(currentPreteur.getListExemplaire());
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
