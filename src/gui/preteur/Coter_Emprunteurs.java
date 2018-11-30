package gui.preteur;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exo.Emprunteur;
import exo.Preteur;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Coter_Emprunteurs extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5173004681407778032L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Preteur currentPreteur;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Coter_Emprunteurs(Connection connect, Preteur currentPreteur) {
		this.connect = connect;
		this.currentPreteur = currentPreteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListeEmprunteurs = new JLabel("Liste des emprunteurs");
		lblListeEmprunteurs.setBounds(10, 25, 137, 14);
		contentPane.add(lblListeEmprunteurs);

		Emprunteur emprunteur = new Emprunteur();
		emprunteur.setiD(currentPreteur.getiD());
		emprunteur.setNom(currentPreteur.getNom());
		emprunteur.setPrenom(currentPreteur.getPrenom());
		emprunteur.setDateNaiss(currentPreteur.getDateNaiss());
		emprunteur.setEmail(currentPreteur.getEmail());
		emprunteur.setPassword(currentPreteur.getPassword());

		List<Emprunteur> listEmprunteur = emprunteur.findAllExceptcurrentEmprunteur(emprunteur, connect);

		Preteur preteur = new Preteur();

		Object[] donnees2 = new Object[listEmprunteur.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.#");

		for (int i = 0; i < listEmprunteur.size(); i++) {
			donnees2[i] = listEmprunteur.get(i).getNom() + " " + listEmprunteur.get(i).getPrenom() + " - "
					+ simpleDateFormat.format(listEmprunteur.get(i).getDateNaiss()) + " - "
					+ listEmprunteur.get(i).getEmail() + " - " + listEmprunteur.get(i).getUnite() + " U" + " - " + " - "
					+ "Cote : " + df.format(listEmprunteur.get(i).CalculerMoyenneCote()) + "/5" + " - "
					+ listEmprunteur.get(i).getNbrCote() + " avis.";
		}

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 764, 334);
		contentPane.add(scrollPane);

		JList listEmprunteurs = new JList(donnees2);
		listEmprunteurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listEmprunteurs);

		JButton btnCoter = new JButton("C\u00F4ter");
		btnCoter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listEmprunteurs.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorEmprunteur.setText("Veuillez sélectionner un emprunteur.");
				} else {
					dispose();
					Attribuer_Cote attribuer_Cote = new Attribuer_Cote(connect, currentPreteur,
							listEmprunteur.get(index));
					attribuer_Cote.setVisible(true);
					attribuer_Cote.setResizable(false);
				}

			}
		});
		btnCoter.setBounds(10, 447, 89, 23);
		contentPane.add(btnCoter);

		listEmprunteurs.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listEmprunteurs.getSelectedIndex();
				if (preteur.isAlreadyCote(currentPreteur, listEmprunteur.get(index), connect)) {
					lblMsgErrorEmprunteur.setText("Vous avez déjà côté cet emprunteur.");
					btnCoter.setEnabled(false);
				} else {
					lblMsgErrorEmprunteur.setText("");
					btnCoter.setEnabled(true);
				}
			}
		});
	}
}
