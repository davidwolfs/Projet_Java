package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Emprunteur;
import exo.Jeu;
import exo.Pret;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import dao.EmprunteurDAO;
import dao.JeuDAO;
import dao.PretDAO;
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
		this.connect = connect;
		this.currentEmprunteur = currentEmprunteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeJeux = new JLabel("Liste de jeux disponibles");
		lblListeJeux.setBounds(10, 26, 153, 19);
		contentPane.add(lblListeJeux);

		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeu = jeuDAO.findAllAvailable();

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] jeu = listJeu.toArray();

		Object[] donnees = new Object[listJeu.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listJeu.size(); i++) {
			String dispo = " ";
			if (listJeu.get(i).isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}

			System.out.println(listJeu.get(i).toString());
			donnees[i] = listJeu.get(i).getNom() + " - " + dispo + " - " + listJeu.get(i).getTarif() + " - "
					+ simpleDateFormat.format(listJeu.get(i).getDateTarif()) + " - " + listJeu.get(i).getAdapterTarif()
					+ " - " + listJeu.get(i).getConsole().getNom();
		}

		JList listJeux = new JList(donnees);
		listJeux.setBounds(10, 46, 564, 214);
		contentPane.add(listJeux);

		JLabel lblDateDebut = new JLabel("Date d\u00E9but");
		lblDateDebut.setBounds(24, 319, 139, 19);
		contentPane.add(lblDateDebut);

		JDateChooser dateChooserDateDebut = new JDateChooser();
		dateChooserDateDebut.setBounds(373, 319, 171, 19);
		contentPane.add(dateChooserDateDebut);

		JLabel lblDateFin = new JLabel("Date fin");
		lblDateFin.setBounds(24, 360, 139, 19);
		contentPane.add(lblDateFin);

		JDateChooser dateChooserDateFin = new JDateChooser();
		dateChooserDateFin.setBounds(373, 360, 171, 19);
		contentPane.add(dateChooserDateFin);

		list = new JList();
		list.setBounds(47, 163, 335, -62);
		contentPane.add(list);

		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(198, 413, 282, 19);
		contentPane.add(lblMsgError);

		btnReservation = new JButton("R\u00E9server");
		btnReservation.addActionListener(new ActionListener() {
			public boolean champsVide() {
				String regex = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(janv.|févr.|mars|avr.|mai|juin|juil.|août|sept.|oct.|nov.|déc.)\\-\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern
						.matcher(((JTextField) dateChooserDateDebut.getDateEditor().getUiComponent()).getText());

				boolean valid = true;
				if (((JTextField) dateChooserDateDebut.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblMsgError.setText("Veuillez choisir une date de début.");
					valid = false;
				} else if (((JTextField) dateChooserDateFin.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblMsgError.setText("Veuillez choisir une date de fin.");
					valid = false;
				}

				else if (!(matcher.matches())) {
					lblMsgError.setText("Format de date attendu \"dd-MMM-yyyy\".");
					valid = false;
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un jeu.");
				} else if (champsVide()) {
					// if (input == 0) {
					int id = listJeu.get(index).getId();
					System.out.println(id);
					dispose();
					Passer_Reservation passer_Reservation = new Passer_Reservation(connect, currentEmprunteur);
					passer_Reservation.setVisible(true);
					passer_Reservation.setResizable(false);
					// }
					ReservationDAO reservationDAO = new ReservationDAO(connect);
					Jeu jeu = new Jeu(listJeu.get(index).getId(), listJeu.get(index).getNom(),
							listJeu.get(index).isDispo(), listJeu.get(index).getTarif(),
							listJeu.get(index).getDateTarif(), listJeu.get(index).getAdapterTarif(),
							listJeu.get(index).getConsole());
					java.util.Date date = new java.util.Date();
					Reservation reservation = new Reservation(new Timestamp(date.getTime()), jeu);
					reservation.setId(-1);
					int lastId = reservationDAO.findLastIdReservation();
					reservation.setId(lastId);
					Pret pret = new Pret(dateChooserDateDebut.getDate(), dateChooserDateFin.getDate(), currentEmprunteur);
					System.out.println(pret);
					System.out.println(reservation);
					EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
					Emprunteur emprunteur = emprunteurDAO.findIdByEmprunteur(currentEmprunteur);
					reservationDAO.createReservation(reservation, emprunteur);
					reservationDAO.create_Ligne_Reservation(reservation, jeu);
					PretDAO pretDAO = new PretDAO(connect);
					pretDAO.create_Pret(pret, emprunteur);
					// }
				}
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
