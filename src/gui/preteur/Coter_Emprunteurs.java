package gui.preteur;

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

public class Coter_Emprunteurs extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;

	/**
	 * Create the frame.
	 */
	public Coter_Emprunteurs(Connection connect, Preteur currentPreteur) {
		this.connect=connect;
		this.currentPreteur=currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblListeEmprunteurs = new JLabel("Liste des emprunteurs");
		lblListeEmprunteurs.setBounds(10, 25, 137, 14);
		contentPane.add(lblListeEmprunteurs);

		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		
		PreteurDAO preteurDAO = new PreteurDAO(connect);

		Object[] donnees2 = new Object[listEmprunteurs.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
		
		for (int i = 0; i < listEmprunteurs.size(); i++) {
			System.out.println(listEmprunteurs.get(i).toString());
			donnees2[i] = listEmprunteurs.get(i).getNom() + " "
					+ listEmprunteurs.get(i).getPrenom() + " - "
					+ simpleDateFormat.format(listEmprunteurs.get(i).getDateNaiss()) + " - "
					+ listEmprunteurs.get(i).getEmail() + " - " + listEmprunteurs.get(i).getUnite() + " U";
		}

		JList listEmprunteur = new JList(donnees2);
		listEmprunteur.setBounds(10, 50, 764, 334);
		contentPane.add(listEmprunteur);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(connect, currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
		btnRetour.setBounds(680, 447, 89, 23);
		contentPane.add(btnRetour);
		
		JLabel lblMsgErrorEmprunteur = new JLabel("");
		lblMsgErrorEmprunteur.setBounds(252, 451, 252, 19);
		contentPane.add(lblMsgErrorEmprunteur);
		
		JButton btnCoter = new JButton("C\u00F4ter");
		btnCoter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listEmprunteur.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorEmprunteur.setText("Veuillez sélectionner un emprunteur.");
				} else {
					System.out.println(index);
					dispose();
					Attribuer_Cote attribuer_Cote = new Attribuer_Cote(connect, currentPreteur, listEmprunteurs.get(index));
					attribuer_Cote.setVisible(true);
					attribuer_Cote.setResizable(false);
				}
				
			}
		});
		btnCoter.setBounds(10, 447, 89, 23);
		contentPane.add(btnCoter);
		
	
		
	}
}
