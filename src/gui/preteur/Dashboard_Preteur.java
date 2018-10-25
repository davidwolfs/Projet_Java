package gui.preteur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Preteur;

public class Dashboard_Preteur extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard_Preteur frame = new Dashboard_Preteur();
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
	public Dashboard_Preteur(Preteur preteur) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBienvenue = new JLabel("Bienvenue " + preteur.getNom() + " " + preteur.getPrenom() + ", vous êtes connecté en tant que : Preteur");
		lblBienvenue.setBounds(10, 25, 414, 20);
		contentPane.add(lblBienvenue);
	}

}
