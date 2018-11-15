package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exo.Administrateur;
import exo.Emprunteur;
import exo.Preteur;

public class EmprunteurDAO extends DAO<Emprunteur> {

	public EmprunteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Emprunteur emprunteur) {
		java.util.Date date = new java.util.Date();
		java.util.Date currentDate = new java.util.Date();
		date = emprunteur.getDateNaiss();
		System.out.println(new Timestamp(date.getTime()));

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Emprunteur (Nom, Prenom, DateNaiss,  Email, Password, Unite, Date_en) VALUES ('"
					+ emprunteur.getNom() + "','" + emprunteur.getPrenom() + "','" + new Timestamp(date.getTime())
					+ "','" + emprunteur.getEmail() + "','" + emprunteur.getPassword() + "','" + emprunteur.getUnite() + "','" 
					+ new Timestamp(currentDate.getTime())
					+ "')" + ";";
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
	public boolean delete(Emprunteur emprunteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Emprunteur WHERE ID = " + emprunteur.getiD() + ";";
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
	public boolean update(Emprunteur emprunteur) {
		System.out.println("Mon objet depuis la méthode update : " + emprunteur);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Emprunteur SET Nom = " + "'" + emprunteur.getNom() +  "', " + "Prenom = " + "'" + emprunteur.getPrenom() + "', " +  "DateNaiss = " + "'" + new Timestamp(emprunteur.getDateNaiss().getTime()) + "', " + "Email = " + "'" + emprunteur.getEmail() + "', " + "Password = " + "'" + emprunteur.getPassword() + "'" + " WHERE ID = " + emprunteur.getiD() + ";";
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
	
	public boolean updateCote_NombreCote(Emprunteur emprunteur) {
		System.out.println("Mon objet depuis la méthode update : " + emprunteur);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Emprunteur SET Cote = Cote + " + emprunteur.getCote()  + "," + " NombreCote = NombreCote + 1" + " WHERE ID = " + emprunteur.getiD() + ";";
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
	
	public boolean updateUnite(Emprunteur emprunteur) {
		System.out.println("Mon objet depuis la méthode update : " + emprunteur.getiD() + " " + emprunteur.getNom() + " " + emprunteur.getPrenom());
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Emprunteur SET Unite = " + emprunteur.getUnite() + " WHERE ID = " + emprunteur.getiD() + ";";
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
	public Emprunteur find(int id) {
		Emprunteur emprunteur = new Emprunteur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur WHERE ID = " + id);
			if(result.first())
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"), result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), result.getDouble("Solde"), result.getDate("Date_en"), result.getInt("Unite"), result.getInt("Cote"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return emprunteur;
		
	}

	public Emprunteur findIdByEmprunteur(Emprunteur emprunteur){
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + emprunteur.getEmail() + "\"");
			if(result.first())
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"), result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), result.getDouble("Solde"), result.getDate("Date_en"), result.getInt("Unite"), result.getInt("Cote"));
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return emprunteur;
	}
	
	public boolean findByEmailPassword(String email, String password) {
		boolean existe = false;
		Emprunteur emprunteur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + email + "\" AND Password = " + "\""
							+ password + "\"");
			if (result.first()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), email, password);
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	public Emprunteur findEmprunteurById(Emprunteur emprunteur) {
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE ID = " + emprunteur.getiD());
			if (result.first()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), result.getInt("Unite"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprunteur;
	}
	
	public Emprunteur findEmprunteurByEmailPassword(String email, String password) {
		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + email + "\" AND Password = " + "\""
							+ password + "\"");
			if (result.first()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), email, password, result.getInt("Unite"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprunteur;
	}

	public List<Emprunteur> findAll(){
		List<Emprunteur> listEmprunteurs = new ArrayList<>();
		Emprunteur emprunteur = new Emprunteur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur WHERE ID <> 19");
			while(result.next())
			{
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), result.getInt("Unite"));
				listEmprunteurs.add(emprunteur);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listEmprunteurs;
	}
	
	public List<Emprunteur> findAllExceptcurrentEmprunteur(Emprunteur emprunteur){
		List<Emprunteur> listEmprunteurs = new ArrayList<>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur WHERE ID <> " + emprunteur.getiD());
			while(result.next())
			{
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), result.getInt("Unite"), result.getInt("Cote"), result.getInt("NombreCote"));
				listEmprunteurs.add(emprunteur);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listEmprunteurs;
	}
	
	public boolean alreadyExist(Emprunteur emprunteur) {
		boolean existe = false;
		try {
			String sql = "SELECT * FROM Emprunteur WHERE Email = " + "\"" + emprunteur.getEmail() + "\"";
			if(emprunteur.getiD()>0) {
				sql = sql + " AND Emprunteur.ID != " + emprunteur.getiD();
			}
			System.out.println(sql);
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(sql);
			if (result.first()) {
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	public List<Emprunteur> getEmprunteurSortByDateRes() {
		List<Emprunteur> listEmprunteur = new ArrayList<>();
 		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur INNER JOIN Reservation ON Emprunteur.ID = Reservation.IDEmprunteur ORDER BY DateReservation");
			while (result.next()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"),  result.getString("Email"), result.getString("Password"));
				listEmprunteur.add(emprunteur);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEmprunteur;
	}
	
	public List<Emprunteur> getEmprunteurSortByDate_en() {
		List<Emprunteur> listEmprunteur = new ArrayList<>();
 		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur ORDER BY Date_en");
			while (result.next()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"),  result.getString("Email"), result.getString("Password"));
				listEmprunteur.add(emprunteur);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEmprunteur;
	}
	
	public List<Emprunteur> getEmprunteurSortByDateNaiss() {
		List<Emprunteur> listEmprunteur = new ArrayList<>();
 		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur ORDER BY DateNaiss");
			while (result.next()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"),  result.getString("Email"), result.getString("Password"));
				listEmprunteur.add(emprunteur);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEmprunteur;
	}
	
	public List<Emprunteur> getEmprunteurSortRandomly() {
		List<Emprunteur> listEmprunteur = new ArrayList<>();
 		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur ORDER BY rnd(ID)");
			while (result.next()) {
				emprunteur = new Emprunteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"),  result.getString("Email"), result.getString("Password"));
				listEmprunteur.add(emprunteur);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listEmprunteur;
	}
	
}
