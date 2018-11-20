package exo;

import java.sql.Connection;
import java.util.Date;

public class Reservation {
	private int id;
	private Date dateReservation;
	private Connection connect;
	private Jeu jeu;

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

	@Override
	public String toString() {
		return "Reservation [dateReservation=" + dateReservation + ", connect=" + connect + ", jeu=" + jeu + "]";
	}

	/*
	 * public void create(Reservation reservation) { ReservationDAO reservationDAO =
	 * new ReservationDAO(connect); reservationDAO.createReservation(reservation); }
	 */

}