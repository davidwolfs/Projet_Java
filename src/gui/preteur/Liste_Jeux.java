package gui.preteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import dao.JeuDAO;
import exo.Jeu;
import exo.Preteur;

public class Liste_Jeux extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;

	/**
	 * Create the frame.
	 */
	public Liste_Jeux(Connection connect, Preteur currentPreteur) {
		this.connect=connect;
		this.currentPreteur=currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 601, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMsgErrorJeux = new JLabel("");
		lblMsgErrorJeux.setBounds(196, 392, 214, 21);
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
					+ listJeu.get(i).getAdapterTarif() + " - " 
					+ listJeu.get(i).getConsole().getNom();
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
		btnAjouterExemplaire.setBounds(10, 386, 175, 27);
		contentPane.add(btnAjouterExemplaire);
		
		JLabel lblJeux = new JLabel("Liste des jeux");
		lblJeux.setBounds(10, 23, 120, 14);
		contentPane.add(lblJeux);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(connect, currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
		btnRetour.setBounds(466, 392, 89, 23);
		contentPane.add(btnRetour);
	}
}
