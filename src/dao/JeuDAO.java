package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exo.Administrateur;
import exo.Console;
import exo.Jeu;

public class JeuDAO extends DAO<Jeu>{

	public JeuDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean create(Jeu obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean create(Jeu jeu, Administrateur currentAdministrateur) {
		java.util.Date date = new java.util.Date();
		date = jeu.getDateTarif();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Jeu (Nom, Dispo, Tarif, DateTarif, IDAdministrateur) VALUES ('" + jeu.getNom() + "','" + jeu.isDispo() + "','" + jeu.getTarif() + "','" + new Timestamp(date.getTime()) + "','" + currentAdministrateur.getiD() + "')" + ";";
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
	
	public boolean create_Ligne_Jeu(Jeu jeu) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Ligne_Jeu (Id_Jeu, Id_Console) VALUES ('" + jeu.getId() + "','" + jeu.getConsole().getId() + "')" + ";";
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
	public boolean delete(Jeu jeu) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Jeu WHERE ID = " + jeu.getId() + ";";
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
	public boolean update(Jeu jeu) {
		java.util.Date date = new java.util.Date();
		date = jeu.getDateTarif();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Jeu SET Nom = " + "'" + jeu.getNom() + "', " + "Dispo = "
					+ jeu.isDispo() + ", " + "Tarif = " + jeu.getTarif() + ", " + "DateTarif = " 
					+ "'" + new Timestamp(date.getTime()) + "'" 
					+ " WHERE ID = " + jeu.getId() + ";";
			System.out.println(query);
			statementResult = true;
			statementResult = statement.execute(query);
			
			statement = connect.createStatement();
			query = "UPDATE Ligne_Jeu SET ID_Console = " + jeu.getConsole().getId() + " WHERE ID_Jeu = " + jeu.getId();
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

	public boolean update_Dispo(Jeu jeu) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Jeu SET Dispo = TRUE" +  " WHERE ID = " + jeu.getId() + ";";
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
	
	public boolean update_Dispo_False(Jeu jeu) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Jeu SET Dispo = FALSE" +  " WHERE ID = " + jeu.getId() + ";";
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
	public Jeu find(int id) {
		Jeu jeu = new Jeu();
		Console console = new Console();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT Jeu.ID AS IDJEU, Jeu.Nom AS NOMJEU, Dispo, Tarif, DateTarif, Console.ID AS IDCONSOLE, Console.Nom AS NOMCONSOLE FROM Console INNER JOIN (Jeu INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console WHERE Jeu.ID = " + id);
			if(result.first())
			{
				console = new Console();
				console.setId(result.getInt("IDCONSOLE"));
				console.setNom(result.getString("NOMCONSOLE"));
				jeu = new Jeu(result.getInt("IDJEU"), result.getString("NOMJEU"), result.getBoolean("Dispo"),
						result.getInt("Tarif"), result.getDate("DateTarif"), console);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return jeu;
	}
	
	//TODO 
	public List<Jeu> findAll(){
		List<Jeu> listJeux = new ArrayList<>();
		Jeu jeu = new Jeu();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Jeu INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu INNER JOIN Console ON Ligne_Jeu.ID_Console = Console.ID");
			while(result.next())
			{
				Console console = new Console();
				console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("Console.NOM"));
				jeu = new Jeu(result.getInt("ID"), result.getString("Jeu.NOM"), result.getBoolean("Dispo"),
						result.getInt("Tarif"), result.getDate("DateTarif"), console);
				listJeux.add(jeu);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listJeux;
	}

	public List<Jeu> findAllJeu(){
		List<Jeu> listJeux = new ArrayList<>();
		Jeu jeu = new Jeu();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Jeu");
			while(result.next())
			{
				jeu = new Jeu(result.getInt("ID"), result.getString("Jeu.NOM"), result.getBoolean("Dispo"),
						result.getInt("Tarif"), result.getDate("DateTarif"));
				listJeux.add(jeu);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listJeux;
	}
}
