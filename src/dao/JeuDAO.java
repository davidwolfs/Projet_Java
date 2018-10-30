package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import exo.Administrateur;
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
			String query = "INSERT INTO Jeu (Nom, Dispo, Tarif, DateTarif, AdapterTarif, IDReservation) VALUES ('" + jeu.getNom() + "','" + jeu.isDispo() + "','" + jeu.getTarif() + "','" + new Timestamp(date.getTime()) + "','" + jeu.getAdapterTarif() + "','" + 3 + "')" + ";";
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
	public boolean delete(Jeu jeu) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Jeu WHERE ID = " + jeu.getId() + ";";
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
	public boolean update(Jeu obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Jeu find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Jeu> findAll(){
		List<Jeu> listJeux = new ArrayList<>();
		Jeu jeu = new Jeu();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Jeu");
			while(result.next())
			{
				jeu = new Jeu(result.getInt("ID"), result.getString("Nom"), result.getBoolean("Dispo"),
						result.getInt("Tarif"), result.getDate("DateTarif"), result.getString("AdapterTarif"));
				listJeux.add(jeu);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listJeux;
	}

}
