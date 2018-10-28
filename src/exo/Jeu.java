package exo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Jeu {
	private String nom;
	private boolean dispo;
	private double tarif;
	private Date dateTarif;
	private String adapterTarif;
	private Console console;
	private Set<Exemplaire> listExemplaire = new HashSet<>();

	public Jeu(String nom, boolean dispo, double tarif, Date dateTarif, String adapterTarif) {
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
		this.adapterTarif = adapterTarif;
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

	public Set<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	public void setListExemplaire(Set<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	@Override
	public String toString() {
		return "Jeu [nom=" + nom + ", dispo=" + dispo + ", tarif=" + tarif + ", dateTarif=" + dateTarif
				+ ", adapterTarif=" + adapterTarif + ", listExemplaire=" + listExemplaire + "]";
	}

}
