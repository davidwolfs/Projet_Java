package exo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AdministrateurDAO;
import dao.JeuDAO;

public class Administrateur extends Personne {
	private Connection connect;

	public Administrateur(int id, String nom, String prenom, Date date, String email, String password) {
		super(id, nom, prenom, date, email, password);
	}

	public Administrateur(String nom, String prenom, Date date, String email, String password) {
		super(nom, prenom, date, email, password);
	}

	public Administrateur() {
		super();
	}
	
	public void create(Administrateur administrateur, Connection connect)
	{
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		administrateurDAO.create(administrateur);
	}
	
	public void update(Administrateur administrateur, Connection connect)
	{
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		administrateurDAO.update(administrateur);
	}
	
	public void delete(Administrateur administrateur, Connection connect)
	{
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		administrateurDAO.delete(administrateur);
	}
	
	public List<Administrateur> findAll(Connection connect)
	{
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();
		
		for(Administrateur admin : listAdministrateurs)
		{
			this.setNom(admin.getNom());
			this.setPrenom(admin.getPrenom());
			this.setDateNaiss(admin.getDateNaiss());
			this.setEmail(admin.getEmail());
		}
		
		return listAdministrateurs;
	}
	
	public boolean findByEmailPassword(String email, String password, Connection connect) {
		boolean existe = false;
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();
		Administrateur administrateur = new Administrateur();
		
		
		for(Administrateur admin : listAdministrateurs)
		{
			if(admin.getEmail().equals(email) && admin.getPassword().equals(password))
			{
				administrateur = new Administrateur(admin.getiD(), admin.getNom(),
						admin.getPrenom(), admin.getDateNaiss(), email, password);
				existe = true;
			}
		}
	
		return existe;
	}
	
	public Administrateur findAdministrateurByEmailPassword(String email, String password, Connection connect) {
		Administrateur administrateur = new Administrateur();
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();
		
		for(Administrateur admin : listAdministrateurs)
		{
			if(admin.getEmail().equals(email) && admin.getPassword().equals(password))
			{
				administrateur = new Administrateur(admin.getiD(), admin.getNom(),
						admin.getPrenom(), admin.getDateNaiss(), email, password);
			}
		}
		return administrateur;
	}
	
	public boolean alreadyExist(Administrateur administrateur, Connection connect) {
		boolean existe = false;
		AdministrateurDAO administrateurDAO = new AdministrateurDAO(connect);
		List<Administrateur> listAdministrateurs = administrateurDAO.findAll();
		
		for(Administrateur admin : listAdministrateurs)
		{
			if(admin.getiD() > 0)
			{
				if(admin.getEmail().equals(administrateur.getEmail()) && admin.getiD() != administrateur.getiD())
				{
					existe = true;
				}
			}
			else
			{
				if(admin.getEmail().equals(administrateur.getEmail()))
				{
					existe = true;
				}
			}
		}
		
		return existe;
	}
}
