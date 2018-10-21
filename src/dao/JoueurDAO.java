package dao;

import java.sql.Connection;

import exo.Joueur;

public class JoueurDAO extends DAO<Joueur> {

	public JoueurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Joueur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Joueur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Joueur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Joueur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
