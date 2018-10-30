package exo;

import java.sql.Connection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import dao.JeuDAO;

public class Jeu {
	private Connection connect;
	private String nom;
	private boolean dispo;
	private double tarif;
	private Date dateTarif;
	private String adapterTarif;
	private Console console;

	public Jeu(String nom, boolean dispo, double tarif, Date dateTarif, String adapterTarif) {
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
		this.adapterTarif = adapterTarif;
	}
	
	public Jeu() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isDispo() {
		return dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

	public Date getDateTarif() {
		return dateTarif;
	}

	public void setDateTarif(Date dateTarif) {
		this.dateTarif = dateTarif;
	}

	public String getAdapterTarif() {
		return adapterTarif;
	}

	public void setAdapterTarif(String adapterTarif) {
		this.adapterTarif = adapterTarif;
	}
	
	@Override
	public String toString() {
		return "Jeu [nom=" + nom + ", dispo=" + dispo + ", tarif=" + tarif + ", dateTarif=" + dateTarif
				+ ", adapterTarif=" + adapterTarif + ", console=" + console + "]";
	}

}
