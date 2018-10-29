package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
			String query = "INSERT INTO Jeu (Nom, Dispo, Tarif, DateTarif, AdapterTarif) VALUES ('" + jeu.getNom() + "','" + jeu.isDispo() + "','" + new Timestamp(date.getTime()) + "','" + jeu.getAdapterTarif() + "')" + ";";
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
	public boolean delete(Jeu obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Jeu obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Jeu find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
