package gui.preteur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Exemplaire;
import exo.Preteur;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Liste_Jeux_A_Preter extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43520900108954949L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Preteur currentPreteur;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Liste_Jeux_A_Preter(Connection connect, Preteur currentPreteur) {
		this.connect = connect;
		this.currentPreteur = currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblJeuxAPreter = new JLabel("Liste de mes exemplaires de jeux a pr\u00EAter");
		lblJeuxAPreter.setBounds(10, 28, 243, 14);
		contentPane.add(lblJeuxAPreter);
		
		Exemplaire e = new Exemplaire();

		List<Exemplaire> listExemplaires = e.findAll(currentPreteur, connect);
		currentPreteur.setListExamplaire(listExemplaires);
		List<Exemplaire> listExemplaire = currentPreteur.getListExemplaire();

		Object[] donnees = new Object[listExemplaire.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
		
		for (int i = 0; i < listExemplaire.size(); i++) {
			String dispo = " ";
			if (listExemplaire.get(i).getJeu().isDispo()) {
				dispo = "Disponible";
			} else {
				dispo = "Indisponible";
			}
			
			donnees[i] = listExemplaire.get(i).getNbrExemplaire() + " - " + listExemplaire.get(i).getJeu().getNom()
					+ " - " + listExemplaire.get(i).getJeu().getConsole().getNom() + " - " + dispo + " - " + "Tarif : "
					+ listExemplaire.get(i).getJeu().getTarif() + " - " + "Date tarif : "
					+ simpleDateFormat.format(listExemplaire.get(i).getJeu().getDateTarif());

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
		btnRetour.setBounds(466, 428, 89, 23);
		contentPane.add(btnRetour);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 46, 564, 350);
		contentPane.add(scrollPane);
		JList listJeux = new JList(donnees);
		listJeux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listJeux);
	}
}
