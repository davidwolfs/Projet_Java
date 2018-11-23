package exo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AdministrateurDAO;
import dao.EmprunteurDAO;
import dao.PreteurDAO;

public class Preteur extends Joueur {
	private Connection connect;
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

	public void create(Preteur preteur, Connection connect)
	{
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		preteurDAO.create(preteur);
	}
	
	public void update(Preteur preteur, Connection connect)
	{
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		preteurDAO.update(preteur);
	}
	
	public void delete(Preteur preteur, Connection connect)
	{
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		preteurDAO.delete(preteur);
	}
	
	public Preteur find(int id, Connection connect)
	{
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		Preteur preteur = preteurDAO.find(id);
		
		return preteur;
	}
	
	public List<Preteur> findAll(Connection connect)
	{
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		List<Preteur> listPreteurs = preteurDAO.findAll();
		
		for(Preteur p : listPreteurs)
		{
			this.setNom(p.getNom());
			this.setPrenom(p.getPrenom());
			this.setDateNaiss(p.getDateNaiss());
			this.setEmail(p.getEmail());
		}
		
		return listPreteurs;
	}
	
	public boolean findByEmailPassword(String email, String password, Connection connect) {
		boolean existe = false;
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		List<Preteur> listPreteurs = preteurDAO.findAll();
		Preteur preteur = new Preteur();
		
		
		for(Preteur p : listPreteurs)
		{
			if(p.getEmail().equals(email) && p.getPassword().equals(password))
			{
				preteur = new Preteur(p.getiD(), p.getNom(),
						p.getPrenom(), p.getDateNaiss(), email, password);
				existe = true;
			}
		}
	
		return existe;
	}
	
	public Preteur findPreteurByEmailPassword(String email, String password, Connection connect) {
		Preteur preteur = new Preteur();
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		List<Preteur> listPreteurs = preteurDAO.findAll();
		
		for(Preteur p : listPreteurs)
		{
			if(p.getEmail().equals(email) && p.getPassword().equals(password))
			{
				preteur = new Preteur(p.getiD(), p.getNom(),
						p.getPrenom(), p.getDateNaiss(), email, password);
			}
		}
		return preteur;
	}
	
	public boolean alreadyExist(Preteur preteur, Connection connect) {
		boolean existe = false;
		PreteurDAO preteurDAO = new PreteurDAO(connect);
		List<Preteur> listPreteurs = preteurDAO.findAll();
		
		for(Preteur p : listPreteurs)
		{
			if(p.getiD() > 0)
			{
				if(p.getEmail().equals(preteur.getEmail()) && p.getiD() != preteur.getiD())
				{
					existe = true;
				}
			}
			else
			{
				if(p.getEmail().equals(p.getEmail()))
				{
					existe = true;
				}
			}
		}
		System.out.println("methode already exist emprunteur");
		
		return existe;
	}
	
	@Override
	public String toString() {
		return "Preteur [listExemplaire=" + listExemplaire + ", cote=" + cote + "]";
	}

}
