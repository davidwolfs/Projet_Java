package exo;

import java.sql.Connection;
import java.util.List;

import dao.ExemplaireDAO;
import dao.JeuDAO;

public class Exemplaire {
	private int id;
	private int nbrExemplaire;
	private Jeu jeu;

	public Exemplaire() {

	}

	public Exemplaire(int id) {
		this.id = id;
	}

	public Exemplaire(int nbrExemplaire, Jeu jeu) {
		this.nbrExemplaire = nbrExemplaire;
		this.jeu = jeu;
	}

	public Exemplaire(Jeu jeu) {
		this.jeu = jeu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrExemplaire() {
		return nbrExemplaire;
	}

	public void setNbrExemplaire(int nbrExemplaire) {
		this.nbrExemplaire = nbrExemplaire;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public void update(Exemplaire exemplaire, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		exemplaireDAO.update(exemplaire);
	}
	
	public Exemplaire findExemplaireByIdJeu(Jeu jeu, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		Exemplaire exemplaire = exemplaireDAO.findExemplaireByIdJeu(jeu);
		
		return exemplaire;
	}
	
	public boolean isLastExemplaire(Jeu jeu, Connection connect)
	{
		boolean lastExemplaire = false;
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		lastExemplaire = exemplaireDAO.isLastExemplaire(jeu);
		
		return lastExemplaire;
	}
	
	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", nbrExemplaire=" + nbrExemplaire + ", jeu=" + jeu + "]";
	}

}
