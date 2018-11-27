package gui.preteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import exo.Emprunteur;
import exo.Exemplaire;
import exo.Jeu;
import exo.Pret;
import exo.Preteur;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Liste_Reservations extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3604722132557838656L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Preteur currentPreteur;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Liste_Reservations(Connection connect, Preteur currentPreteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 687);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListePrets = new JLabel("Liste des pr\u00EAts");
		lblListePrets.setBounds(10, 11, 124, 14);
		contentPane.add(lblListePrets);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setiD(currentPreteur.getiD());

		Pret p = new Pret();
		List<Pret> listPret = p.findAll(emprunteur, connect);

		Object[] donnees = new Object[listPret.size()];

		for (int i = 0; i < listPret.size(); i++) {
			String confirmer_pret = " ";
			if (listPret.get(i).isConfirmer_pret()) {
				confirmer_pret = "Confirmé";
			} else {
				confirmer_pret = "En attente de confirmation";
			}

			donnees[i] = "Emprunteur : " + listPret.get(i).getEmprunteur().getNom() + " - "
					+ listPret.get(i).getEmprunteur().getPrenom() + " - "
					+ listPret.get(i).getEmprunteur().getDateNaiss() + " - "
					+ listPret.get(i).getEmprunteur().getEmail() + " - " + " - " + "Date de réservation : "
					+ listPret.get(i).getEmprunteur().getReservation().getDateReservation() + " - " + " - " + "Prêt : "
					+ "du " + listPret.get(i).getDateDebut() + " au " + listPret.get(i).getDateFin() + " - " + " - "
					+ "Jeu : " + listPret.get(i).getExemplaire().getJeu().getNom() + " - " + "Console : "
					+ listPret.get(i).getExemplaire().getJeu().getConsole().getNom() + " - " + " - " + "Statut : "
					+ confirmer_pret;
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
		btnRetour.setBounds(1071, 614, 89, 23);
		contentPane.add(btnRetour);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 1150, 550);
		contentPane.add(scrollPane);

		JLabel lblMsgError = new JLabel("");
		lblMsgError.setBounds(313, 614, 241, 23);
		contentPane.add(lblMsgError);

		JList listPrets = new JList(donnees);
		listPrets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listPrets);

		JButton btnConfirmer = new JButton("Confirmer");

		listPrets.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listPrets.getSelectedIndex();
				if (p.isAlreadyConfirmed(listPret.get(index), connect)) {
					lblMsgError.setText("Ce prêt a déjà été confirmé.");
					btnConfirmer.setEnabled(false);
				} else if (listPret.get(index).getEmprunteur().getUnite() < listPret.get(index).getExemplaire().getJeu()
						.getTarif()) {
					lblMsgError.setText("L'emprunteur n'a pas assez d'unité.");
					btnConfirmer.setEnabled(false);
				} else {
					lblMsgError.setText("");
					btnConfirmer.setEnabled(true);
				}
			}
		});

		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listPrets.getSelectedIndex();
				if (index == -1) {
					lblMsgError.setText("Veuillez sélectionner un prêt.");
				} else {
					if (p.sameReservationFound(listPret.get(index).getExemplaire(), connect)) {
						List<Pret> listPret = p.findAll(emprunteur, connect);
						Emprunteur emprunteur = new Emprunteur();
						List<Pret> listPretEmprunteur = p
								.getPretEmprunteurSortByPriorites(listPret.get(index).getExemplaire(), connect);

						Pret pret = new Pret();
						pret.setId(listPretEmprunteur.get(0).getId());
						emprunteur.setiD(listPretEmprunteur.get(0).getEmprunteur().getiD());
						pret.update_Pret_Emprunteur(emprunteur, pret, connect);

						for (int i = 1; i < listPretEmprunteur.size(); i++) {
							pret.delete_Pret_Emprunteur(listPretEmprunteur.get(i), connect);
						}

						dispose();
						listPretEmprunteur.get(0).setConfirmer_pret(true);
						Emprunteur emprunteurADebiter = listPretEmprunteur.get(0).getEmprunteur();
						Emprunteur emprunteurACrediter = new Emprunteur();
						emprunteurACrediter.setiD(currentPreteur.getiD());
						emprunteurACrediter = emprunteur.findEmprunteurById(emprunteurACrediter, connect);
						emprunteurADebiter
								.soustraireUnite((int) listPretEmprunteur.get(0).getExemplaire().getJeu().getTarif());

						emprunteurACrediter.setiD(currentPreteur.getiD());
						emprunteurACrediter
								.ajouterUnite(((int) listPretEmprunteur.get(0).getExemplaire().getJeu().getTarif()));
						emprunteur.updateUnite(emprunteurADebiter, connect);
						emprunteur.updateUnite(emprunteurACrediter, connect);
						pret.update_Confirmation(listPretEmprunteur.get(0), connect);
						pret.update_Pret_Preteur(currentPreteur, listPretEmprunteur.get(0), connect);
						listPretEmprunteur.get(0).getExemplaire().getJeu().setDispo(false);
						Exemplaire exemplaire = new Exemplaire();
						exemplaire.update(listPretEmprunteur.get(0).getExemplaire(), connect);
						Jeu jeu = new Jeu();
						jeu.update_Dispo_False(listPretEmprunteur.get(0).getExemplaire().getJeu(), connect);
						Liste_Reservations liste_Reservations = new Liste_Reservations(connect, currentPreteur);
						liste_Reservations.setVisible(true);
						liste_Reservations.setResizable(false);
					} else {
						dispose();
						listPret.get(index).setConfirmer_pret(true);
						Emprunteur emprunteurADebiter = listPret.get(index).getEmprunteur();
						Emprunteur e = new Emprunteur();
						Emprunteur emprunteurACrediter = new Emprunteur();
						emprunteurACrediter.setiD(currentPreteur.getiD());
						emprunteurACrediter = e.findEmprunteurById(emprunteurACrediter, connect);
						emprunteurADebiter
								.soustraireUnite((int) listPret.get(index).getExemplaire().getJeu().getTarif());

						emprunteurACrediter.setiD(currentPreteur.getiD());
						emprunteurACrediter
								.ajouterUnite(((int) listPret.get(index).getExemplaire().getJeu().getTarif()));
						e.updateUnite(emprunteurADebiter, connect);
						e.updateUnite(emprunteurACrediter, connect);
						p.update_Confirmation(listPret.get(index), connect);
						p.update_Pret_Preteur(currentPreteur, listPret.get(index), connect);
						Liste_Reservations liste_Reservations = new Liste_Reservations(connect, currentPreteur);
						liste_Reservations.setVisible(true);
						liste_Reservations.setResizable(false);
					}
				}
			}
		});
		btnConfirmer.setBounds(10, 614, 99, 23);
		contentPane.add(btnConfirmer);

	}
}
