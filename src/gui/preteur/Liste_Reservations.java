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
import exo.Jeu;
import exo.Pret;
import exo.Preteur;
import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JList;

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
		
		PretDAO pretDAO = new PretDAO(connect);
		List<Pret> listPret = pretDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] pret = listPret.toArray();

		Object[] donnees = new Object[listPret.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listPret.size(); i++) {
			String dispo = " ";
			if(listPret.get(i).getExemplaire().getJeu().isDispo())
			{
				dispo = "Disponible";
			}
			else
			{
				dispo = "Indisponible";
			}
			
			donnees[i] = listPret.get(i).getEmprunteur().getNom() + " - " 
					+ listPret.get(i).getEmprunteur().getPrenom() + " - "
					+ listPret.get(i).getEmprunteur().getDateNaiss() + " - "
					+ listPret.get(i).getEmprunteur().getEmail() + " - "
					+ listPret.get(i).getEmprunteur().getPassword() + " - " + " - "
					+ "Du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin() + " - " + " - "
					+ listPret.get(i).getExemplaire().getJeu().getNom() +  " - "
					+ dispo + " - "
					+ listPret.get(i).getExemplaire().getJeu().getTarif() + " - " 
					+ simpleDateFormat.format(listPret.get(i).getExemplaire().getJeu().getDateTarif()) + " - "
					+ listPret.get(i).getExemplaire().getJeu().getAdapterTarif() + " - " 
					+ listPret.get(i).getExemplaire().getJeu().getConsole().getNom();
		}
		
		JList listPrets = new JList(donnees);
		listPrets.setBounds(10, 46, 751, 214);
		contentPane.add(listPrets);
		
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
		
		JButton btnConfirmer = new JButton("Confirmer");
		btnConfirmer.setBounds(10, 392, 99, 23);
		contentPane.add(btnConfirmer);
	}
}
