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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dao.EmprunteurDAO;
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
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Liste_Reservations extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;
	private ListSelectionModel listSelectionModel;
	
	/**
	 * Create the frame.
	 */
	public Liste_Reservations(Connection connect, Preteur currentPreteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 687);
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
				confirmer_pret = "En attente de confirmation";
			}
			
			donnees[i] = "Emprunteur : " + listPret.get(i).getEmprunteur().getNom() + " - " 
					+ listPret.get(i).getEmprunteur().getPrenom() + " - "
					+ listPret.get(i).getEmprunteur().getDateNaiss() + " - "
					+ listPret.get(i).getEmprunteur().getEmail() + " - "
					//+ listPret.get(i).getEmprunteur().getPassword() + " - " + " - "
					+ "Prêt : " + "du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin() + " - " + " - "
					+ "Jeu : " + listPret.get(i).getExemplaire().getJeu().getNom() + " - "
				//	+ dispo + " - "
				/*	+ listPret.get(i).getExemplaire().getJeu().getTarif() + " - " 
					+ simpleDateFormat.format(listPret.get(i).getExemplaire().getJeu().getDateTarif()) + " - "
					+ listPret.get(i).getExemplaire().getJeu().getAdapterTarif() + " - " */
					+ "Console : " + listPret.get(i).getExemplaire().getJeu().getConsole().getNom() + " - " + " - "
					+ "Statut : " + confirmer_pret;
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
		btnRetour.setBounds(869, 614, 89, 23);
		contentPane.add(btnRetour);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 950, 550);
		contentPane.add(scrollPane);
		
		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(313, 614, 241, 23);
		contentPane.add(lblMsgError);
		
		JList listPrets = new JList(donnees);
		listPrets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listPrets);
		
		JButton btnConfirmer = new JButton("Confirmer");
		
		listPrets.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listPrets.getSelectedIndex();
				if(pretDAO.isAlreadyConfirmed(listPret.get(index)))
				{
					lblMsgError.setText("Ce prêt a déjà été confirmé.");
					btnConfirmer.setEnabled(false);
				}
				else if(listPret.get(index).getEmprunteur().getUnite() < listPret.get(index).getExemplaire().getJeu().getTarif())
				{
					lblMsgError.setText("L'emprunteur n'a pas assez d'unité.");
					btnConfirmer.setEnabled(false);
				}
				else
				{
					lblMsgError.setText("");
					btnConfirmer.setEnabled(true);
				}
			}
		});
		
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PretDAO pretDAO = new PretDAO(connect);
				int index = listPrets.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un prêt.");
				} else {
					System.out.println(index);
					/*if(pretDAO.sameReservationFound())
					{
						System.out.println("Même réservation trouvée.");
						List<Pret> listPret = pretDAO.findAll();
						EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
						Emprunteur emprunteur = new Emprunteur();
						List<Emprunteur> listEmprunteur = emprunteurDAO.getEmprunteurSortByUnite();
						emprunteur.setiD(listEmprunteur.get(0).getiD());
						System.out.println(emprunteur.getiD());
						
						
					}
					else
					{*/
						dispose();
						listPret.get(index).setConfirmer_pret(true);
						Emprunteur emprunteurADebiter = listPret.get(index).getEmprunteur();
						EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
						Emprunteur emprunteurACrediter = new Emprunteur();
						emprunteurACrediter.setiD(currentPreteur.getiD());
						emprunteurACrediter = emprunteurDAO.findEmprunteurById(emprunteurACrediter);
						System.out.println(listPret.get(index));
						System.out.println("Unite de l'emprunteur : " + listPret.get(index).getEmprunteur().getUnite());
						System.out.println("Unite de prêteur: " + emprunteurACrediter.getUnite());
						emprunteurADebiter.soustraireUnite((int)listPret.get(index).getExemplaire().getJeu().getTarif());
						
						emprunteurACrediter.setiD(currentPreteur.getiD());
						emprunteurACrediter.ajouterUnite(((int)listPret.get(index).getExemplaire().getJeu().getTarif()));
						System.out.println("Unite de l'emprunteur : " + listPret.get(index).getEmprunteur().getUnite());
						System.out.println("Unite de prêteur: " + emprunteurACrediter.getUnite());
						JOptionPane.showMessageDialog(null, emprunteurADebiter.getUnite());
						JOptionPane.showMessageDialog(null, emprunteurACrediter.getUnite());
						emprunteurDAO.updateUnite(emprunteurADebiter);
						emprunteurDAO.updateUnite(emprunteurACrediter);
						pretDAO.update_Confirmation(listPret.get(index));
						System.out.println(listPret.get(index).isConfirmer_pret());
						Liste_Reservations liste_Reservations = new Liste_Reservations(connect, currentPreteur);
						liste_Reservations.setVisible(true);
						liste_Reservations.setResizable(false);
					//}
				}
			}
		});
		btnConfirmer.setBounds(10, 614, 99, 23);
		contentPane.add(btnConfirmer);
	
	}
}
