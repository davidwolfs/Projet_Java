package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exo.Console;

public class ConsoleDAO extends DAO<Console> {

	public ConsoleDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Console console) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Console (Nom) VALUES ('" + console.getNom() + "')" + ";";
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
	public boolean delete(Console console) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Console WHERE ID = " + console.getId() + ";";
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
	public boolean update(Console console) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Console SET Nom = " + "'" + console.getNom() + "'" + " WHERE ID = " + console.getId()
					+ ";";
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
	public Console find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Console> findAll() {
		List<Console> listConsole = new ArrayList<>();
		Console console = new Console();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Console");
			while (result.next()) {
				console = new Console(result.getInt("ID"), result.getString("Nom"));
				listConsole.add(console);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listConsole;
	}
}
