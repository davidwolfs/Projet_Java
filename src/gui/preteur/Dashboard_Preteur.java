package gui.preteur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.JeuDAO;
import exo.Jeu;
import exo.Preteur;
import gui.Main;
import gui.administrateur.Ajouter_Jeu;
import gui.administrateur.Modifier_Jeu;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextPane;

public class Dashboard_Preteur extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;
	
	/**
	 * Create the frame.
	 */
	public Dashboard_Preteur(Connection connect, Preteur currentPreteur) {
		this.connect=connect;
		this.currentPreteur=currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + currentPreteur.getNom() + " " + currentPreteur.getPrenom() + ", vous êtes connecté en tant que : Preteur");
		lblBienvenue.setBounds(10, 25, 414, 20);
		contentPane.add(lblBienvenue);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 327, 118, 28);
		contentPane.add(btnDeconnexion);
		
		JLabel lblMsgErrorJeux = new JLabel("");
		lblMsgErrorJeux.setBounds(156, 334, 214, 21);
		contentPane.add(lblMsgErrorJeux);
		
		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeu = jeuDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] jeu = listJeu.toArray();

		Object[] donnees = new Object[listJeu.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listJeu.size(); i++) {
			String dispo = " ";
			if(listJeu.get(i).isDispo())
			{
				dispo = "Disponible";
			}
			else
			{
				dispo = "Indisponible";
			}
			
			System.out.println(listJeu.get(i).toString());
			donnees[i] = listJeu.get(i).getNom() + " - "
					+ dispo + " - "
					+ listJeu.get(i).getTarif() + " - " 
					+ simpleDateFormat.format(listJeu.get(i).getDateTarif()) + " - "
					+ listJeu.get(i).getAdapterTarif();
		}
		
		JList listJeux = new JList(donnees);
		listJeux.setBounds(10, 46, 564, 214);
		contentPane.add(listJeux);
		
		JButton btnAjouterExemplaire = new JButton("Ajouter un exemplaire");
		btnAjouterExemplaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listJeux.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorJeux.setText("Veuillez sélectionner un jeu.");
				} else {
					System.out.println(index);
					dispose();
					Ajouter_Exemplaire ajouter_Exemplaire = new Ajouter_Exemplaire(connect, currentPreteur, listJeu.get(index));
					ajouter_Exemplaire.setVisible(true);
					ajouter_Exemplaire.setResizable(false);
				}
				
			}
		});
		btnAjouterExemplaire.setBounds(392, 328, 175, 27);
		contentPane.add(btnAjouterExemplaire);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(478, 327, 6, 20);
		contentPane.add(textPane);
	}
}

