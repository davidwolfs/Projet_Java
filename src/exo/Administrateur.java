package exo;

public class Administrateur extends Personne {

	public Administrateur(String nom, String prenom, String dateNaiss, String email, String password) {
		super(nom, prenom, dateNaiss, email, password);
	}

	public Administrateur() {
		super();
	}

	public boolean findByEmailPassword(String email, String password) {
		if (email.equals("david.wolfs@condorcet.be") && password.equals("test")) {
			return true;
		}
		return false;
	}
	
	public boolean alreadyExist(String email)
	{
		return false;
	}
}
