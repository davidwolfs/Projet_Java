package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.EmprunteurDAO;
import dao.PreteurDAO;
import exo.Emprunteur;
import exo.Preteur;
import gui.administrateur.Modifier_Participant;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Coter_Preteurs extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Emprunteur currentEmprunteur;

	/**
	 * Create the frame.
	 */
	public Coter_Preteurs(Connection connect, Emprunteur currentEmprunteur) {
		this.connect=connect;
		this.currentEmprunteur=currentEmprunteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListePreteurs = new JLabel("Liste des pr\u00EAteurs");
		lblListePreteurs.setBounds(10, 25, 137, 14);
		contentPane.add(lblListePreteurs);

		PreteurDAO preteurDAO = new PreteurDAO(connect);
		
		
		Preteur preteur = new Preteur();
		preteur.setiD(currentEmprunteur.getiD());
		preteur.setNom(currentEmprunteur.getNom());
		preteur.setPrenom(currentEmprunteur.getPrenom());
		preteur.setDateNaiss(currentEmprunteur.getDateNaiss());
		preteur.setEmail(currentEmprunteur.getEmail());
		preteur.setPassword(currentEmprunteur.getPassword());
		
		List<Preteur> listPreteurs = preteurDAO.findAllExceptcurrentPreteur(preteur);
		
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);

		Object[] donnees2 = new Object[listPreteurs.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
		
		for (int i = 0; i < listPreteurs.size(); i++) {
			System.out.println(listPreteurs.get(i).toString());
			donnees2[i] = listPreteurs.get(i).getNom() + " "
					+ listPreteurs.get(i).getPrenom() + " - "
					+ simpleDateFormat.format(listPreteurs.get(i).getDateNaiss()) + " - "
					+ listPreteurs.get(i).getEmail() + " - " + " - "
					+ "Cote : " + listPreteurs.get(i).CalculerMoyenneCote() + " - "
					+ listPreteurs.get(i).getNbrCote() + " avis.";
		}
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Emprunteur dashboard_Emprunteur = new Dashboard_Emprunteur(connect, currentEmprunteur);
				dashboard_Emprunteur.setVisible(true);
				dashboard_Emprunteur.setResizable(false);
			}
		});
		btnRetour.setBounds(680, 447, 89, 23);
		contentPane.add(btnRetour);
		
		JLabel lblMsgErrorEmprunteur = new JLabel("");
		lblMsgErrorEmprunteur.setBounds(252, 451, 252, 19);
		contentPane.add(lblMsgErrorEmprunteur);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 764, 334);
		contentPane.add(scrollPane);
		
				JList listEmprunteur = new JList(donnees2);
				scrollPane.setViewportView(listEmprunteur);
				
		JButton btnCoter = new JButton("C\u00F4ter");
		btnCoter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listEmprunteur.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorEmprunteur.setText("Veuillez sélectionner un prêteur.");
				}
				else {
					System.out.println(index);
					dispose();
					Attribuer_Cote attribuer_Cote = new Attribuer_Cote(connect, currentEmprunteur, listPreteurs.get(index));
					attribuer_Cote.setVisible(true);
					attribuer_Cote.setResizable(false);
				}
				
			}
		});
		btnCoter.setBounds(10, 447, 89, 23);
		contentPane.add(btnCoter);
	}
}
