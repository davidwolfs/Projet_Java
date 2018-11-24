package exo;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import dao.JeuDAO;
import dao.ReservationDAO;

public class Reservation {
	private int id;
	private Date dateReservation;
	private Jeu jeu;
	private Connection connect;
	
	public Reservation() {

	}

	public Reservation(int id, Date dateReservation, Jeu jeu) {
		this.id = id;
		this.dateReservation = dateReservation;
		this.jeu = jeu;
	}

	public Reservation(int id, Date dateReservation) {
		this.id = id;
		this.dateReservation = dateReservation;
	}

	public Reservation(Date dateReservation, Jeu jeu) {
		this.dateReservation = dateReservation;
		this.jeu = jeu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public void createReservation(Reservation reservation, Emprunteur emprunteur, Connection connect)
	{
		ReservationDAO reservationDAO = new ReservationDAO(connect);
		reservationDAO.createReservation(reservation, emprunteur);
	}
	
	public void create_Ligne_Reservation(Reservation reservation, Jeu jeu, Connection connect)
	{
		ReservationDAO reservationDAO = new ReservationDAO(connect);
		reservationDAO.create_Ligne_Reservation(reservation, jeu);
	}
	
	public int findLastIdReservation(Connection connect)
	{
		int lastID = 0;
		ReservationDAO reservationDAO = new ReservationDAO(connect);
		List<Reservation> listReservations = reservationDAO.findAll();
		
		for(Reservation reservation : listReservations)
		{
			reservation = listReservations.get(listReservations.size()-1);
			lastID = reservation.getId();
		}
		
		return lastID;
	}
	
	@Override
	public String toString() {
		return "Reservation [dateReservation=" + dateReservation + ", connect=" + connect + ", jeu=" + jeu + "]";
	}

	/*
	 * public void create(Reservation reservation) { ReservationDAO reservationDAO =
	 * new ReservationDAO(connect); reservationDAO.createReservation(reservation); }
	 */

}