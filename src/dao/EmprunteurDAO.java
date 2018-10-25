package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import exo.Emprunteur;
import exo.Preteur;

public class EmprunteurDAO extends DAO<Emprunteur> {

	public EmprunteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Emprunteur emprunteur) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Emprunteur (Nom, Prenom, DateNaiss,  Email, Password) VALUES ('" + emprunteur.getNom() + "','" + emprunteur.getPrenom() + "','" + "1994-02-18" + "','" + emprunteur.getEmail() + "','" + emprunteur.getPassword() + "')" + ";";
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
	public boolean delete(Emprunteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Emprunteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Emprunteur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean findByEmailPassword(String email, String password, Connection connect) {
		boolean existe = false;
		Emprunteur emprunteur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + email + "\" AND Password = "
							+ "\"" + password + "\"");
			if (result.first()) {
				emprunteur = new Emprunteur(result.getString("Nom"), result.getString("Prenom"),
						result.getString("DateNaiss"), email, password, connect);
				existe = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return existe;
	}
	
	public Emprunteur findEmprunteurByEmailPassword(String email, String password, Connection connect){
		Emprunteur emprunteur = new Emprunteur();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Emprunteur WHERE Email = " + "\"" + email + "\" AND Password = " + "\"" + password + "\"");
			if(result.first())
			{
				emprunteur = new Emprunteur(result.getString("Nom"), result.getString("Prenom"),
						result.getString("DateNaiss"), email, password, connect);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return emprunteur;
	}
}
