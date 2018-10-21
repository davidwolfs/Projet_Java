package exo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dao.PreteurDAO;

public class Preteur extends Joueur {
	private Connection connect;
	private List<Jeu> listJeu = new ArrayList<>();

	public Preteur(List<Jeu> listJeu) {
		this.listJeu = listJeu;
	}
	
	public Preteur(String nom, String prenom, String dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
	}

	public Preteur() {

	}

	public List<Jeu> getListJeu() {
		return listJeu;
	}

	public void setListJeu(List<Jeu> listJeu) {
		this.listJeu = listJeu;
	}

	public void create(Preteur preteur)
	{
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		preteurDAO.create(preteur);
	}
	public boolean findByEmailPassword(String email, String password) {
		if (email.equals("david.wolfs@condorcet.be") && password.equals("test")) {
			return true;
		}
		return false;
	}

	public boolean alreadyExist(String email)
	{
		return false;
	}
	
	@Override
	public String toString() {
		return "Preteur [listJeu=" + listJeu + "]";
	}

}
