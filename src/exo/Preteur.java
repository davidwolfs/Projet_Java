package exo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.PreteurDAO;

public class Preteur extends Joueur {
	private Set<Exemplaire> listExemplaire = new HashSet<>();

	public Preteur(Set<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public Preteur(int id, String nom, String prenom, Date dateNaiss, String email, String password) {
		super(id, nom, prenom, dateNaiss, email, password);
	}
	
	public Preteur(String nom, String prenom, Date dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
	}

	public Preteur() {

	}

	public Set<Exemplaire> getListExemplaire() {
		return listExemplaire;
	}

	public void setListExamplaire(Set<Exemplaire> listExemplaire) {
		this.listExemplaire = listExemplaire;
	}

	public void addExemplaire(Exemplaire exemplaire) {
		this.listExemplaire.add(exemplaire);
	}

	public void remoteExemplaire(Exemplaire exemplaire) {
		this.listExemplaire.remove(exemplaire);
	}

	@Override
	public String toString() {
		return "Preteur [listExemplaire=" + listExemplaire + "]";
	}

}
