package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Preteur (Nom, Prenom, DateNaiss,  Email, Password, Date_en) VALUES ('"
					+ preteur.getNom() + "','" + preteur.getPrenom() + "','" + new Timestamp(date.getTime()) + "','"
					+ preteur.getEmail() + "','" + preteur.getPassword() + "','" + new Timestamp(currentDate.getTime())
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
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Preteur SET Nom = " + "'" + preteur.getNom() + "', " + "Prenom = " + "'"
					+ preteur.getPrenom() + "', " + "DateNaiss = " + "'"
					+ new Timestamp(preteur.getDateNaiss().getTime()) + "', " + "Email = " + "'" + preteur.getEmail()
					+ "', " + "Password = " + "'" + preteur.getPassword() + "'" + " WHERE ID = " + preteur.getiD()
					+ ";";
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

	public boolean updateCote_NombreCote(Preteur preteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Preteur SET Cote = Cote + " + preteur.getCote() + ","
					+ " NombreCote = NombreCote + 1" + " WHERE ID = " + preteur.getiD() + ";";
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
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preteur;
	}

	public List<Preteur> findAll() {
		List<Preteur> listPreteurs = new ArrayList<>();
		Preteur preteur = new Preteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Preteur");
			while (result.next()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), 
						result.getInt("Cote"), result.getInt("NombreCote"));
				listPreteurs.add(preteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPreteurs;
	}

	public boolean marquerPreteursEmprunteursCotes(Preteur preteur, Emprunteur emprunteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Cote (IDPreteur, IDEmprunteur) VALUES (" + preteur.getiD() + ","
					+ emprunteur.getiD() + ")" + ";";
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

	public List<Preteur> findAllCote() {
		List<Preteur> listPreteurs = new ArrayList<>();
		Preteur preteur = new Preteur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur INNER JOIN (Preteur INNER JOIN Cote ON Preteur.ID = Cote.IDPreteur) ON Emprunteur.ID = Cote.IDEmprunteur");
			while (result.next()) {
				preteur = new Preteur(result.getInt("ID"), result.getString("Nom"), result.getString("Prenom"),
						result.getDate("DateNaiss"), result.getString("Email"), result.getString("Password"), 
						result.getInt("Cote"), result.getInt("NombreCote"));
				listPreteurs.add(preteur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPreteurs;
	}
	
	public boolean isAlreadyCote(Preteur preteur, Emprunteur emprunteur) {
		boolean isAlreadyCote = false;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT * FROM Emprunteur INNER JOIN (Preteur INNER JOIN Cote ON Preteur.ID = Cote.IDPreteur) ON Emprunteur.ID = Cote.IDEmprunteur WHERE Preteur.ID = "
									+ preteur.getiD() + " AND Emprunteur.ID = " + emprunteur.getiD());
			if (result.first()) {
				isAlreadyCote = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAlreadyCote;
	}
}
