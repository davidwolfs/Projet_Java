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

	public void create_Exemplaire(Exemplaire exemplaire, Preteur preteur, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		exemplaireDAO.create_Exemplaire(exemplaire, preteur);
	}
	
	public void update(Exemplaire exemplaire, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		exemplaireDAO.update(exemplaire);
	}
	
	public List<Exemplaire> findAll(Preteur preteur, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		List<Exemplaire> listExemplaire = exemplaireDAO.findAll(preteur);
		
		return listExemplaire;
	}
	
	public Exemplaire findExemplaireByIdJeu(Jeu jeu, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		List<Exemplaire> listExemplaire = exemplaireDAO.findAll();
		Exemplaire exemplaire = new Exemplaire();
		
		for(Exemplaire e : listExemplaire)
		{
			if(e.getJeu().getId() == jeu.getId())
			{
				exemplaire.setId(e.getId());
			}
		}
		
		return exemplaire;
	}
	
	public int getNombreExemplaireJeu(Jeu jeu, Preteur preteur, Connection connect)
	{
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		List<Exemplaire> listExemplaires = exemplaireDAO.findAll(preteur);
		
		return listExemplaires.size();
	}
	
	public boolean isLastExemplaire(Jeu jeu, Preteur preteur, Connection connect)
	{
		boolean lastExemplaire = false;
		ExemplaireDAO exemplaireDAO = new ExemplaireDAO(connect);
		List<Exemplaire> listExemplaires = exemplaireDAO.findAll(preteur);
		
		int element = 0;
		
		for(Exemplaire e : listExemplaires)
		{
			element++;
		}
		
		if(element == 1)
		{
			lastExemplaire = true;
		}
		
		return lastExemplaire;
	}
	
	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", nbrExemplaire=" + nbrExemplaire + ", jeu=" + jeu + "]";
	}

}
