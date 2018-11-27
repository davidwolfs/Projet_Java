package gui.emprunteur;

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

public class Coter_Preteurs extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4903296286659925322L;
	private JPanel contentPane;
	@SuppressWarnings("unused")
	private Connection connect;
	@SuppressWarnings("unused")
	private Emprunteur currentEmprunteur;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Coter_Preteurs(Connection connect, Emprunteur currentEmprunteur) {
		this.connect = connect;
		this.currentEmprunteur = currentEmprunteur;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListePreteurs = new JLabel("Liste des pr\u00EAteurs");
		lblListePreteurs.setBounds(10, 25, 137, 14);
		contentPane.add(lblListePreteurs);

		Preteur preteur = new Preteur();
		preteur.setiD(currentEmprunteur.getiD());
		preteur.setNom(currentEmprunteur.getNom());
		preteur.setPrenom(currentEmprunteur.getPrenom());
		preteur.setDateNaiss(currentEmprunteur.getDateNaiss());
		preteur.setEmail(currentEmprunteur.getEmail());
		preteur.setPassword(currentEmprunteur.getPassword());

		List<Preteur> listPreteur = preteur.findAllExceptcurrentPreteur(preteur, connect);

		Object[] donnees2 = new Object[listPreteur.size()];
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");

		java.text.DecimalFormat df = new java.text.DecimalFormat("0.#");

		for (int i = 0; i < listPreteur.size(); i++) {
			donnees2[i] = listPreteur.get(i).getNom() + " " + listPreteur.get(i).getPrenom() + " - "
					+ simpleDateFormat.format(listPreteur.get(i).getDateNaiss()) + " - " + listPreteur.get(i).getEmail()
					+ " - " + " - " + "Cote : " + df.format(listPreteur.get(i).CalculerMoyenneCote()) + "/5" + " - "
					+ listPreteur.get(i).getNbrCote() + " avis.";
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

		JList listPreteurs = new JList(donnees2);
		scrollPane.setViewportView(listPreteurs);

		JButton btnCoter = new JButton("C\u00F4ter");
		btnCoter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = listPreteurs.getSelectedIndex();

				if (index == -1) {
					lblMsgErrorEmprunteur.setText("Veuillez sélectionner un prêteur.");
				} else {
					dispose();
					Attribuer_Cote attribuer_Cote = new Attribuer_Cote(connect, currentEmprunteur,
							listPreteur.get(index));
					attribuer_Cote.setVisible(true);
					attribuer_Cote.setResizable(false);
				}

			}
		});
		btnCoter.setBounds(10, 447, 89, 23);
		contentPane.add(btnCoter);

		listPreteurs.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = listPreteurs.getSelectedIndex();
				if (preteur.isAlreadyCote(listPreteur.get(index), currentEmprunteur, connect)) {
					lblMsgErrorEmprunteur.setText("Vous avez déjà côté ce prêteur.");
					btnCoter.setEnabled(false);
				} else {
					lblMsgErrorEmprunteur.setText("");
					btnCoter.setEnabled(true);
				}
			}
		});
	}

}
