package dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import exo.Console;
import exo.Emprunteur;
import exo.Exemplaire;
import exo.Jeu;
import exo.Pret;
import exo.Preteur;
import exo.Reservation;

public class PretDAO extends DAO<Pret> {

	public PretDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean create_Pret(Pret pret, Emprunteur emprunteur, Exemplaire exemplaire) {
		java.util.Date dateDebut = new java.util.Date();
		java.util.Date dateFin = new java.util.Date();
		dateDebut = pret.getDateDebut();
		dateFin = pret.getDateFin();

		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "INSERT INTO Pret (DateDebut, DateFin, Confirmer_Pret, IDEmprunteur, IDPreteur, IDExemplaire) VALUES ('"
					+ new Timestamp(dateDebut.getTime()) + "','" + new Timestamp(dateFin.getTime()) + "','" + "FALSE"
					+ "','" + emprunteur.getiD() + "','" + emprunteur.getiD() + "','" + exemplaire.getId() + "')" + ";";
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

	public boolean delete_Pret_Emprunteur(Pret pret) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "DELETE FROM Pret WHERE ID = " + pret.getId() + ";";
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
	public boolean update(Pret obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean update_Confirmation(Pret pret) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Pret SET Confirmer_Pret = " + pret.isConfirmer_pret() + " WHERE ID = " + pret.getId()
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

	public boolean update_Pret_Emprunteur(Emprunteur emprunteur, Pret pret) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Pret SET IDEmprunteur = " + emprunteur.getiD() + " WHERE ID = " + pret.getId() + ";";
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

	public boolean update_Pret_Preteur(Preteur preteur, Pret pret) {
		boolean statementResult;
		try {
			Statement statement = connect.createStatement();
			String query = "UPDATE Pret SET IDPreteur = " + preteur.getiD() + " WHERE ID = " + pret.getId() + ";";
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
	public Pret find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pret> findAll(Emprunteur emprunteur) {
		List<Pret> listPret = new ArrayList<>();
		Reservation reservation;
		Exemplaire exemplaire;
		Jeu jeu;
		Console console;
		Pret pret;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT Pret.ID AS IDPret, Pret.DateDebut, Pret.DateFin, Pret.Confirmer_Pret, Emprunteur.ID AS IDEMPRUNTEUR, Emprunteur.Nom AS NOMEMPRUNTEUR, Emprunteur.Prenom AS PRENOMEMPRUNTEUR, Emprunteur.DateNaiss AS DATENAISSEMPRUNTEUR, Emprunteur.Email AS EMAILEMPRUNTEUR, Emprunteur.Unite AS UNITEEMPRUNTEUR, Exemplaire.ID AS IDEXEMPLAIRE, Exemplaire.IDJeu AS ID_JEU, Pret.IDExemplaire AS IDEXEMPLAIRE, Jeu.ID AS IDJEU, Jeu.Nom AS NOMJEU, Jeu.Dispo AS DISPO, Jeu.Tarif AS TARIF, Jeu.DateTarif AS DATETARIF, Console.ID AS ID_Console, Console.Nom AS NOMCONSOLE, Pret.DateReservation AS DATERESERVATION FROM Console INNER JOIN ((Jeu INNER JOIN (Exemplaire INNER JOIN (Emprunteur INNER JOIN Pret ON Emprunteur.ID = Pret.IDEmprunteur) ON Exemplaire.ID = Pret.IDExemplaire) ON Jeu.ID = Exemplaire.IDJeu) INNER JOIN Ligne_Jeu ON Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console WHERE Emprunteur.ID <> "
									+ emprunteur.getiD());
			while (result.next()) {
				reservation = new Reservation();
				emprunteur = new Emprunteur();
				jeu = new Jeu();
				console = new Console();
				pret = new Pret();
				exemplaire = new Exemplaire();
				console.setId(result.getInt("ID_Console"));
				console.setNom(result.getString("NOMCONSOLE"));
				jeu.setId(result.getInt("ID_JEU"));
				JeuDAO jeuDAO = new JeuDAO(connect);
				jeu = jeuDAO.find(jeu.getId());
				jeu.setNom(jeu.getNom());
				jeu.setNom(result.getString("NOMJEU"));
				jeu.setDispo(result.getBoolean("DISPO"));
				jeu.setTarif(result.getDouble("TARIF"));
				jeu.setDateTarif(result.getDate("DATETARIF"));
				jeu.setConsole(console);
				exemplaire.setJeu(jeu);
				// reservation.setId(result.getInt("ID_Reservation"));
				reservation.setDateReservation(result.getDate("DATERESERVATION"));
				reservation.setJeu(jeu);
				emprunteur.setiD(result.getInt("IDEMPRUNTEUR"));
				emprunteur.setNom(result.getString("NOMEMPRUNTEUR"));
				emprunteur.setPrenom(result.getString("PRENOMEMPRUNTEUR"));
				emprunteur.setDateNaiss(result.getDate("DATENAISSEMPRUNTEUR"));
				emprunteur.setEmail(result.getString("EMAILEMPRUNTEUR"));
				emprunteur.setUnite(result.getInt("UNITEEMPRUNTEUR"));
				emprunteur.setReservation(reservation);
				pret.setId(result.getInt("IDPret"));
				pret.setDateDebut(result.getDate("DateDebut"));
				pret.setDateFin(result.getDate("DateFin"));
				pret.setConfirmer_pret(result.getBoolean("Confirmer_Pret"));
				pret.setExemplaire(exemplaire);
				pret.setEmprunteur(emprunteur);
				exemplaire.setId(result.getInt("IDEXEMPLAIRE"));
				exemplaire.setJeu(jeu);
				pret.setExemplaire(exemplaire);
				listPret.add(pret);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPret;
	}

	public boolean sameReservationFound(Exemplaire exemplaire) {
		boolean found = false;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT * FROM Pret A INNER JOIN Pret B ON A.IDExemplaire = B.IDExemplaire WHERE A.ID <> B.ID AND A.IDEmprunteur <> B.IDEmprunteur AND A.IDExemplaire = "
									+ exemplaire.getId() + " AND B.Confirmer_Pret = False");
			if (result.first()) {
				found = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return found;
	}

	public List<Pret> findAll() {
		List<Pret> listPret = new ArrayList<>();
		Pret pret;
		Emprunteur emprunteur;
		Exemplaire exemplaire;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT A.ID, A.DateDebut, A.DateFin, A.IDEmprunteur AS IDEMPRUNTEUR, B.Confirmer_Pret, Emprunteur.Unite, A.IDExemplaire AS IDEXEMPLAIRE FROM (Pret AS A INNER JOIN Pret AS B ON A.IDExemplaire = B.IDExemplaire) INNER JOIN Emprunteur ON A.IDEmprunteur = Emprunteur.ID WHERE A.ID<>B.ID AND A.IDEmprunteur<>B.IDEmprunteur AND B.Confirmer_Pret=False ORDER BY Emprunteur.Unite DESC");
			while (result.next()) {
				pret = new Pret();
				emprunteur = new Emprunteur();
				exemplaire = new Exemplaire();
				pret.setId(result.getInt("ID"));
				pret.setDateDebut(result.getDate("DateDebut"));
				pret.setDateFin(result.getDate("DateFin"));
				pret.setConfirmer_pret(result.getBoolean("Confirmer_Pret"));
				emprunteur.setiD(result.getInt("IDEMPRUNTEUR"));
				exemplaire.setId(result.getInt("IDEXEMPLAIRE"));
				pret.setEmprunteur(emprunteur);
				pret.setExemplaire(exemplaire);
				listPret.add(pret);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPret;
	}

	// SELECT ID_Reservation, DateReservation, Jeu.Nom AS NOMJEU, Console.Nom AS
	// NOMCONSOLE, DateDebut, DateFin, Confirmer_Pret FROM Console INNER JOIN
	// ((((Emprunteur INNER JOIN Pret ON Emprunteur.ID = Pret.IDEmprunteur) INNER
	// JOIN Reservation ON Emprunteur.ID = Reservation.IDEmprunteur) INNER JOIN (Jeu
	// INNER JOIN Ligne_Reservation ON Jeu.ID = Ligne_Reservation.ID_Jeu) ON
	// Reservation.ID = Ligne_Reservation.ID_Reservation) INNER JOIN Ligne_Jeu ON
	// Jeu.ID = Ligne_Jeu.ID_Jeu) ON Console.ID = Ligne_Jeu.ID_Console WHERE
	// Emprunteur.ID = " + currentEmprunteur.getiD()
	// SELECT Exemplaire.ID AS IDEXEMPLAIRE, Reservation.ID AS IDRESERVATION,
	// Reservation.DateReservation AS DATERESERVATION, Jeu.ID AS IDJEU, Jeu.Nom AS
	// NOMJEU, Console.ID AS IDCONSOLE, Console.Nom AS NOMCONSOLE, Pret.ID AS
	// IDPRET, Pret.DateDebut AS DATEDEBUT, Pret.DateFin AS DATEFIN,
	// Pret.Confirmer_Pret AS CONFIRMERPRET, Preteur.ID AS IDPRETEUR, Preteur.Nom AS
	// NOMPRETEUR, Preteur.Prenom AS PRENOMPRETEUR FROM Preteur INNER JOIN
	// (Reservation INNER JOIN (((Jeu INNER JOIN (Exemplaire INNER JOIN (Emprunteur
	// INNER JOIN Pret ON Emprunteur.ID = Pret.IDEmprunteur) ON Exemplaire.ID =
	// Pret.IDExemplaire) ON Jeu.ID = Exemplaire.IDJeu) INNER JOIN (Console INNER
	// JOIN Ligne_Jeu ON Console.ID = Ligne_Jeu.ID_Console) ON Jeu.ID =
	// Ligne_Jeu.ID_Jeu) INNER JOIN Ligne_Reservation ON Jeu.ID =
	// Ligne_Reservation.ID_Jeu) ON Reservation.ID =
	// Ligne_Reservation.ID_Reservation) ON Preteur.ID = Pret.IDPreteur WHERE
	// Emprunteur.ID = " + currentEmprunteur.getiD());
	public List<Pret> findAllPretByEmprunteur(Emprunteur currentEmprunteur) {
		List<Pret> listPret = new ArrayList<>();
		Exemplaire exemplaire;
		Jeu jeu;
		Console console;
		Pret pret;
		Preteur preteur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT Exemplaire.ID AS IDEXEMPLAIRE, COUNT(Reservation.ID) AS IDRESERVATION, Jeu.ID AS IDJEU, Jeu.Nom AS NOMJEU, Console.ID AS IDCONSOLE, Console.Nom AS NOMCONSOLE, Pret.ID AS IDPRET, Pret.DateDebut AS DATEDEBUT, Pret.DateFin AS DATEFIN, Pret.Confirmer_Pret AS CONFIRMERPRET, Preteur.ID AS IDPRETEUR, Preteur.Nom AS NOMPRETEUR, Preteur.Prenom AS PRENOMPRETEUR FROM Preteur INNER JOIN (Reservation INNER JOIN (((Jeu INNER JOIN (Exemplaire INNER JOIN (Emprunteur INNER JOIN Pret ON Emprunteur.ID = Pret.IDEmprunteur) ON Exemplaire.ID = Pret.IDExemplaire) ON Jeu.ID = Exemplaire.IDJeu) INNER JOIN (Console INNER JOIN Ligne_Jeu ON Console.ID = Ligne_Jeu.ID_Console) ON Jeu.ID = Ligne_Jeu.ID_Jeu) INNER JOIN Ligne_Reservation ON Jeu.ID = Ligne_Reservation.ID_Jeu) ON Reservation.ID = Ligne_Reservation.ID_Reservation) ON Preteur.ID = Pret.IDPreteur WHERE Emprunteur.ID = "
									+ currentEmprunteur.getiD()
									+ " GROUP BY Exemplaire.ID,  Jeu.ID, Jeu.Nom, Console.ID, Console.Nom, Pret.ID, Pret.DateDebut, Pret.DateFin, Pret.Confirmer_Pret, Preteur.ID, Preteur.Nom, Preteur.Prenom");
			while (result.next()) {
				exemplaire = new Exemplaire();
				jeu = new Jeu();
				console = new Console();
				pret = new Pret();
				preteur = new Preteur();
				console.setId(result.getInt("IDCONSOLE"));
				console.setNom(result.getString("NOMCONSOLE"));
				jeu.setId(result.getInt("IDJEU"));
				jeu.setNom(result.getString("NOMJEU"));
				/*
				 * jeu.setDispo(result.getBoolean("Dispo"));
				 * jeu.setTarif(result.getDouble("Tarif"));
				 * jeu.setDateTarif(result.getDate("DateTarif"));
				 */
				jeu.setConsole(console);
				exemplaire.setId(result.getInt("IDEXEMPLAIRE"));
				exemplaire.setJeu(jeu);
				pret.setId(result.getInt("IDPRET"));
				pret.setDateDebut(result.getDate("DATEDEBUT"));
				pret.setDateFin(result.getDate("DATEFIN"));
				pret.setConfirmer_pret(result.getBoolean("CONFIRMERPRET"));
				/*
				 * reservation = new Reservation(result.getInt("IDRESERVATION"),
				 * result.getDate("DATERESERVATION")); reservation.setJeu(jeu);
				 */
				preteur.setiD(result.getInt("IDPRETEUR"));
				preteur.setNom(result.getString("NOMPRETEUR"));
				preteur.setPrenom(result.getString("PRENOMPRETEUR"));
				// currentEmprunteur.setReservation(reservation);
				pret.setExemplaire(exemplaire);
				pret.setEmprunteur(currentEmprunteur);
				pret.setPreteur(preteur);
				listPret.add(pret);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPret;
	}

	public boolean isAlreadyConfirmed(Pret pret) {
		boolean isAlreadyConfirmed = false;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM PRET WHERE ID = " + pret.getId() + " AND Confirmer_Pret = TRUE");
			if (result.first()) {
				isAlreadyConfirmed = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAlreadyConfirmed;
	}

	public List<Pret> getPretEmprunteurSortByPriorites(Exemplaire exemplaire) {
		List<Pret> listPret = new ArrayList<>();
		Pret pret;
		Emprunteur emprunteur;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT A.ID AS IDPRET, A.DateDebut AS DATEDEBUT, A.DateFin AS DATEFIN, A.Confirmer_Pret AS CONFIRMER_PRET, Emprunteur.ID AS IDEMPRUNTEUR, Emprunteur.Nom AS NOMEMPRUNTEUR, Emprunteur.Prenom AS PRENOMEMPRUNTEUR, Emprunteur.DateNaiss AS DATENAISSEMPRUNTEUR, Emprunteur.Email AS EMAILEMPRUNTEUR, Emprunteur.Password AS PASSWORDEMPRUNTEUR, Emprunteur.Unite AS UNITEEMPRUNTEUR, A.DateReservation AS DATERESERVATION FROM (Pret AS A INNER JOIN Pret AS B ON A.IDExemplaire = B.IDExemplaire) INNER JOIN Emprunteur ON A.IDEmprunteur = Emprunteur.ID WHERE A.ID<>B.ID AND A.IDEmprunteur<>B.IDEmprunteur AND A.IDExemplaire = "
									+ exemplaire.getId()
									+ " AND B.Confirmer_Pret=False ORDER BY Emprunteur.Unite DESC, A.DateReservation, Date_en, Emprunteur.DateNaiss, rnd(B.ID)");
			while (result.next()) {
				pret = new Pret();
				pret.setId(result.getInt("IDPRET"));
				pret.setDateDebut(result.getDate("DATEDEBUT"));
				pret.setDateFin(result.getDate("DATEFIN"));
				pret.setConfirmer_pret(result.getBoolean("CONFIRMER_PRET"));
				emprunteur = new Emprunteur(result.getInt("IDEMPRUNTEUR"), result.getString("NOMEMPRUNTEUR"),
						result.getString("PRENOMEMPRUNTEUR"), result.getDate("DATENAISSEMPRUNTEUR"),
						result.getString("EMAILEMPRUNTEUR"), result.getString("PASSWORDEMPRUNTEUR"),
						result.getInt("UNITEEMPRUNTEUR"));
				pret.setEmprunteur(emprunteur);
				pret.setExemplaire(exemplaire);
				listPret.add(pret);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listPret;
	}

}
