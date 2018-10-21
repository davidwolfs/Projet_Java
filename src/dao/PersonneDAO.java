package dao;

import java.sql.Connection;

import exo.Personne;

public class PersonneDAO extends DAO<Personne>{

	public PersonneDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Personne obj) {
		return false;
	}

	@Override
	public boolean delete(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Personne obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Personne find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
