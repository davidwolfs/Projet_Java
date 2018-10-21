package dao;

import java.sql.Connection;

import javax.swing.JOptionPane;

import exo.Administrateur;

public class AdministrateurDAO extends DAO<Administrateur>{

	public AdministrateurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Administrateur obj) {
		// TODO Auto-generated method stub
		System.out.println("ADMINISTRATEUR CREE.");
		JOptionPane.showMessageDialog(null,  "ADMINISTRATEUR CREE.");
		return false;
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

}
