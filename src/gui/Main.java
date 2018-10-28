package gui;

import java.sql.Connection;

import driver.DriverACCESS;

public class Main {
	private static Connection connect = DriverACCESS.getInstance();

	public static void creerConnexion() {
		Connexion connexion = new Connexion(connect);
		connexion.setVisible(true);
	}

	public static void main(String[] args) {
		creerConnexion();
	}

}
