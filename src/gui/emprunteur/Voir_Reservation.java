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
import dao.PretDAO;
import dao.ReservationDAO;
import exo.Emprunteur;
import exo.Jeu;
import exo.Pret;
import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

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
		setBounds(100, 100, 987, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListeReservations = new JLabel("Liste de mes r\u00E9servations");
		lblListeReservations.setBounds(10, 24, 153, 14);
		contentPane.add(lblListeReservations);
		
		PretDAO pretDAO = new PretDAO(connect);
		List<Pret> listPret = pretDAO.findAllPretByEmprunteur(currentEmprunteur);

		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] reservation = listPret.toArray();

		Object[] donnees = new Object[listPret.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listPret.size(); i++) {
			
			String confirmer_pret = " ";
			if(listPret.get(i).isConfirmer_pret())
			{
				confirmer_pret = "Confirmé par " + listPret.get(i).getPreteur().getNom() + " " +  listPret.get(i).getPreteur().getPrenom();
			}
			else
			{
				confirmer_pret = "En attente de confirmation par le prêteur";
			}
			
			System.out.println(listPret.get(i).toString());
			donnees[i] = /*"Date de réservation : " + simpleDateFormat.format(listPret.get(i).getEmprunteur().getReservation().getDateReservation()) + " - " + " - " + */"Jeu : " +  listPret.get(i).getExemplaire().getJeu().getNom() /*listReservation.get(i).getJeu().isDispo() + " - " + listReservation.get(i).getJeu().getTarif() */
					/*+ " - " + listReservation.get(i).getJeu().getAdapterTarif();*/
					+ " - " + "Console : " + listPret.get(i).getExemplaire().getJeu().getConsole().getNom()
					+ " - " + " - " + "Réservation : " +  "du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin()
					+ " - " + " - " + "État : " + confirmer_pret;
					
		}
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Emprunteur dashboard_Emprunteur = new Dashboard_Emprunteur(connect, currentEmprunteur);
				dashboard_Emprunteur.setVisible(true);
				dashboard_Emprunteur.setResizable(false);
			}
		});
		btnRetour.setBounds(872, 460, 89, 23);
		contentPane.add(btnRetour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 950, 400);
		contentPane.add(scrollPane);
		
				JList listReservations = new JList(donnees);
				scrollPane.setViewportView(listReservations);
	}
}
