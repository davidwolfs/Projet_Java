package exo;

import java.sql.Connection;
import java.util.Date;

import dao.EmprunteurDAO;

public class Emprunteur extends Joueur {
	private int unite;
	private int cote;
	private Reservation reservation;

	public Emprunteur(int cote) {
		this.unite = 10;
		this.cote = cote;
	}

	public Emprunteur(String nom, String prenom, Date dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
		this.unite = 10;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, double solde,
			Date date_en, int unite, int cote) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
		this.cote = cote;
	}

	public Emprunteur(String nom, String prenom, Date dateNaiss, String email, String password, int unite) {
		super(nom, prenom, dateNaiss, email, password);
		this.unite = unite;
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

	public void soustraireUnite(int unite) {
		this.unite -= unite;
	}

	public void AjouterCote(int cote) {
		this.cote += cote;
	}

	@Override
	public String toString() {
		return "Emprunteur [unite=" + unite + ", cote=" + cote + "]";
	}

}
