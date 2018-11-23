package exo;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dao.AdministrateurDAO;
import dao.EmprunteurDAO;
import dao.PreteurDAO;

public class Emprunteur extends Joueur {
	private Connection connect;
	private int unite;
	private double cote;
	private int nbrCote = 0;
	private Reservation reservation;

	public Emprunteur(int cote) {
		this.unite = 10;
		this.cote = cote;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = 10;
	}

	public Emprunteur(String nom, String prenom, Date dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
		this.unite = 10;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite, Reservation reservation) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
		this.reservation = reservation;
	}
	
	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
	}
	
	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password, int unite, double cote, int nbrCote) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
		this.cote=cote;
		this.nbrCote=nbrCote;
	}

	public Emprunteur(int id, String nom, String prenom, Date dateNaiss, String email, String password,
			Date date_en, int unite, int cote) {
		super(id, nom, prenom, dateNaiss, email, password);
		this.unite = unite;
		this.cote = cote;
	}

	public Emprunteur(String nom, String prenom, Date dateNaiss, String email, String password, int unite) {
		super(nom, prenom, dateNaiss, email, password);
		this.unite = unite;
	}

	
	public Emprunteur() {
		super();
		this.unite = 10;
	}

	public int getUnite() {
		return unite;
	}

	public void setUnite(int unite) {
		this.unite = unite;
	}

	public double getCote() {
		return cote;
	}

	public void setCote(double cote) {
		this.cote = cote;
	}

	
	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
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

	public void ajouterUnite(int unite) {
		
		this.unite += unite;
	}
	
	public void soustraireUnite(int unite) {
		this.unite -= unite;
	}

	public void AjouterCote(int cote) {
		this.cote += cote;
	}

	@Override
	public double CalculerMoyenneCote()
	{
		double moyenneCote = 0.0;
		
		// On déclenche une exception si on a une division par zéro
		try
		{
			if(nbrCote == 0)
			{
				moyenneCote = 0.0;
			}
			else
			{
				moyenneCote = (cote / nbrCote);
				
			}
			System.out.println(moyenneCote);
		}
		catch(ArithmeticException ex)
		{
			ex.getMessage();
		}
		
		return moyenneCote;
	}
	
	public void create(Emprunteur emprunteur, Connection connect)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.create(emprunteur);
	}

	public void updateCote_NombreCote(Emprunteur emprunteur, Connection connect)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.updateCote_NombreCote(emprunteur);
	}
	
	public void update(Emprunteur emprunteur, Connection connect)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.update(emprunteur);
	}
	
	public void updateUnite(Emprunteur emprunteur, Connection connect)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.updateUnite(emprunteur);
	}
	
	public void delete(Emprunteur emprunteur, Connection connect)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		emprunteurDAO.delete(emprunteur);
	}
	
	public List<Emprunteur> findAll(Connection connect)
	{
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		
		for(Emprunteur e : listEmprunteurs)
		{
			this.setNom(e.getNom());
			this.setPrenom(e.getPrenom());
			this.setDateNaiss(e.getDateNaiss());
			this.setEmail(e.getEmail());
		}
		
		return listEmprunteurs;
	}
	
	public boolean findByEmailPassword(String email, String password, Connection connect) {
		boolean existe = false;
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		Emprunteur emprunteur = new Emprunteur();
		
		
		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getEmail().equals(email) && e.getPassword().equals(password))
			{
				emprunteur = new Emprunteur(e.getiD(), e.getNom(),
						e.getPrenom(), e.getDateNaiss(), email, password);
				existe = true;
			}
		}
	
		return existe;
	}
	
	public Emprunteur findEmprunteurById(Emprunteur emprunteur, Connection connect) {
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		Emprunteur e = emprunteurDAO.find(emprunteur.getiD());
		
		return e;
	}
	
	//TODO
	public Emprunteur findIdByEmprunteur(Emprunteur emprunteur, Connection connect) {
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		Emprunteur e = emprunteurDAO.findIdByEmprunteur(emprunteur);
		
		
		return e;
	}
	
	
	public Emprunteur findEmprunteurByEmailPassword(String email, String password, Connection connect) {
		Emprunteur emprunteur = new Emprunteur();
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		
		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getEmail().equals(email) && e.getPassword().equals(password))
			{
				emprunteur = new Emprunteur(e.getiD(), e.getNom(),
						e.getPrenom(), e.getDateNaiss(), email, password);
			}
		}
		return emprunteur;
	}
	
	public List<Emprunteur> findAllExceptcurrentEmprunteur(Emprunteur emprunteur, Connection connect) {
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAllExceptcurrentEmprunteur(emprunteur);
		
		return listEmprunteurs;
	}
	
	public boolean alreadyExist(Emprunteur emprunteur, Connection connect) {
		boolean existe = false;
		EmprunteurDAO emprunteurDAO = new EmprunteurDAO(connect);
		List<Emprunteur> listEmprunteurs = emprunteurDAO.findAll();
		
		for(Emprunteur e : listEmprunteurs)
		{
			if(e.getiD() > 0)
			{
				if(e.getEmail().equals(emprunteur.getEmail()) && e.getiD() != emprunteur.getiD())
				{
					existe = true;
				}
			}
			else
			{
				if(e.getEmail().equals(emprunteur.getEmail()))
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
		return "Emprunteur [unite=" + unite + ", cote=" + cote + ", reservation=" + reservation + ", iD=" + iD
				+ ", nom=" + nom + ", prenom=" + prenom + ", dateNaiss=" + dateNaiss + ", email=" + email
				+ ", password=" + password + "]";
	}

}
