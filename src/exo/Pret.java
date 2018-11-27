package exo;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import dao.PretDAO;

public class Pret {
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private boolean confirmer_pret;
	private Exemplaire exemplaire;
	private Emprunteur emprunteur;
	private Preteur preteur;

	public Pret() {

	}

	public Pret(int id, Date dateDebut, Date dateFin, boolean confirmer_pret, Exemplaire exemplaire,
			Emprunteur emprunteur, Preteur preteur) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.confirmer_pret = confirmer_pret;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.preteur = preteur;

	}

	public Pret(Date dateDebut, Date dateFin, boolean confirmer_pret, Exemplaire exemplaire, Emprunteur emprunteur,
			Preteur preteur) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.confirmer_pret = confirmer_pret;
		this.exemplaire = exemplaire;
		this.emprunteur = emprunteur;
		this.preteur = preteur;

	}

	public Pret(int id, Date dateDebut, Date dateFin, Emprunteur emprunteur) {
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.emprunteur = emprunteur;

	}

	public Pret(Date dateDebut, Date dateFin, Emprunteur emprunteur) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.emprunteur = emprunteur;

	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public boolean isConfirmer_pret() {
		return confirmer_pret;
	}

	public void setConfirmer_pret(boolean confirmer_pret) {
		this.confirmer_pret = confirmer_pret;
	}

	public Exemplaire getExemplaire() {
		return exemplaire;
	}

	public void setExemplaire(Exemplaire exemplaire) {
		this.exemplaire = exemplaire;
	}

	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Preteur getPreteur() {
		return preteur;
	}

	public void setPreteur(Preteur preteur) {
		this.preteur = preteur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void create_Pret(Pret pret, Emprunteur emprunteur, Exemplaire exemplaire, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		pretDAO.create_Pret(pret, emprunteur, exemplaire);
	}
	
	public void update_Pret_Emprunteur(Emprunteur emprunteur, Pret pret, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		pretDAO.update_Pret_Emprunteur(emprunteur, pret);
	}
	
	public void update_Confirmation(Pret pret, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		pretDAO.update_Confirmation(pret);
	}
	
	public void update_Pret_Preteur(Preteur preteur, Pret pret, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		pretDAO.update_Pret_Preteur(preteur, pret);
	}
	
	
	public void delete_Pret_Emprunteur(Pret pret, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		pretDAO.delete_Pret_Emprunteur(pret);
	}
	
	
	
	public List<Pret> findAll(Emprunteur emprunteur, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		List<Pret> listPrets = pretDAO.findAll(emprunteur);
		
		return listPrets;
	}
	
	public List<Pret> findAllPretByEmprunteur(Emprunteur emprunteur, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		List<Pret> listPrets = pretDAO.findAllPretByEmprunteur(emprunteur);
		
		return listPrets;
	}
	
	public boolean ConfirmationEmprunteurDebut() {
		return false;
	}

	public boolean ConfirmationPreteurDebut() {
		return false;
	}

	public boolean ConfirmationEmprunteurFin() {
		return false;
	}

	public boolean ConfirmationPreteurFin() {
		return false;
	}
	
	public boolean isAlreadyConfirmed(Pret pret, Connection connect)
	{
		boolean alreadyConfirmed = false;
		PretDAO pretDAO = new PretDAO(connect);
		alreadyConfirmed = pretDAO.isAlreadyConfirmed(pret);
		
		return alreadyConfirmed;
	}
	
	public boolean sameReservationFound(Exemplaire exemplaire, Connection connect)
	{
		boolean reservationFound = false;
		PretDAO pretDAO = new PretDAO(connect);
		reservationFound = pretDAO.sameReservationFound(exemplaire);
		
		return reservationFound;
	}
	
	public List<Pret> getPretEmprunteurSortByPriorites(Exemplaire exemplaire, Connection connect)
	{
		PretDAO pretDAO = new PretDAO(connect);
		List<Pret> listPret = pretDAO.getPretEmprunteurSortByPriorites(exemplaire);
		
		return listPret;
	}
	
	@Override
	public String toString() {
		return "Pret [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", confirmer_pret="
				+ confirmer_pret + ", exemplaire=" + exemplaire + ", emprunteur=" + emprunteur + ", preteur=" + preteur
				+ "]";
	}

}
