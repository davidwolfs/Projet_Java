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
	public boolean delete(Preteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Preteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Preteur find(int id) {
		// TODO Auto-generated method stub
		return null;
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
				preteur = new Preteur(result.getString("Nom"), result.getString("Prenom"), result.getDate("DateNaiss"),
						email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preteur;
	}

	public boolean alreadyExist(String email) {
		boolean existe = false;
		Preteur preteur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur WHERE Email = " + "\"" + email + "\"");
			if (result.first()) {
				preteur = new Preteur(result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), email, result.getString("Password"));
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
}
