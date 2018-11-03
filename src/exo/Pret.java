package exo;

import java.util.Date;

public class Pret {
	private Date dateDebut;
	private Date dateFin;
	private boolean confirmer_pret;
	private Exemplaire exemplaire;
	private Emprunteur emprunteur;
	private Preteur preteur;

	public Pret(Date dateDebut, Date dateFin, boolean confirmer_pret, Exemplaire exemplaire, Emprunteur emprunteur,
			Preteur preteur) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.confirmer_pret = confirmer_pret;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.preteur = preteur;

	}
	
	public Pret(Date dateDebut, Date dateFin, Emprunteur emprunteur) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.emprunteur = emprunteur;

	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isConfirmer_pret() {
		return confirmer_pret;
	}

	public void setConfirmer_pret(boolean confirmer_pret) {
		this.confirmer_pret = confirmer_pret;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Preteur getPreteur() {
		return preteur;
	}

	public void setPreteur(Preteur preteur) {
		this.preteur = preteur;
	}

	public boolean ConfirmationEmprunteurDebut()
	{
		return false;
	}
	
	public boolean ConfirmationPreteurDebut()
	{
		return false;
	}
	
	public boolean ConfirmationEmprunteurFin()
	{
		return false;
	}
	
	public boolean ConfirmationPreteurFin()
	{
		return false;
	}
	
	@Override
	public String toString() {
		return "Pret [dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", confirmer_pret=" + confirmer_pret + "]";
	}

}
