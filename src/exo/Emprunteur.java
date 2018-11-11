package exo;

import java.sql.Connection;
import java.util.Date;

import dao.EmprunteurDAO;

public class Emprunteur extends Joueur {
	private int unite;
	private int cote;
	private int nbrCote = 0;
	private Reservation reservation;

	public Emprunteur(int cote) {
		this.unite = 10;
		this.cote = cote;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = 10;
	}

	public Emprunteur(String nom, String prenom, Date dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
		this.unite = 10;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite, Reservation reservation) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
		this.reservation = reservation;
	}
	
	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
	}
	
	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite, int cote, int nbrCote) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
		this.cote=cote;
		this.nbrCote=nbrCote;
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
		this.unite = 10;
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

	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getNbrCote() {
		return nbrCote;
	}

	public void setNbrCote(int nbrCote) {
		this.nbrCote = nbrCote;
	}
	
	public void incrementerNbrCote() {
		this.nbrCote++;
	}

	public void ajouterUnite(int unite) {
		
		this.unite += unite;
	}
	
	public void soustraireUnite(int unite) {
		this.unite -= unite;
	}

	public void AjouterCote(int cote) {
		this.cote += cote;
	}

	public double CalculerMoyenneCote()
	{
		double moyenneCote = 0.0;
		
		// On déclenche une exception si on a une division par zéro
		try
		{
			moyenneCote = (double)(cote / nbrCote);
			System.out.println(moyenneCote);
		}
		catch(ArithmeticException ex)
		{
			ex.getMessage();
		}
		
		return moyenneCote;
	}
	
	@Override
	public String toString() {
		return "Emprunteur [unite=" + unite + ", cote=" + cote + ", reservation=" + reservation + ", iD=" + iD
				+ ", nom=" + nom + ", prenom=" + prenom + ", dateNaiss=" + dateNaiss + ", email=" + email
				+ ", password=" + password + "]";
	}

}
