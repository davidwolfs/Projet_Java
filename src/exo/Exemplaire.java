package exo;

public class Exemplaire {
	private int id;
	private int nbrExemplaire;
	private Jeu jeu;

	public Exemplaire() {

	}

	public Exemplaire(int id) {
		this.id = id;
	}

	public Exemplaire(int nbrExemplaire, Jeu jeu) {
		this.nbrExemplaire = nbrExemplaire;
		this.jeu = jeu;
	}

	public Exemplaire(Jeu jeu) {
		this.jeu = jeu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbrExemplaire() {
		return nbrExemplaire;
	}

	public void setNbrExemplaire(int nbrExemplaire) {
		this.nbrExemplaire = nbrExemplaire;
	}

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	@Override
	public String toString() {
		return "Exemplaire [id=" + id + ", nbrExemplaire=" + nbrExemplaire + ", jeu=" + jeu + "]";
	}

}
