package exo;

public class Console {
	private int id;
	private String nom;

	public Console() {

	}

	public Console(String nom) {
		this.nom = nom;
	}

	public Console(int id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Console [nom=" + nom + "]";
	}

}
