package dao;

import java.sql.Connection;

import javax.swing.JOptionPane;

import exo.Preteur;

public class PreteurDAO extends DAO<Preteur>{

	public PreteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Preteur obj) {
		System.out.println("PRETEUR CREE.");
		JOptionPane.showMessageDialog(null,  "PRETEUR CREE.");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Preteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Preteur obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Preteur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
