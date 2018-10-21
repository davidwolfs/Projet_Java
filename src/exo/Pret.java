package exo;

import java.util.Date;

public class Pret {
	private Date dateDebut;
	private Date dateFin;
	private boolean confirmer_pret;

	public Pret(Date dateDebut, Date dateFin, boolean confirmer_pret) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.confirmer_pret = confirmer_pret;

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

	@Override
	public String toString() {
		return "Pret [dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", confirmer_pret=" + confirmer_pret + "]";
	}

}
