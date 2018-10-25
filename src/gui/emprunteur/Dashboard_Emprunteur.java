package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Emprunteur;

import javax.swing.JLabel;

public class Dashboard_Emprunteur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_Emprunteur frame = new Dashboard_Emprunteur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Dashboard_Emprunteur(Emprunteur emprunteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + emprunteur.getPrenom() + " " + emprunteur.getNom() + ", vous êtes connecté en tant que : Emprunteur");
		lblBienvenue.setBounds(10, 26, 414, 24);
		contentPane.add(lblBienvenue);
	}

}
