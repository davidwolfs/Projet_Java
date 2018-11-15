package gui;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.EmprunteurDAO;
import dao.PretDAO;
import driver.DriverACCESS;
import exo.Emprunteur;
import exo.Pret;

public class Main {
	private static Connection connect = DriverACCESS.getInstance();

	public static void creerConnexion() {
		Connexion connexion = new Connexion(connect);
		connexion.setVisible(true);
		connexion.setResizable(false);
	}

	public static void main(String[] args) {
		creerConnexion();
	}

}
