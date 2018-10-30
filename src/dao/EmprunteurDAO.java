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
		date = emprunteur.getDateNaiss();
		System.out.println(new Timestamp(date.getTime()));

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Emprunteur (Nom, Prenom, DateNaiss,  Email, Password, Unite) VALUES ('"
					+ emprunteur.getNom() + "','" + emprunteur.getPrenom() + "','" + new Timestamp(date.getTime())
					+ "','" + emprunteur.getEmail() + "','" + emprunteur.getPassword() + "','" + emprunteur.getUnite()
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

	@Override
	public Emprunteur find(int id) {
		// TODO Auto-generated method stub
		return null;
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
				emprunteur = new Emprunteur(result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), email, password);
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	public Emprunteur findEmprunteurByEmailPassword(String email, String password) {
		Emprunteur emprunteur = new Emprunteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + email + "\" AND Password = " + "\""
							+ password + "\"");
			if (result.first()) {
				emprunteur = new Emprunteur(result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), email, password);
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
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur");
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
	
	public boolean alreadyExist(String email) {
		boolean existe = false;
		Emprunteur emprunteur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + email + "\"");
			if (result.first()) {
				emprunteur = new Emprunteur(result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), email, result.getString("Password"));
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
}
