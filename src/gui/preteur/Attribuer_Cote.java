package gui.preteur;

import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Emprunteur;
import exo.Preteur;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Attribuer_Cote extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 924761164533305446L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Preteur currentPreteur;
	@SuppressWarnings("unused")
	private Emprunteur emprunteurACoter;

	/**
	 * Create the frame.
	 */
	public Attribuer_Cote(Connection connect, Preteur currentPreteur, Emprunteur emprunteurACoter) {
		this.connect = connect;
		this.currentPreteur = currentPreteur;
		this.emprunteurACoter = emprunteurACoter;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCote = new JLabel("C\u00F4te sur 5");
		lblCote.setBounds(10, 122, 107, 14);
		contentPane.add(lblCote);

		JSpinner spinnerCoteEmprunteur = new JSpinner();
		spinnerCoteEmprunteur.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinnerCoteEmprunteur.setBounds(372, 118, 40, 23);
		contentPane.add(spinnerCoteEmprunteur);

		JLabel lblMsgErrorEmprunteur = new JLabel("");
		lblMsgErrorEmprunteur.setBounds(142, 216, 324, 23);
		contentPane.add(lblMsgErrorEmprunteur);

		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cote = (int) spinnerCoteEmprunteur.getValue();

				if (cote <= 0) {
					lblMsgErrorEmprunteur.setText("Veuillez indiquer une cote de 1 � 5.");
				} else {
					emprunteurACoter.setCote(cote);
					emprunteurACoter.incrementerNbrCote();
					Emprunteur emprunteur = new Emprunteur();
					emprunteur.updateCote_NombreCote(emprunteurACoter, connect);
					Preteur preteur = new Preteur();
					preteur.marquerPreteursEmprunteursCotes(currentPreteur, emprunteurACoter, connect);
					dispose();
					Coter_Emprunteurs coter_Emprunteurs = new Coter_Emprunteurs(connect, currentPreteur);
					coter_Emprunteurs.setVisible(true);
					coter_Emprunteurs.setResizable(false);
				}
			}
		});
		btnValider.setBounds(10, 216, 89, 23);
		contentPane.add(btnValider);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Coter_Emprunteurs Coter_Emprunteurs = new Coter_Emprunteurs(connect, currentPreteur);
				Coter_Emprunteurs.setVisible(true);
				Coter_Emprunteurs.setResizable(false);
			}
		});
		btnRetour.setBounds(506, 216, 89, 23);
		contentPane.add(btnRetour);

		JLabel lblNoteAttribuee = new JLabel("Attribuer une note a ");
		lblNoteAttribuee.setBounds(10, 30, 129, 14);
		contentPane.add(lblNoteAttribuee);

		JLabel lblNomPrenomEmprunteur = new JLabel("");
		lblNomPrenomEmprunteur.setBounds(372, 24, 223, 26);
		lblNomPrenomEmprunteur.setText(emprunteurACoter.getPrenom() + " " + emprunteurACoter.getNom());
		contentPane.add(lblNomPrenomEmprunteur);
	}
}
