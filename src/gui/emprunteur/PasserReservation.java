package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Jeu;
import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.JList;

public class PasserReservation extends JFrame {

	private JPanel contentPane;
	private JButton btnReservation;
	private JList list;

	/**
	 * Create the frame.
	 */
	public PasserReservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDateReservation = new JLabel("Date r\u00E9servation");
		lblDateReservation.setBounds(47, 48, 139, 19);
		contentPane.add(lblDateReservation);

		JDateChooser dateChooserDateReservation = new JDateChooser();
		dateChooserDateReservation.setBounds(217, 48, 171, 19);
		contentPane.add(dateChooserDateReservation);

		list = new JList();
		list.setBounds(47, 163, 335, -62);
		contentPane.add(list);
		
		btnReservation = new JButton("R\u00E9server");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Jeu jeu = new Jeu("GTA V", true, 50, dateChooserDateReservation.getDate(), "Tarif");
				Reservation reservation = new Reservation(dateChooserDateReservation.getDate(), jeu);
				reservation.create(reservation);
			}
		});
		btnReservation.setBounds(38, 189, 89, 23);
		contentPane.add(btnReservation);
	}
}
