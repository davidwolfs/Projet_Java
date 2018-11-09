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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.JeuDAO;
import dao.PretDAO;
import dao.ReservationDAO;
import exo.Emprunteur;
import exo.Jeu;
import exo.Pret;
import exo.Preteur;
import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class Liste_Reservations extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;
	
	/**
	 * Create the frame.
	 */
	public Liste_Reservations(Connection connect, Preteur currentPreteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListePrets = new JLabel("Liste des pr\u00EAts");
		lblListePrets.setBounds(10, 11, 124, 14);
		contentPane.add(lblListePrets);
		
		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setiD(currentPreteur.getiD());
		
		PretDAO pretDAO = new PretDAO(connect);
		List<Pret> listPret = pretDAO.findAll(emprunteur);

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] pret = listPret.toArray();

		Object[] donnees = new Object[listPret.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listPret.size(); i++) {
			String confirmer_pret = " ";
			if(listPret.get(i).isConfirmer_pret())
			{
				confirmer_pret = "Confirmé";
			}
			else
			{
				confirmer_pret = "Non confirmé";
			}
			
			donnees[i] = listPret.get(i).getEmprunteur().getNom() + " - " 
					+ listPret.get(i).getEmprunteur().getPrenom() + " - "
					+ listPret.get(i).getEmprunteur().getDateNaiss() + " - "
					+ listPret.get(i).getEmprunteur().getEmail() + " - "
					//+ listPret.get(i).getEmprunteur().getPassword() + " - " + " - "
					+ "Du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin() + " - " + " - "
					+ confirmer_pret;
				//	+ listPret.get(i).getExemplaire().getJeu().getNom() +  " - "
				//	+ dispo + " - "
				/*	+ listPret.get(i).getExemplaire().getJeu().getTarif() + " - " 
					+ simpleDateFormat.format(listPret.get(i).getExemplaire().getJeu().getDateTarif()) + " - "
					+ listPret.get(i).getExemplaire().getJeu().getAdapterTarif() + " - " 
					+ listPret.get(i).getExemplaire().getJeu().getConsole().getNom();*/
		}
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(connect, currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
		btnRetour.setBounds(672, 392, 89, 23);
		contentPane.add(btnRetour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 751, 214);
		contentPane.add(scrollPane);
		
		JList listPrets = new JList(donnees);
		scrollPane.setViewportView(listPrets);
		
		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(263, 392, 241, 23);
		contentPane.add(lblMsgError);
		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listPrets.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un prêt.");
				} else {
					System.out.println(index);
					dispose();
					listPret.get(index).setConfirmer_pret(true);
					System.out.println(listPret.get(index));
					PretDAO pretDAO = new PretDAO(connect);
					pretDAO.update_Confirmation(listPret.get(index));
					System.out.println(listPret.get(index).isConfirmer_pret());
					Liste_Reservations liste_Reservations = new Liste_Reservations(connect, currentPreteur);
					liste_Reservations.setVisible(true);
					liste_Reservations.setResizable(false);
				}
			}
		});
		btnConfirmer.setBounds(10, 392, 99, 23);
		contentPane.add(btnConfirmer);
	
	}
}
