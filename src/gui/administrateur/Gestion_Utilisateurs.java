package gui.administrateur;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import exo.Administrateur;
import exo.Emprunteur;
import exo.Preteur;
import gui.Connexion;
import gui.Main;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class Gestion_Utilisateurs extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Gestion_Utilisateurs(Connection connect, Administrateur currentAdministrateur) {
		setTitle("Projet Jeux Video");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeAdministrateur = new JLabel("Liste des administrateurs");
		lblListeAdministrateur.setBounds(10, 11, 146, 14);
		contentPane.add(lblListeAdministrateur);

		Administrateur admin = new Administrateur();
		List<Administrateur> listAdministrateur = admin.findAll(connect);

		Object[] donnees = new Object[listAdministrateur.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		for (int i = 0; i < listAdministrateur.size(); i++) {
			System.out.println(listAdministrateur.get(i).toString());
			donnees[i] = listAdministrateur.get(i).getNom() + " " + listAdministrateur.get(i).getPrenom() + " - "
					+ simpleDateFormat.format(listAdministrateur.get(i).getDateNaiss()) + " - "
					+ listAdministrateur.get(i).getEmail();
		}

		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Connexion connexion = new Connexion(connect);
				connexion.setVisible(true);
				connexion.setResizable(false);
			}
		});
		btnDeconnexion.setBounds(10, 527, 118, 23);
		contentPane.add(btnDeconnexion);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Administrateur dashboard_Administrateur = new Dashboard_Administrateur(connect,
						currentAdministrateur);
				dashboard_Administrateur.setVisible(true);
				dashboard_Administrateur.setResizable(false);
			}
		});
		btnRetour.setBounds(685, 527, 89, 23);
		contentPane.add(btnRetour);

		JButton btnAjouterAdministrateur = new JButton("Ajouter un administrateur");
		btnAjouterAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Ajouter_Administrateur ajouter_Administrateur = new Ajouter_Administrateur(connect,
						currentAdministrateur);
				ajouter_Administrateur.setVisible(true);
				ajouter_Administrateur.setResizable(false);
			}
		});
		btnAjouterAdministrateur.setBounds(10, 198, 200, 23);
		contentPane.add(btnAjouterAdministrateur);

		JLabel lblMsgErrorAdministrateur = new JLabel("");
		lblMsgErrorAdministrateur.setBounds(302, 233, 287, 23);
		contentPane.add(lblMsgErrorAdministrateur);

		JLabel lblMsgErrorParticipant = new JLabel("");
		lblMsgErrorParticipant.setBounds(302, 516, 241, 23);
		contentPane.add(lblMsgErrorParticipant);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 764, 150);
		contentPane.add(scrollPane);

		JList listAdministrateurs = new JList(donnees);
		scrollPane.setViewportView(listAdministrateurs);
		listAdministrateurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnModifierAdministrateur = new JButton("Modifier un administrateur");
		btnModifierAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listAdministrateurs.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorAdministrateur.setText("Veuillez sélectionner un administrateur.");
				} else {
					System.out.println(index);
					dispose();
					Modifier_Administrateur modifier_Administrateur = new Modifier_Administrateur(connect,
							currentAdministrateur, listAdministrateur.get(index));
					modifier_Administrateur.setVisible(true);
					modifier_Administrateur.setResizable(false);
				}
			}
		});
		btnModifierAdministrateur.setBounds(318, 198, 185, 23);
		contentPane.add(btnModifierAdministrateur);

		JButton btnSupprimerAdministrateur = new JButton("Supprimer un administrateur");
		btnSupprimerAdministrateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Administrateur administrateur = new Administrateur();
				
				int index = listAdministrateurs.getSelectedIndex();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
				System.out.println(index);
				if (index == -1) {
					lblMsgErrorAdministrateur.setText("Veuillez sélectionner un administrateur.");
				} else if (index == 0) {
					lblMsgErrorAdministrateur.setText("Vous ne pouvez pas supprimer cet administrateur.");
				} else if (listAdministrateurs.getSelectedValue()
						.equals(currentAdministrateur.getNom() + " " + currentAdministrateur.getPrenom() + " - "
								+ simpleDateFormat.format(currentAdministrateur.getDateNaiss()) + " - "
								+ currentAdministrateur.getEmail())) {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer votre propre compte administrateur ? Vous serez ensuite déconnecté.");
					if (input == 0) {
						int id = listAdministrateur.get(index).getiD();
						System.out.println(id);
						JOptionPane.showMessageDialog(null, "Votre compte administrateur a bien été supprimé.");
						administrateur.delete(listAdministrateur.get(index), connect);
						dispose();
						Main.creerConnexion();
					}
				} else {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer cette administrateur ?");
					if (input == 0) {
						int id = listAdministrateur.get(index).getiD();
						System.out.println(id);
						administrateur.delete(listAdministrateur.get(index), connect);

						dispose();
						Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect,
								currentAdministrateur);
						gestion_Utilisateurs.setVisible(true);
						gestion_Utilisateurs.setResizable(false);
					}
				}
			}
		});
		btnSupprimerAdministrateur.setBounds(560, 198, 200, 23);
		contentPane.add(btnSupprimerAdministrateur);

		JLabel lblListeParticipants = new JLabel("Liste des participants");
		lblListeParticipants.setBounds(10, 255, 146, 14);
		contentPane.add(lblListeParticipants);
		
		Emprunteur e = new Emprunteur();
		List<Emprunteur> listEmprunteurs = e.findAll(connect);
		
		Preteur p = new Preteur();
		
		Object[] donnees2 = new Object[listEmprunteurs.size()];

		for (int i = 0; i < listEmprunteurs.size(); i++) {
			System.out.println(listEmprunteurs.get(i).toString());
			donnees2[i] = listEmprunteurs.get(i).getNom() + " " + listEmprunteurs.get(i).getPrenom() + " - "
					+ simpleDateFormat.format(listEmprunteurs.get(i).getDateNaiss()) + " - "
					+ listEmprunteurs.get(i).getEmail() + " - " + listEmprunteurs.get(i).getUnite() + " U";
		}

		JButton btnAjouterParticipant = new JButton("Ajouter un participant");
		btnAjouterParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Ajouter_Participant ajouter_Participant = new Ajouter_Participant(connect, currentAdministrateur);
				ajouter_Participant.setVisible(true);
				ajouter_Participant.setResizable(false);
			}
		});
		btnAjouterParticipant.setBounds(10, 474, 200, 23);
		contentPane.add(btnAjouterParticipant);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 296, 764, 150);
		contentPane.add(scrollPane_1);

		JList listEmprunteur = new JList(donnees2);
		scrollPane_1.setViewportView(listEmprunteur);

		JButton btnModifierParticipant = new JButton("Modifier un participant");
		btnModifierParticipant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listEmprunteur.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorParticipant.setText("Veuillez sélectionner un participant.");
				} else {
					System.out.println(index);
					dispose();
					Modifier_Participant modifier_Participant = new Modifier_Participant(connect, currentAdministrateur,
							listEmprunteurs.get(index), p.find(listEmprunteurs.get(index).getiD(), connect));
					modifier_Participant.setVisible(true);
					modifier_Participant.setResizable(false);
				}
			}
		});
		btnModifierParticipant.setBounds(318, 474, 185, 23);
		contentPane.add(btnModifierParticipant);

		JButton btnSupprimerParticipant = new JButton("Supprimer un participant");
		btnSupprimerParticipant.addActionListener(new ActionListener() {
			Emprunteur emprunteur = new Emprunteur();
			Preteur preteur = new Preteur();
			
			public void actionPerformed(ActionEvent e) {
				int index = listEmprunteur.getSelectedIndex();
				System.out.println(index);
				if (index == -1) {
					lblMsgErrorParticipant.setText("Veuillez sélectionner un participant.");
				} else {
					int input = JOptionPane.showConfirmDialog(null,
							"Êtes-vous sûr de bien vouloir supprimer ce participant ?");
					if (input == 0) {
						int id = listEmprunteurs.get(index).getiD();
						System.out.println(id);
						emprunteur.delete(listEmprunteurs.get(index), connect);
						preteur.delete(p.find(id, connect), connect);

						dispose();
						Gestion_Utilisateurs gestion_Utilisateurs = new Gestion_Utilisateurs(connect,
								currentAdministrateur);
						gestion_Utilisateurs.setVisible(true);
						gestion_Utilisateurs.setResizable(false);
					}
				}
			}
		});
		btnSupprimerParticipant.setBounds(567, 474, 193, 23);
		contentPane.add(btnSupprimerParticipant);
	}
}
