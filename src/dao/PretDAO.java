package dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import exo.Emprunteur;
import exo.Pret;

public class PretDAO extends DAO<Pret>{

	public PretDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean create_Pret(Pret pret, Emprunteur emprunteur)
	{
		java.util.Date dateDebut = new java.util.Date();
		java.util.Date dateFin = new java.util.Date();
		dateDebut = pret.getDateDebut();
		dateFin = pret.getDateFin();
		
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Pret (DateDebut, DateFin, Confirmer_Pret, IDEmprunteur, IDPreteur, IDExemplaire) VALUES ('" +
					new Timestamp(dateDebut.getTime()) + "','" +  new Timestamp(dateFin.getTime()) + "','" + "FALSE" + "','" + emprunteur.getiD() + "','" + emprunteur.getiD() + "','" + 1 + "')" + ";";
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
	public boolean delete(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pret find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
