package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exo.Console;
import exo.Emprunteur;
import exo.Jeu;
import exo.Reservation;

public class ReservationDAO extends DAO<Reservation>{

	public ReservationDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean createReservation(Reservation reservation, Emprunteur emprunteur) {
		java.util.Date date = new java.util.Date();
		date = reservation.getDateReservation();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Reservation (DateReservation, IDEmprunteur) VALUES ('" +
					new Timestamp(date.getTime()) + "','" +  emprunteur.getiD() + "')" + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	public boolean create_Ligne_Reservation(Reservation reservation, Jeu jeu) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Ligne_Reservation (ID_Jeu, ID_Reservation) VALUES ('" + jeu.getId() + "','" +  reservation.getId() + "')" + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
		} catch (SQLException e) {
			statementResult = false;
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(statementResult);
		return statementResult;
	}
	
	@Override
	public boolean delete(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Reservation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Reservation find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Reservation> findAll(){
		List<Reservation> listReservation = new ArrayList<>();
		Reservation reservation;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Console INNER JOIN ((Jeu INNER JOIN (Reservation INNER JOIN Ligne_Reservation ON Reservation.ID = Ligne_Reservation.ID_Reservation) ON Jeu.ID = Ligne_Reservation.ID_Jeu) INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console");
			while(result.next())
			{
				Jeu jeu = new Jeu();
				Console console = new Console();
				console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("Console.Nom"));
				jeu.setId(result.getInt("ID_jeu"));
				jeu.setNom(result.getString("Nom"));
				jeu.setDispo(result.getBoolean("Dispo"));
				jeu.setTarif(result.getDouble("Tarif"));
				jeu.setDateTarif(result.getDate("DateTarif"));
				jeu.setAdapterTarif(result.getString("AdapterTarif"));
				jeu.setConsole(console);
				reservation = new Reservation(result.getInt("ID"), result.getDate("DateReservation"), jeu);
				listReservation.add(reservation);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listReservation;
	}
	
	public int findLastIdReservation(){
		int lastID=0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Reservation ORDER BY ID DESC");
			if(result.first())
			{
				lastID = result.getInt("ID");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return lastID;
	}

}
