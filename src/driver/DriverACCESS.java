package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DriverACCESS {
	private static Connection snglConnection = null;

	private DriverACCESS() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			String url = "jdbc:ucanaccess://./Projet_Jeux_Video.accdb";

			snglConnection = DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Impossible de trouver le driver pour la base de donn�e!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Impossible de se connecter �  la base de donn�e.");
		}

		if (snglConnection == null) {
			JOptionPane.showMessageDialog(null, "La base de donn�e est innaccessible, fermeture du programme.");
			System.exit(0);
		}
	}

	public static Connection getInstance() {
		if (snglConnection == null) {
			new DriverACCESS();
		}

		return snglConnection;
	}
}
