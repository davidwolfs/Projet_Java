package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import exo.Administrateur;

public class AdministrateurDAO extends DAO<Administrateur> {

	public AdministrateurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Administrateur administrateur) {
		java.util.Date date = new java.util.Date();
		date = administrateur.getDateNaiss();
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Administrateur (Nom, Prenom, DateNaiss, Email, Password) VALUES ('"
					+ administrateur.getNom() + "','" + administrateur.getPrenom() + "','"
					+ new Timestamp(date.getTime()) + "','" + administrateur.getEmail() + "','"
					+ administrateur.getPassword() + "')" + ";";
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
	public boolean delete(Administrateur administrateur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Administrateur WHERE ID = " + administrateur.getiD() + ";";
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
	public boolean update(Administrateur administrateur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Administrateur SET Nom = " + "'" + administrateur.getNom() + "', " + "Prenom = "
					+ "'" + administrateur.getPrenom() + "', " + "DateNaiss = " + "'"
					+ new Timestamp(administrateur.getDateNaiss().getTime()) + "', " + "Email = " + "'"
					+ administrateur.getEmail() + "', " + "Password = " + "'" + administrateur.getPassword() + "'"
					+ " WHERE ID = " + administrateur.getiD() + ";";
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
	public Administrateur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Administrateur> findAll() {
		List<Administrateur> listAdministrateurs = new ArrayList<>();
		Administrateur administrateur = new Administrateur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Administrateur");
			while (result.next()) {
				administrateur = new Administrateur(result.getInt("ID"), result.getString("Nom"),
						result.getString("Prenom"), result.getDate("DateNaiss"), result.getString("Email"),
						result.getString("Password"));
				listAdministrateurs.add(administrateur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listAdministrateurs;
	}
}
