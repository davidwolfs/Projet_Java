package exo;

import java.sql.Connection;
import java.util.Date;

import dao.EmprunteurDAO;

public class Emprunteur extends Joueur {
	private Connection connect;
	private int unite;
	private int cote;
	private Reservation reservation;

	public Emprunteur(int cote) {
		this.unite = 10;
		this.cote = cote;
	}

	public Emprunteur(String nom, String prenom, Date dateNaiss, String email, String password, Connection connect) {
		super(nom, prenom, dateNaiss, email, password);
		this.connect = connect;
		this.unite = 10;
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
	
	public void soustraireUnite(int unite)
	{
		this.unite -= unite;
	}
	
	public void soustraireCote(int cote)
	{
		this.cote -= cote;
	}

	public void create(Emprunteur emprunteur) {
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.create(emprunteur);
	}

	public boolean findByEmailPassword(String email, String password, Connection connect) {
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		if (emprunteurDAO.findByEmailPassword(email, password, connect)) {
			return true;
		}
		return false;
	}

	public Emprunteur findEmprunteurByEmailPassword(String email, String password, Connection connect) {
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		Emprunteur emprunteur = emprunteurDAO.findEmprunteurByEmailPassword(email, password, connect);
		return emprunteur;
	}

	public boolean alreadyExist(String email) {
		return false;
	}

	@Override
	public String toString() {
		return "Emprunteur [unite=" + unite + ", cote=" + cote + "]";
	}

}
