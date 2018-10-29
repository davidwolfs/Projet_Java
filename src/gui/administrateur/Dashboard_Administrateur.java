package gui.administrateur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Administrateur;
import gui.Main;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard_Administrateur extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Dashboard_Administrateur(Administrateur currentAdministrateur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + currentAdministrateur.getPrenom() + " " + currentAdministrateur.getNom() + ", vous êtes connecté en tant que : Administrateur");
		lblBienvenue.setBounds(10, 27, 414, 21);
		contentPane.add(lblBienvenue);
		
		JButton btnDeconnexion = new JButton("D\u00E9connexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main.creerConnexion();
			}
		});
		btnDeconnexion.setBounds(10, 209, 117, 23);
		contentPane.add(btnDeconnexion);
		
		JButton btnGestionDesConsoles = new JButton("Gestion des consoles");
		btnGestionDesConsoles.setBounds(152, 73, 159, 28);
		contentPane.add(btnGestionDesConsoles);
		
		JButton btnGestionDesJeux = new JButton("Gestion des jeux");
		btnGestionDesJeux.setBounds(152, 138, 159, 28);
		contentPane.add(btnGestionDesJeux);
	}
}
