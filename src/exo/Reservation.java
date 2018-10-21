package exo;

import java.util.Date;

public class Reservation {
	private Date dateReservation;

	public Reservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	@Override
	public String toString() {
		return "Reservation [dateReservation=" + dateReservation + "]";
	}

}