package exo;

import java.sql.Connection;
import java.util.Date;

import dao.AdministrateurDAO;

public class Administrateur extends Personne {

	public Administrateur(String nom, String prenom, Date date, String email, String password) {
		super(nom, prenom, date, email, password);
	}

	public Administrateur() {
		super();
	}
}
