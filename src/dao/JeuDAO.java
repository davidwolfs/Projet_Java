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
	public boolean create(Jeu jeu) {
		java.util.Date date = new java.util.Date();
		date = jeu.getDateTarif();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Jeu (Nom, Dispo, Tarif, DateTarif, AdapterTarif) VALUES ('" + jeu.getNom() + "','" + jeu.isDispo() + "','" + jeu.getTarif() + "','" + new Timestamp(date.getTime()) + "','" + jeu.getAdapterTarif() + "')" + ";";
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
		System.out.println("LIGNE JEU : " + jeu);
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
		System.out.println("Mon objet depuis la méthode update : " + jeu);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Jeu SET Nom = " + "'" + jeu.getNom() + "', " + "Dispo = "
					+ jeu.isDispo() + ", " + "Tarif = " + jeu.getTarif() + ", " + "DateTarif = " 
					+ "'" + new Timestamp(jeu.getDateTarif().getTime()) + "', " 
					+ "AdapterTarif = " + "'" + jeu.getAdapterTarif() + "'"
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

	@Override
	public Jeu find(int id) {
		// TODO Auto-generated method stub
		return null;
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
						result.getInt("Tarif"), result.getDate("DateTarif"), result.getString("AdapterTarif"), console);
				listJeux.add(jeu);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listJeux;
	}
	
	public List<Jeu> findAllAvailable(){
		List<Jeu> listJeux = new ArrayList<>();
		Jeu jeu = new Jeu();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Jeu INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu INNER JOIN Console ON Ligne_Jeu.ID_Console = Console.ID WHERE Dispo = TRUE");
			while(result.next())
			{
				Console console = new Console();
				console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("Console.NOM"));
				jeu = new Jeu(result.getInt("ID"), result.getString("Jeu.Nom"), result.getBoolean("Dispo"),
						result.getInt("Tarif"), result.getDate("DateTarif"), result.getString("AdapterTarif"), console);
				listJeux.add(jeu);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listJeux;
	}
	
	public List<Jeu> findAll_LigneJeu(){
		List<Jeu> listJeux = new ArrayList<>();
		Jeu jeu = new Jeu();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Jeu INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.IDJeu INNER JOIN Console ON Console.ID = Ligne_Jeu.IDJeu");
			while(result.next())
			{
				jeu = new Jeu(result.getInt("ID"), result.getString("Nom"), result.getBoolean("Dispo"),
						result.getInt("Tarif"), result.getDate("DateTarif"), result.getString("AdapterTarif"));
				listJeux.add(jeu);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listJeux;
	}

	public int findLastIdJeu(){
		int lastID=0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Jeu ORDER BY ID DESC");
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
	
	public boolean alreadyExist(Jeu jeu)
	{
		boolean existe = false;
		try{
			String sql = "SELECT * FROM Console INNER JOIN (Jeu INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console WHERE Jeu.Nom = " + "'" + jeu.getNom() + "'" + " AND Console.Nom = " + "'" + jeu.getConsole().getNom() + "'";
			if(jeu.getId()>0) {
				sql = sql + " AND Jeu.ID != " + jeu.getId();
			}
			System.out.println(sql);
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if(result.first())
			{
				existe = true;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return existe;
	}
}
