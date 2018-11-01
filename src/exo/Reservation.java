package exo;

import java.sql.Connection;
import java.util.Date;

import dao.ReservationDAO;

public class Reservation {
	private Date dateReservation;
	private Connection connect;
	private Jeu jeu;

	public Reservation(Date dateReservation, Jeu jeu) {
		this.dateReservation = dateReservation;
		this.jeu = jeu;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
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