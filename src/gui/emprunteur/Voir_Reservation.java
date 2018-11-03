package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.JeuDAO;
import dao.ReservationDAO;
import exo.Emprunteur;
import exo.Jeu;
import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Voir_Reservation extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Emprunteur currentEmprunteur;
	
	/**
	 * Create the frame.
	 */
	public Voir_Reservation(Connection connect, Emprunteur currentEmprunteur) {
		this.connect=connect;
		this.currentEmprunteur=currentEmprunteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListeReservations = new JLabel("Liste de mes r\u00E9servations");
		lblListeReservations.setBounds(20, 24, 153, 14);
		contentPane.add(lblListeReservations);
		
		ReservationDAO reservationDAO = new ReservationDAO(connect);
		List<Reservation> listReservation = reservationDAO.findAll();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] reservation = listReservation.toArray();

		Object[] donnees = new Object[listReservation.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listReservation.size(); i++) {
			System.out.println(listReservation.get(i).toString());
			donnees[i] = "Réservation effectuée le : " + simpleDateFormat.format(listReservation.get(i).getDateReservation()) + " - " + listReservation.get(i).getJeu().getNom() + " - " + listReservation.get(i).getJeu().isDispo() + " - " + listReservation.get(i).getJeu().getTarif() + " - "
					+ simpleDateFormat.format(listReservation.get(i).getJeu().getDateTarif()) + " - " + listReservation.get(i).getJeu().getAdapterTarif()
					+ " - " + listReservation.get(i).getJeu().getConsole().getNom();
		}

		JList listReservations = new JList(donnees);
		listReservations.setBounds(10, 46, 564, 214);
		contentPane.add(listReservations);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Emprunteur dashboard_Emprunteur = new Dashboard_Emprunteur(connect, currentEmprunteur);
				dashboard_Emprunteur.setVisible(true);
				dashboard_Emprunteur.setResizable(false);
			}
		});
		btnRetour.setBounds(490, 409, 89, 23);
		contentPane.add(btnRetour);
	}
}
