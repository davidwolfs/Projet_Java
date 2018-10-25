package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import exo.Administrateur;

public class AdministrateurDAO extends DAO<Administrateur> {

	public AdministrateurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Administrateur administrateur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Administrateur (Nom, Prenom, DateNaiss, Email, Password) VALUES ('"
					+ administrateur.getNom() + "','" + administrateur.getPrenom() + "','" + "23-10-18" + "','"
					+ administrateur.getEmail() + "','" + administrateur.getPassword() + "')" + ";";
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
	public boolean delete(Administrateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Administrateur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Administrateur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean findByEmailPassword(String email, String password, Connection connect) {
		boolean existe = false;
		Administrateur administrateur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Administrateur WHERE Email = " + "\"" + email + "\" AND Password = "
							+ "\"" + password + "\"");
			if (result.first()) {
				administrateur = new Administrateur(result.getString("Nom"), result.getString("Prenom"),
						result.getString("DateNaiss"), email, password, connect);
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	public Administrateur findAdministrateurByEmailPassword(String email, String password, Connection connect){
		Administrateur administrateur = new Administrateur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Administrateur WHERE Email = " + "\"" + email + "\" AND Password = " + "\"" + password + "\"");
			if(result.first())
			{
				administrateur = new Administrateur(result.getString("Nom"), result.getString("Prenom"),
						result.getString("DateNaiss"), email, password, connect);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return administrateur;
	}
}
