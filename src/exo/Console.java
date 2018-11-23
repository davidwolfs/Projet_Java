package exo;

import java.sql.Connection;
import java.util.List;

import dao.AdministrateurDAO;
import dao.ConsoleDAO;

public class Console {
	private int id;
	private String nom;

	public Console() {

	}

	public Console(String nom) {
		this.nom = nom;
	}

	public Console(int id, String nom) {
		this.id = id;
		this.nom = nom;
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

	public void create(Console Console, Connection connect)
	{
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		consoleDAO.create(Console);
	}
	
	public void update(Console Console, Connection connect)
	{
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		consoleDAO.update(Console);
	}
	
	public void delete(Console console, Connection connect)
	{
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		consoleDAO.delete(console);
	}
	
	public List<Console> findAll(Connection connect)
	{
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		List<Console> listConsoles = consoleDAO.findAll();
		
		for(Console c : listConsoles)
		{
			this.setId(c.getId());
			this.setNom(c.getNom());
			listConsoles.add(c);
		}
		
		return listConsoles;
	}
	
	public boolean alreadyExist(Console console, Connection connect) {
		boolean existe = false;
		ConsoleDAO consoleDAO = new ConsoleDAO(connect);
		List<Console> listConsoles = consoleDAO.findAll();
		
		for(Console c : listConsoles)
		{
			if(c.getId() > 0)
			{
				if(c.getNom().equals(console.getNom()) && c.getId() != console.getId())
				{
					existe = true;
				}
			}
			else
			{
				if(c.getNom().equals(console.getNom()))
				{
					existe = true;
				}
			}
		}
		
		return existe;
	}
	
	@Override
	public String toString() {
		return "Console [nom=" + nom + "]";
	}

}
