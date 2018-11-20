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
		/*
		 * Date maintenant = new Date(); SimpleDateFormat formatDateJour = new
		 * SimpleDateFormat("dd-MM-yy"); String dateFormatee =
		 * formatDateJour.format(maintenant);
		 */
		java.util.Date date = new java.util.Date();
		date = administrateur.getDateNaiss();
		System.out.println(new Timestamp(date.getTime()));
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
		System.out.println("Mon objet depuis la méthode update : " + administrateur);
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

	public boolean findByEmailPassword(String email, String password) {
		boolean existe = false;
		Administrateur administrateur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Administrateur WHERE Email = " + "\"" + email + "\" AND Password = "
							+ "\"" + password + "\"");
			if (result.first()) {
				administrateur = new Administrateur(result.getInt("ID"), result.getString("Nom"),
						result.getString("Prenom"), result.getDate("DateNaiss"), email, password);
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}

	public Administrateur findAdministrateurByEmailPassword(String email, String password) {
		Administrateur administrateur = new Administrateur();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Administrateur WHERE Email = " + "\"" + email + "\" AND Password = "
							+ "\"" + password + "\"");
			if (result.first()) {
				administrateur = new Administrateur(result.getInt("ID"), result.getString("Nom"),
						result.getString("Prenom"), result.getDate("DateNaiss"), email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrateur;
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

	public boolean alreadyExist(Administrateur administrateur) {
		boolean existe = false;
		try {
			String sql = "SELECT * FROM Administrateur WHERE Email = " + "\"" + administrateur.getEmail() + "\"";
			if (administrateur.getiD() > 0) {
				sql = sql + " AND Administrateur.ID != " + administrateur.getiD();
			}
			System.out.println(sql);
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(sql);
			if (result.first()) {
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
}
