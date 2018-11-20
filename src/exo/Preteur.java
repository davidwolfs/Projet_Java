package exo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Preteur extends Joueur {
	private List<Exemplaire> listExemplaire = new ArrayList<>();
	private double cote;
	private int nbrCote = 0;

	public Preteur(List<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public Preteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int cote) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.cote = cote;
	}

	public Preteur(int id, String nom, String prenom, Date dateNaiss, String email, String password) {
		super(id, nom, prenom, dateNaiss, email, password);
	}

	public Preteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, double cote,
			int nbrCote) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.cote = cote;
		this.nbrCote = nbrCote;
	}

	public Preteur(String nom, String prenom, Date dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
	}

	public Preteur() {

	}

	public double getCote() {
		return cote;
	}

	public void setCote(double cote) {
		this.cote = cote;
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

	public List<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	public void setListExamplaire(List<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public void AjouterExemplaire(Exemplaire exemplaire) {
		this.listExemplaire.add(exemplaire);
	}

	public void SupprimerExemplaire(Exemplaire exemplaire) {
		this.listExemplaire.remove(exemplaire);
	}

	@Override
	public double CalculerMoyenneCote() {
		double moyenneCote = 0.0;

		// On déclenche une exception si on a une division par zéro
		try {
			if (nbrCote == 0) {
				moyenneCote = 0.0;
			} else {
				moyenneCote = (cote / nbrCote);

			}
			System.out.println(moyenneCote);
		} catch (ArithmeticException ex) {
			ex.getMessage();
		}

		return moyenneCote;
	}

	@Override
	public String toString() {
		return "Preteur [listExemplaire=" + listExemplaire + ", cote=" + cote + "]";
	}

}
