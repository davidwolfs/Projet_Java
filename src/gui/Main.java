package gui;

import java.sql.Connection;

import javax.swing.JFrame;

import driver.DriverACCESS;

public class Main {
	private static JFrame frame = new JFrame("Projet Jeux Vidéo");
	private static Connection connect = DriverACCESS.getInstance();

	public static void creerConnexion() {
		Connexion connexion = new Connexion();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		creerConnexion();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
