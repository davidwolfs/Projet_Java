package exo;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;

import dao.AdministrateurDAO;

public class Administrateur extends Personne {

	private Connection connect;

	public Administrateur(String nom, String prenom, String string, String email, String password,
			Connection connect) {
		super(nom, prenom, string, email, password);
		this.connect = connect;
	}

	public Administrateur(Connection connect) {
		super();
	}
	
	public Administrateur() {
		super();
	}

	public void create(Administrateur administrateur) {
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		administrateurDAO.create(administrateur);

	}

	public boolean findByEmailPassword(String email, String password, Connection connect) {
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		System.out.println("CONNECT IN FIND : " + connect);
		if(administrateurDAO.findByEmailPassword(email, password, connect))
		{
			return true;
		}
		return false;
	}
	
	public Administrateur findAdministrateurByEmailPassword(String email, String password, Connection connect) {
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		Administrateur administrateur = administrateurDAO.findAdministrateurByEmailPassword(email, password, connect);
		return administrateur;
	}

	public boolean alreadyExist(String email) {
		return false;
	}
}
