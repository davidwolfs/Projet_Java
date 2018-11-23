package gui.emprunteur;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import exo.Emprunteur;
import exo.Exemplaire;
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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import dao.EmprunteurDAO;
import dao.ExemplaireDAO;
import dao.JeuDAO;
import dao.PretDAO;
import dao.ReservationDAO;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Passer_Reservation extends JFrame {

	private JPanel contentPane;
	private JButton btnReservation;
	@SuppressWarnings("rawtypes")
	private JList list;
	private Connection connect;
	@SuppressWarnings("unused")
	private Emprunteur currentEmprunteur;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
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
			donnees[i] = listJeu.get(i).getNom() + " - " + listJeu.get(i).getConsole().getNom() + " - " + "Tarif : "
					+ listJeu.get(i).getTarif() + " - " + simpleDateFormat.format(listJeu.get(i).getDateTarif()) + " - "
					+ dispo;
		}

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
		lblMsgError.setBounds(135, 413, 345, 19);
		contentPane.add(lblMsgError);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 564, 214);
		contentPane.add(scrollPane);

		JList listJeux = new JList(donnees);
		scrollPane.setViewportView(listJeux);

		btnReservation = new JButton("R\u00E9server");
		btnReservation.addActionListener(new ActionListener() {
			public boolean champsVide() {
				String regex = "^(([0-9])|([0-2][0-9])|([3][0-1]))\\-(janv.|févr.|mars|avr.|mai|juin|juil.|août|sept.|oct.|nov.|déc.)\\-\\d{4}$";
				Pattern pattern = Pattern.compile(regex);
				Pattern pattern2 = Pattern.compile(regex);
				Matcher matcher = pattern
						.matcher(((JTextField) dateChooserDateDebut.getDateEditor().getUiComponent()).getText());

				Matcher matcher2 = pattern2
						.matcher(((JTextField) dateChooserDateFin.getDateEditor().getUiComponent()).getText());

				boolean valid = true;
				if (((JTextField) dateChooserDateDebut.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblMsgError.setText("Veuillez choisir une date de début.");
					valid = false;
				} else if (((JTextField) dateChooserDateFin.getDateEditor().getUiComponent()).getText().isEmpty()) {
					lblMsgError.setText("Veuillez choisir une date de fin.");
					valid = false;
				}

				else if (!(matcher.matches()) || !(matcher2.matches())) {
					lblMsgError.setText("Format de date attendu \"dd-MMM-yyyy\".");
					valid = false;
				} else {
					java.util.Date dateDebut = new java.util.Date();
					java.util.Date dateFin = new java.util.Date();
					dateDebut = dateChooserDateDebut.getDate();
					dateFin = dateChooserDateFin.getDate();

					dateDebut = new Timestamp(dateDebut.getTime());
					dateFin = new Timestamp(dateFin.getTime());
					System.out.println(dateDebut);
					System.out.println(dateFin);

					int res = dateDebut.compareTo(dateFin);

					if (res == 1) {
						lblMsgError.setText("La date de fin doit être ultérieure à la date de début.");
						valid = false;
					}
				}

				return valid;
			}

			public void actionPerformed(ActionEvent e) {
				int index = listJeux.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un jeu.");
				} else if (champsVide()) {
					int id = listJeu.get(index).getId();
					System.out.println("JEU SELECTIONNE : " + listJeu.get(index));
					System.out.println(id);
					dispose();
					Passer_Reservation passer_Reservation = new Passer_Reservation(connect, currentEmprunteur);
					passer_Reservation.setVisible(true);
					passer_Reservation.setResizable(false);
					ReservationDAO reservationDAO = new ReservationDAO(connect);
					Jeu jeu = new Jeu(listJeu.get(index).getId(), listJeu.get(index).getNom(),
							listJeu.get(index).isDispo(), listJeu.get(index).getTarif(),
							listJeu.get(index).getDateTarif(), listJeu.get(index).getConsole());
					java.util.Date date = new java.util.Date();
					Exemplaire exemplaire = new Exemplaire(jeu);
					ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
					exemplaire = exemplaireDAO.findExemplaireByIdJeu(jeu);
					if (exemplaireDAO.isLastExemplaire(listJeu.get(index))) {
						System.out.println("C EST LE LAST EXEMPLAIRE");

					} else {
						exemplaireDAO.update(exemplaire);
					}
					exemplaire.setJeu(jeu);
					System.out.println(exemplaire.getId());
					Reservation reservation = new Reservation(new Timestamp(date.getTime()), jeu);
					reservation.setId(-1);
					Pret pret = new Pret(dateChooserDateDebut.getDate(), dateChooserDateFin.getDate(),
							currentEmprunteur);
					System.out.println(pret);
					System.out.println("ID RESERVATION : " + reservation.getId());
					EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
					Emprunteur emprunteur = emprunteurDAO.findIdByEmprunteur(currentEmprunteur);
					reservationDAO.createReservation(reservation, emprunteur);
					int lastId = reservation.findLastIdReservation(connect);
					reservation.setId(lastId);
					reservationDAO.create_Ligne_Reservation(reservation, jeu);
					PretDAO pretDAO = new PretDAO(connect);
					pretDAO.create_Pret(pret, emprunteur, exemplaire);
				}
			}
		});
		btnReservation.setBounds(24, 409, 89, 23);
		contentPane.add(btnReservation);

		listJeux.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listJeux.getSelectedIndex();
				if (currentEmprunteur.getUnite() < listJeu.get(index).getTarif()) {
					lblMsgError.setText("Vous n'avez pas assez d'unité pour réserver ce jeu.");
					btnReservation.setEnabled(false);
				} else {
					lblMsgError.setText("");
					btnReservation.setEnabled(true);
				}
			}
		});

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
