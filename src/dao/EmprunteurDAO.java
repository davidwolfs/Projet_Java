package dao;

import java.sql.Connection;

import javax.swing.JOptionPane;

import exo.Emprunteur;

public class EmprunteurDAO extends DAO<Emprunteur> {

	public EmprunteurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Emprunteur obj) {
		System.out.println("Nom : " + obj.getNom());
		System.out.println("Prenom : " + obj.getPrenom());
		System.out.println("Date de naissance : " + obj.getDateNaiss());
		System.out.println("Email : " + obj.getEmail());
		System.out.println("Password : " + obj.getPassword());
		System.out.println("EMPRUNTEUR CREE.");
		JOptionPane.showMessageDialog(null,  "EMPRUNTEUR CREE.");
		// TODO Auto-generated method stub
		return false;
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

}
