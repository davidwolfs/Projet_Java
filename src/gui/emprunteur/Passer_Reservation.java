package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Emprunteur;
import exo.Jeu;
import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import dao.EmprunteurDAO;
import dao.JeuDAO;
import dao.ReservationDAO;

import javax.swing.JList;
import javax.swing.JOptionPane;

public class Passer_Reservation extends JFrame {

	private JPanel contentPane;
	private JButton btnReservation;
	private JList list;
	private Connection connect;
	private Emprunteur currentEmprunteur;
	/**
	 * Create the frame.
	 */
	public Passer_Reservation(Connection connect, Emprunteur currentEmprunteur) {
		this.connect=connect;
		this.currentEmprunteur=currentEmprunteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeJeux = new JLabel("Liste de jeux");
		lblListeJeux.setBounds(34, 26, 129, 19);
		contentPane.add(lblListeJeux);
		
		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeu = jeuDAO.findAllAvailable();

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
		
		JLabel lblDateReservation = new JLabel("Date r\u00E9servation");
		lblDateReservation.setBounds(24, 319, 139, 19);
		contentPane.add(lblDateReservation);

		JDateChooser dateChooserDateReservation = new JDateChooser();
		dateChooserDateReservation.setBounds(373, 319, 171, 19);
		contentPane.add(dateChooserDateReservation);

		list = new JList();
		list.setBounds(47, 163, 335, -62);
		contentPane.add(list);
		
		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(198, 413, 282, 19);
		contentPane.add(lblMsgError);
		
		btnReservation = new JButton("R\u00E9server");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un jeu.");
				} 
					//if (input == 0) {
						int id = listJeu.get(index).getId();
						System.out.println(id);
						jeuDAO.delete(listJeu.get(index));

						dispose();
						Passer_Reservation passer_Reservation = new Passer_Reservation(connect,
								currentEmprunteur);
						passer_Reservation.setVisible(true);
						passer_Reservation.setResizable(false);
					//}
					ReservationDAO reservationDAO = new ReservationDAO(connect);
					Jeu jeu = new Jeu("GTA V", true, 50, dateChooserDateReservation.getDate(), "Tarif");
					Reservation reservation = new Reservation(dateChooserDateReservation.getDate(), jeu);
					System.out.println(reservation);
					EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
					Emprunteur emprunteur = emprunteurDAO.findIdByEmprunteur(currentEmprunteur);
					reservationDAO.createReservation(reservation, emprunteur);
				}
		});
		btnReservation.setBounds(24, 409, 89, 23);
		contentPane.add(btnReservation);
		
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
