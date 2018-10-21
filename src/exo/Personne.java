package exo;

public abstract class Personne {
	protected int iD;
	protected String nom;
	protected String prenom;
	protected String dateNaiss;
	protected String email;
	protected String password;

	public Personne(int iD, String nom, String prenom, String dateNaiss, String email, String password) {
		this.iD = iD;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.email = email;
		this.password = password;
	}

	public Personne(String nom, String prenom, String dateNaiss, String email, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaiss = dateNaiss;
		this.email = email;
		this.password = password;
	}

	public Personne() {

	}

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(String dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Override
	public String toString() {
		return prenom + " " + nom;
	}
}
