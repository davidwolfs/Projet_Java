package exo;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import dao.ConsoleDAO;
import dao.JeuDAO;

public class Jeu {
	private Connection connect;
	private int id;
	private String nom;
	private boolean dispo;
	private double tarif;
	private Date dateTarif;
	private Console console;

	public Jeu(int id, String nom, boolean dispo, double tarif, Date dateTarif, Console console) {
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
		this.console = console;
	}

	public Jeu(int id, String nom, boolean dispo, double tarif, Date dateTarif) {
		this.id = id;
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
	}

	public Jeu(String nom, boolean dispo, double tarif, Date dateTarif, Console console) {
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
		this.console = console;
	}

	public Jeu(String nom, boolean dispo, double tarif, Date dateTarif) {
		this.nom = nom;
		this.dispo = dispo;
		this.tarif = tarif;
		this.dateTarif = dateTarif;
	}

	public Jeu() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Console getConsole() {
		return console;
	}

	public void setConsole(Console console) {
		this.console = console;
	}

	public void adapterTarif(double tarif) {
		this.tarif -= tarif;
	}

	public void create(Jeu jeu, Administrateur administrateur, Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		jeuDAO.create(jeu, administrateur);
	}
	
	public void create_Ligne_Jeu(Jeu jeu, Administrateur administrateur, Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		jeuDAO.create_Ligne_Jeu(jeu);
	}
	
	public void update(Jeu jeu, Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		jeuDAO.update(jeu);
	}
	
	public void update_Dispo(Jeu jeu, Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		jeuDAO.update_Dispo(jeu);
	}
	
	public void update_Dispo_False(Jeu jeu, Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		jeuDAO.update_Dispo_False(jeu);
	}
	
	public void delete(Jeu jeu, Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		jeuDAO.delete(jeu);
	}
	
	public List<Jeu> findAll(Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeux = jeuDAO.findAll();
		
		return listJeux;
	}
	
	public List<Jeu> findAllAvailable(Connection connect)
	{
		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeux = jeuDAO.findAll();
		
		for(Jeu j : listJeux)
		{
			if(j.isDispo())
			{
				Console console = new Console();
				console.setId(j.getConsole().getId());
				console.setNom(j.getConsole().getNom());
				this.setId(j.getId());
				this.setNom(j.getNom());
				this.setDispo(j.isDispo());
				this.setTarif(j.getTarif());
				this.setDateTarif(j.getDateTarif());
			}
		}
		
		return listJeux;
	}

	public int findLastIdJeu(Connection connect)
	{
		int lastID = 0;
		JeuDAO jeuDAO = new JeuDAO(connect);
		List<Jeu> listJeux = jeuDAO.findAllJeu();
		
		for(Jeu jeu : listJeux)
		{
			jeu = listJeux.get(listJeux.size()-1);
			lastID = jeu.getId();
		}
		
		return lastID;
	}
	
	public boolean alreadyExist(Jeu jeu, Connection connect)
	{
		boolean existe = false;
		JeuDAO jeuDAO = new JeuDAO(connect);
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		List<Jeu> listJeux = jeuDAO.findAll();
		List<Console> listConsoles = consoleDAO.findAll();
		
		for(Jeu j : listJeux)
		{
			for(Console c : listConsoles)
			{
				if(j.getId() > 0)
				{
					if(j.getNom().equals(jeu.getNom()) && c.getNom().equals(j.getConsole().getNom()) && j.getId() != jeu.getId())
					{
						existe = true;
					}
				}
				else
				{
					if(j.getNom().equals(jeu.getNom()) && c.getNom().equals(j.getConsole().getNom()))
					{
						existe = true;
					}
				}
			}
		}
		
		return existe;
	}
	
	@Override
	public String toString() {
		return "Jeu [connect=" + connect + ", id=" + id + ", nom=" + nom + ", dispo=" + dispo + ", tarif=" + tarif
				+ ", dateTarif=" + dateTarif + ", console=" + console + "]";
	}

}
