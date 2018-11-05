package dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exo.Console;
import exo.Emprunteur;
import exo.Exemplaire;
import exo.Jeu;
import exo.Pret;
import exo.Reservation;

public class PretDAO extends DAO<Pret>{

	public PretDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean create_Pret(Pret pret, Emprunteur emprunteur)
	{
		java.util.Date dateDebut = new java.util.Date();
		java.util.Date dateFin = new java.util.Date();
		dateDebut = pret.getDateDebut();
		dateFin = pret.getDateFin();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Pret (DateDebut, DateFin, Confirmer_Pret, IDEmprunteur, IDPreteur, IDExemplaire) VALUES ('" +
					new Timestamp(dateDebut.getTime()) + "','" +  new Timestamp(dateFin.getTime()) + "','" + "FALSE" + "','" + emprunteur.getiD() + "','" + emprunteur.getiD() + "','" + 1 + "')" + ";";
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
	public boolean delete(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pret find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pret> findAll(){
		List<Pret> listPret = new ArrayList<>();
		Reservation reservation;
		Emprunteur emprunteur;
		Exemplaire exemplaire;
		Jeu jeu;
		Console console;
		Pret pret;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT ID_Console, ID_Reservation, DateReservation, Console.Nom AS NOMCONSOLE, Ligne_Jeu.ID_Jeu, Jeu.Nom AS NOMJEU, Dispo, Tarif, DateTarif, AdapterTarif, DateDebut, DateFin, Confirmer_Pret, Emprunteur.ID AS IDEMPRUNTEUR, Emprunteur.Nom AS NOMEMPRUNTEUR, Emprunteur.Prenom AS PRENOMEMPRUNTEUR, Emprunteur.DateNaiss AS DATENAISSEMPRUNTEUR, Emprunteur.Email AS EMAILEMPRUNTEUR, Emprunteur.Password AS PASSWORDEMPRUNTEUR, Emprunteur.Unite AS UNITEEMPRUNTEUR FROM (Emprunteur INNER JOIN (Reservation INNER JOIN ((Jeu INNER JOIN (Console INNER JOIN Ligne_Jeu ON Console.ID = Ligne_Jeu.ID_Console) ON Jeu.ID = Ligne_Jeu.ID_Jeu) INNER JOIN Ligne_Reservation ON Jeu.ID = Ligne_Reservation.ID_Jeu) ON Reservation.ID = Ligne_Reservation.ID_Reservation) ON Emprunteur.ID = Reservation.IDEmprunteur) INNER JOIN Pret ON (Console.ID = Pret.ID) AND (Emprunteur.ID = Pret.IDEmprunteur)");
			while(result.next())
			{
				reservation = new Reservation();
				emprunteur = new Emprunteur();
				jeu = new Jeu();
				console = new Console();
				pret = new Pret();
				exemplaire = new Exemplaire();
				console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("NOMCONSOLE"));
				jeu.setId(result.getInt("ID_Jeu"));
				jeu.setNom(result.getString("NOMJEU"));
				jeu.setDispo(result.getBoolean("Dispo"));
				jeu.setTarif(result.getDouble("Tarif"));
				jeu.setDateTarif(result.getDate("DateTarif"));
				jeu.setAdapterTarif(result.getString("AdapterTarif"));
				jeu.setConsole(console);
				exemplaire.setJeu(jeu);
				reservation.setId(result.getInt("ID_Reservation"));
				reservation.setDateReservation(result.getDate("DateReservation"));
				reservation.setJeu(jeu);
				emprunteur.setiD(result.getInt("IDEMPRUNTEUR"));
				emprunteur.setNom(result.getString("NOMEMPRUNTEUR"));
				emprunteur.setPrenom(result.getString("PRENOMEMPRUNTEUR"));
				emprunteur.setDateNaiss(result.getDate("DATENAISSEMPRUNTEUR"));
				emprunteur.setEmail(result.getString("EMAILEMPRUNTEUR"));
				emprunteur.setPassword(result.getString("PASSWORDEMPRUNTEUR"));
				emprunteur.setUnite(result.getInt("UNITEEMPRUNTEUR"));
				emprunteur.setReservation(reservation);
				pret.setDateDebut(result.getDate("DateDebut"));
				pret.setDateFin(result.getDate("DateFin"));
				pret.setConfirmer_pret(result.getBoolean("Confirmer_Pret"));
				pret.setExemplaire(exemplaire);
				pret.setEmprunteur(emprunteur);
				listPret.add(pret);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listPret;
	}
	
}
