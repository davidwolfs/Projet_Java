package exo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import dao.PreteurDAO;

public class Preteur extends Joueur {
	private Connection connect;
	private List<Exemplaire> listExemplaire = new ArrayList<>();

	public Preteur(List<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public Preteur(String nom, String prenom, Date dateNaiss, String email, String password, Connection connect) {
		super(nom, prenom, dateNaiss, email, password);
		this.connect = connect;
	}

	public Preteur() {

	}

	public List<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	public void setListExamplaire(List<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public void create(Preteur preteur) {
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		preteurDAO.create(preteur);
	}

	public boolean findByEmailPassword(String email, String password, Connection connect) {
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		if (preteurDAO.findByEmailPassword(email, password, connect)) {
			return true;
		}
		return false;
	}

	public Preteur findPreteurByEmailPassword(String email, String password, Connection connect) {
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		Preteur preteur = preteurDAO.findPreteurByEmailPassword(email, password, connect);
		return preteur;
	}

	public boolean alreadyExist(String email) {
		return false;
	}

	@Override
	public String toString() {
		return "Preteur [listExemplaire=" + listExemplaire + "]";
	}

}
