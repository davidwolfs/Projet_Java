package exo;

import java.sql.Connection;

import dao.EmprunteurDAO;
import dao.PreteurDAO;

public class Emprunteur extends Joueur {
	private Connection connect;
	private int unite;
	private int cote;

	public Emprunteur(int cote) {
		this.unite = 10;
		this.cote = cote;
	}

	public Emprunteur(String nom, String prenom, String dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
	}
	
	public Emprunteur() {
		super();
	}

	public int getUnite() {
		return unite;
	}

	public void setUnite(int unite) {
		this.unite = unite;
	}

	public int getCote() {
		return cote;
	}

	public void setCote(int cote) {
		this.cote = cote;
	}
	
	public void create(Emprunteur emprunteur)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.create(emprunteur);
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
		return "Emprunteur [unite=" + unite + ", cote=" + cote + "]";
	}

}
