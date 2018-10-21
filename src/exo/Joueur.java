package exo;

import java.util.Date;

public class Joueur extends Personne {

	private double solde;
	private Date date_en;

	public Joueur(double solde, Date date_en) {
		this.solde = solde;
		this.date_en = date_en;
	}

	public Joueur(String nom, String prenom, String dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
	}
	
	public Joueur() {
		super();
	}

	public double getSolde() {
		return solde;
	}

	public void setSolde(double solde) {
		this.solde = solde;
	}

	public Date getDate_en() {
		return date_en;
	}

	public void setDate_en(Date date_en) {
		this.date_en = date_en;
	}

	@Override
	public String toString() {
		return "Joueur [solde=" + solde + ", date_en=" + date_en + "]";
	}

}
