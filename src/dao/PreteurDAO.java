package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import exo.Emprunteur;
import exo.Preteur;

public class PreteurDAO extends DAO<Preteur> {

	public PreteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Preteur preteur) {
		java.util.Date date = new java.util.Date();
		java.util.Date currentDate = new java.util.Date();
		date = preteur.getDateNaiss();
		System.out.println(new Timestamp(date.getTime()));
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Preteur (Nom, Prenom, DateNaiss,  Email, Password, Date_en) VALUES ('" + preteur.getNom()
					+ "','" + preteur.getPrenom() + "','" + new Timestamp(date.getTime()) + "','" + preteur.getEmail()
					+ "','" + preteur.getPassword() + "','" + new Timestamp(currentDate.getTime()) + "')" + ";";
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
	public boolean delete(Preteur preteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Preteur WHERE ID = " + preteur.getiD() + ";";
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
	public boolean update(Preteur preteur) {
		System.out.println("Mon objet depuis la m�thode update : " + preteur);
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Preteur SET Nom = " + "'" + preteur.getNom() +  "', " + "Prenom = " + "'" + preteur.getPrenom() + "', " +  "DateNaiss = " + "'" + new Timestamp(preteur.getDateNaiss().getTime()) + "', " + "Email = " + "'" + preteur.getEmail() + "', " + "Password = " + "'" + preteur.getPassword() + "'" + " WHERE ID = " + preteur.getiD() + ";";
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
	public Preteur find(int id) {
		Preteur preteur = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur WHERE ID = " + id);
			if (result.first()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"), result.getDate("DateNaiss"),
						result.getString("Email"), result.getString("Password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preteur;
	}

	public boolean findByEmailPassword(String email, String password) {
		boolean existe = false;
		Preteur preteur = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur WHERE Email = " + "\"" + email + "\" AND Password = " + "\""
							+ password + "\"");
			if (result.first()) {
				preteur = new Preteur(result.getString("Nom"), result.getString("Prenom"), result.getDate("DateNaiss"),
						email, password);
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	public Preteur findPreteurByEmailPassword(String email, String password) {
		Preteur preteur = new Preteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur WHERE Email = " + "\"" + email + "\" AND Password = " + "\""
							+ password + "\"");
			if (result.first()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"), result.getDate("DateNaiss"),
						email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preteur;
	}

	public boolean alreadyExist(Preteur preteur) {
		boolean existe = false;
		try {
			String sql = "SELECT * FROM Preteur WHERE Email = " + "\"" + preteur.getEmail() + "\"";
			if(preteur.getiD()>0) {
				sql = sql + " AND Preteur.ID != " + preteur.getiD();
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
}
