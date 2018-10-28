package gui.emprunteur;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exo.Reservation;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

public class PasserReservation extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldDateReservation;
	private JButton btnReservation;

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
		
		textFieldDateReservation = new JTextField();
		textFieldDateReservation.setBounds(234, 44, 166, 26);
		contentPane.add(textFieldDateReservation);
		textFieldDateReservation.setColumns(10);
		
		btnReservation = new JButton("R\u00E9server");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservation reservation = new Reservation(new Date(textFieldDateReservation.getText()));
				reservation.create(reservation);
			}
		});
		btnReservation.setBounds(38, 189, 89, 23);
		contentPane.add(btnReservation);
	}
}
