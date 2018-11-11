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

	public boolean create_Pret(Pret pret, Emprunteur emprunteur, Exemplaire exemplaire)
	{
		System.out.println("ID EXEMPLAIRE : " + exemplaire.getId());
		java.util.Date dateDebut = new java.util.Date();
		java.util.Date dateFin = new java.util.Date();
		dateDebut = pret.getDateDebut();
		dateFin = pret.getDateFin();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Pret (DateDebut, DateFin, Confirmer_Pret, IDEmprunteur, IDPreteur, IDExemplaire) VALUES ('" +
					new Timestamp(dateDebut.getTime()) + "','" +  new Timestamp(dateFin.getTime()) + "','" + "FALSE" + "','" + emprunteur.getiD() + "','" + emprunteur.getiD() + "','" + exemplaire.getId() + "')" + ";";
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
	
	public boolean update_Confirmation(Pret pret) {
		System.out.println("Mon objet depuis la méthode update : " + pret);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Pret SET Confirmer_Pret = " + pret.isConfirmer_pret() + " WHERE ID = " + pret.getId() + ";";
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
	public Pret find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pret> findAll(Emprunteur emprunteur){
		List<Pret> listPret = new ArrayList<>();
		Reservation reservation;
		Exemplaire exemplaire;
		Jeu jeu;
		Console console;
		Pret pret;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT Pret.ID AS IDPret, DateDebut, DateFin, Confirmer_Pret, Emprunteur.ID AS IDEMPRUNTEUR, Emprunteur.Nom AS NOMEMPRUNTEUR, Emprunteur.Prenom AS PRENOMEMPRUNTEUR, Emprunteur.DateNaiss AS DATENAISSEMPRUNTEUR, Emprunteur.Email AS EMAILEMPRUNTEUR, Emprunteur.Unite AS UNITEEMPRUNTEUR, Exemplaire.IDJeu AS ID_JEU FROM Exemplaire INNER JOIN (Emprunteur INNER JOIN Pret ON Emprunteur.ID = Pret.IDEmprunteur) ON Exemplaire.ID = Pret.IDExemplaire WHERE Emprunteur.ID <> " + emprunteur.getiD() + " AND Pret.ID <> 24");
			while(result.next())
			{
				reservation = new Reservation();
				emprunteur = new Emprunteur();
				jeu = new Jeu();
				console = new Console();
				pret = new Pret();
				exemplaire = new Exemplaire();
				/*console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("NOMCONSOLE"));*/
				jeu.setId(result.getInt("ID_JEU"));
				JeuDAO jeuDAO = new JeuDAO(connect);
				System.out.println("ID DU JEU : " + jeu.getId());
				jeu = jeuDAO.find(jeu.getId());
				jeu.setNom(jeu.getNom());
				System.out.println("ID JEU : " + jeu.getId());
				/*jeu.setNom(result.getString("NOMJEU"));
				jeu.setDispo(result.getBoolean("Dispo"));
				jeu.setTarif(result.getDouble("Tarif"));
				jeu.setDateTarif(result.getDate("DateTarif"));
				jeu.setAdapterTarif(result.getString("AdapterTarif"));
				jeu.setConsole(console);*/
				exemplaire.setJeu(jeu);
				System.out.println("Exemplaire : " + exemplaire);
				/*reservation.setId(result.getInt("ID_Reservation"));
				reservation.setDateReservation(result.getDate("DateReservation"));
				reservation.setJeu(jeu);*/
				emprunteur.setiD(result.getInt("IDEMPRUNTEUR"));
				emprunteur.setNom(result.getString("NOMEMPRUNTEUR"));
				emprunteur.setPrenom(result.getString("PRENOMEMPRUNTEUR"));
				emprunteur.setDateNaiss(result.getDate("DATENAISSEMPRUNTEUR"));
				emprunteur.setEmail(result.getString("EMAILEMPRUNTEUR"));
				emprunteur.setUnite(result.getInt("UNITEEMPRUNTEUR"));
				emprunteur.setReservation(reservation);
				pret.setId(result.getInt("IDPret"));
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
	
	public boolean sameReservationFound(){
		boolean found = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Pret A INNER JOIN Pret B ON A.IDExemplaire = B.IDExemplaire WHERE A.ID <> B.ID AND A.IDEmprunteur <> B.IDEmprunteur AND B.Confirmer_Pret = False");
			if(result.first())
			{
				found = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return found;
	}
	
	public List<Pret> findAll(){
		List<Pret> listPret = new ArrayList<>();
		Pret pret;
		Emprunteur emprunteur;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT A.ID, A.DateDebut, A.DateFin, A.IDEmprunteur, B.Confirmer_Pret, Emprunteur.Unite FROM (Pret AS A INNER JOIN Pret AS B ON A.IDExemplaire = B.IDExemplaire) INNER JOIN Emprunteur ON A.IDEmprunteur = Emprunteur.ID WHERE A.ID<>B.ID AND A.IDEmprunteur<>B.IDEmprunteur AND B.Confirmer_Pret=False ORDER BY Emprunteur.Unite DESC");
			while(result.next())
			{
				pret = new Pret();
				emprunteur = new Emprunteur();
				pret.setId(result.getInt("ID"));
				pret.setDateDebut(result.getDate("DateDebut"));
				pret.setDateFin(result.getDate("DateFin"));
				pret.setConfirmer_pret(result.getBoolean("Confirmer_Pret"));
				emprunteur.setiD(result.getInt("IDEmprunteur"));
				pret.setEmprunteur(emprunteur);
				listPret.add(pret);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listPret;
	}
	
	public List<Pret> findAllPretByEmprunteur(Emprunteur currentEmprunteur){
		List<Pret> listPret = new ArrayList<>();
		Jeu jeu;
		Console console;
		Pret pret;
		Reservation reservation;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT ID_Reservation, DateReservation, Jeu.Nom AS NOMJEU, Console.Nom AS NOMCONSOLE, DateDebut, DateFin, Confirmer_Pret FROM (Console INNER JOIN ((Jeu INNER JOIN ((Emprunteur INNER JOIN Reservation ON Emprunteur.ID = Reservation.IDEmprunteur) INNER JOIN Ligne_Reservation ON Reservation.ID = Ligne_Reservation.ID_Reservation) ON Jeu.ID = Ligne_Reservation.ID_Jeu) INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console) INNER JOIN Pret ON (Reservation.ID = Pret.ID) AND (Emprunteur.ID = Pret.IDEmprunteur) WHERE Emprunteur.ID = 3");
			while(result.next())
			{
				jeu = new Jeu();
				console = new Console();
				pret = new Pret();
				//console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("NOMCONSOLE"));
				//jeu.setId(result.getInt("ID_Jeu"));
				jeu.setNom(result.getString("NOMJEU"));
				System.out.println("NOM DU JEU  : " + jeu.getNom());
				/*jeu.setDispo(result.getBoolean("Dispo"));
				jeu.setTarif(result.getDouble("Tarif"));
				jeu.setDateTarif(result.getDate("DateTarif"));
				jeu.setAdapterTarif(result.getString("AdapterTarif"));*/
				jeu.setConsole(console);
				pret.setDateDebut(result.getDate("DateDebut"));
				pret.setDateFin(result.getDate("DateFin"));
				pret.setConfirmer_pret(result.getBoolean("Confirmer_Pret"));
				reservation = new Reservation(result.getInt("ID_Reservation"), result.getDate("DateReservation"));
				reservation.setJeu(jeu);
				currentEmprunteur.setReservation(reservation);
				pret.setEmprunteur(currentEmprunteur);
				listPret.add(pret);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listPret;
	}
	
	public boolean isAlreadyConfirmed(Pret pret){
		boolean isAlreadyConfirmed = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM PRET WHERE ID = " + pret.getId() + " AND Confirmer_Pret = TRUE");
			if(result.first())
			{
				isAlreadyConfirmed = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return isAlreadyConfirmed;
	}
}
