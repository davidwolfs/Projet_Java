package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exo.Exemplaire;
import exo.Jeu;

public class ExemplaireDAO extends DAO<Exemplaire>{

	public ExemplaireDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Exemplaire find(int id) {
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
