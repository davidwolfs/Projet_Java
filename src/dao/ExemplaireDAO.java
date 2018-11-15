package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import exo.Console;
import exo.Exemplaire;
import exo.Jeu;
import exo.Preteur;

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

	public boolean create_Exemplaire(Exemplaire exemplaire, Preteur currentPreteur)
	{
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Exemplaire (IDJeu, IDPreteur) VALUES ('" + exemplaire.getJeu().getId() + "','" + currentPreteur.getiD() + "')" + ";";
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
	public boolean delete(Exemplaire obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Exemplaire exemplaire) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Exemplaire SET Reserve = True" + " WHERE ID = " + exemplaire.getId() + ";";
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
	public Exemplaire find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Exemplaire> findAll(Preteur currentPreteur){
		List<Exemplaire> listExemplaire = new ArrayList<>();
		Jeu jeu; 
		Console console;
		Exemplaire exemplaire;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS NOMBREEXEMPLAIRE, Jeu.ID AS IDJEU, Jeu.Nom AS NOMJEU, Dispo, Tarif, DateTarif, Console.ID AS IDCONSOLE, Console.Nom AS NOMCONSOLE FROM Console INNER JOIN ((Jeu INNER JOIN Exemplaire ON Jeu.ID = Exemplaire.IDJeu) INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console WHERE IDPreteur = " + currentPreteur.getiD() + " AND Reserve = False GROUP BY Jeu.ID, Jeu.Nom, Dispo, Tarif, DateTarif, Console.ID, Console.Nom");
			while(result.next())
			{
				console = new Console();
				jeu = new Jeu();
				exemplaire = new Exemplaire();
				console.setId(result.getInt("IDCONSOLE"));
				console.setNom(result.getString("NOMCONSOLE"));
				jeu.setId(result.getInt("IDJEU"));
				jeu.setNom(result.getString("NOMJEU"));
				jeu.setDispo(result.getBoolean("Dispo"));
				jeu.setTarif(result.getDouble("Tarif"));
				jeu.setDateTarif(result.getDate("DateTarif"));
				jeu.setConsole(console);
				exemplaire.setNbrExemplaire(result.getInt("NOMBREEXEMPLAIRE"));
				exemplaire.setJeu(jeu);
				listExemplaire.add(exemplaire);
			}
			currentPreteur.setListExamplaire(listExemplaire);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return listExemplaire;
	}
	
	public Exemplaire findExemplaireByIdJeu(Jeu jeu){
		Exemplaire exemplaire = new Exemplaire();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Exemplaire WHERE IDJeu = " + jeu.getId() + " AND Reserve = False");
			while(result.next())
			{
				exemplaire = new Exemplaire();
				exemplaire.setId(result.getInt("ID"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return exemplaire;
	}
	
	public boolean isLastExemplaire(Jeu jeu){
		boolean isLastExemplaire = false;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*)  FROM Exemplaire WHERE IDJeu = " + jeu.getId() + " AND Reserve = False GROUP BY IDJeu HAVING COUNT(*) = 1");
			if(result.first())
			{
				isLastExemplaire = true;
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return isLastExemplaire;
	}
	
	public int getNombreExemplaireJeu(Jeu jeu){
		int nombreExemplaireJeu = 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT COUNT(*) AS NombreExemplaireJeu FROM Exemplaire WHERE IDJeu = " + jeu.getId());
			if(result.first())
			{
				nombreExemplaireJeu = result.getInt("NombreExemplaireJeu");
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return nombreExemplaireJeu;
	}
}
