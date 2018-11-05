package gui.preteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ExemplaireDAO;
import dao.JeuDAO;
import exo.Exemplaire;
import exo.Jeu;
import exo.Preteur;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Liste_Jeux_A_Preter extends JFrame {

	private JPanel contentPane;
	private Connection connect;
	private Preteur currentPreteur;

	/**
	 * Create the frame.
	 */
	public Liste_Jeux_A_Preter(Connection connect, Preteur currentPreteur) {
		this.connect=connect;
		this.currentPreteur=currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblJeuxAPreter = new JLabel("Liste des jeux a pr\u00EAter");
		lblJeuxAPreter.setBounds(10, 28, 154, 14);
		contentPane.add(lblJeuxAPreter);
		
		JeuDAO jeuDAO = new JeuDAO(connect);
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		
		List<Exemplaire> listExemplaires = exemplaireDAO.findAll(currentPreteur);
		currentPreteur.setListExamplaire(listExemplaires);
		List<Exemplaire> listExemplaire = currentPreteur.getListExemplaire();

		
		for(int i=0;i<currentPreteur.getListExemplaire().size();i++)
		{
			JOptionPane.showMessageDialog(null, listExemplaire.get(i).getJeu().getNom() + " " + listExemplaire.get(i).getJeu().getConsole().getNom());
		}
		
		// List<Vehicule> listVehicule = vehiculeDAO.listVehicule();
		Object[] exemplaire = listExemplaire.toArray();

		Object[] donnees = new Object[listExemplaire.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
		
		
		System.out.println("SIZE : " + listExemplaire.size());
		int nbrExemplaires = 1;
		for (int i = 0; i < listExemplaire.size(); i++) 
		{
				if(i<listExemplaire.size()-1)
				{
					/*System.out.println(listExemplaire.get(i).getJeu().getId());
					System.out.println(listExemplaire.get(i+1).getJeu().getId());
					
					
					System.out.println(listExemplaire.get(i).getJeu().getNom());
					System.out.println(listExemplaire.get(i+1).getJeu().getNom());
					
					System.out.println(listExemplaire.get(i).getJeu().getConsole().getNom());
					System.out.println(listExemplaire.get(i+1).getJeu().getConsole().getNom());*/
					
					if(listExemplaire.get(i).getJeu().getNom().equals(listExemplaire.get(i+1).getJeu().getNom()) && listExemplaire.get(i).getJeu().getConsole().getNom().equals(listExemplaire.get(i+1).getJeu().getConsole().getNom()))
					{
						listExemplaire.remove(listExemplaire.get(i));
						System.out.println("EQUALS");
						nbrExemplaires++;
						
					}
					else
					{
						System.out.println("NOT EQUALS");
					}
	
				}
		System.out.println("Nombre d'exemplaires : " + nbrExemplaires);
		
			String dispo = " ";
			if(listExemplaire.get(i).getJeu().isDispo())
			{
				dispo = "Disponible";
			}
			else
			{
				dispo = "Indisponible";
			}
			
			System.out.println(listExemplaire.get(i).toString());
			donnees[i] = nbrExemplaires + " - " + listExemplaire.get(i).getJeu().getNom() + " - "
					+ dispo + " - "
					+ listExemplaire.get(i).getJeu().getTarif() + " - " 
					+ simpleDateFormat.format(listExemplaire.get(i).getJeu().getDateTarif()) + " - "
					+ listExemplaire.get(i).getJeu().getAdapterTarif() + " - "
					+ listExemplaire.get(i).getJeu().getConsole().getNom();
			
		}
		JList listJeux = new JList(donnees);
		listJeux.setBounds(10, 46, 564, 214);
		contentPane.add(listJeux);
		

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Dashboard_Preteur dashboard_Preteur = new Dashboard_Preteur(connect, currentPreteur);
				dashboard_Preteur.setVisible(true);
				dashboard_Preteur.setResizable(false);
			}
		});
		btnRetour.setBounds(466, 392, 89, 23);
		contentPane.add(btnRetour);
	}
}