package exo;

import java.util.Date;

public class Joueur extends Personne {

	private double solde;
	private Date date_en;

	public Joueur(double solde, Date date_en) {
		this.solde = solde;
		this.date_en = date_en;
	}

	public Joueur(int id, String nom, String prenom, Date dateNaiss, String email, String password) {
		super(id, nom, prenom, dateNaiss, email, password);
	}
	
	public Joueur(String nom, String prenom, Date dateNaiss, String email, String password) {
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
	
	public void rayerJoueur()
	{
		
	}
	
	@Override
	public String toString() {
		return "Joueur [solde=" + solde + ", date_en=" + date_en + "]";
	}

}
