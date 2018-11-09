package exo;

public class Exemplaire {
	private int id;
	private Jeu jeu;

	public Exemplaire() {
		
	}
	
	public Exemplaire(int id) {
		this.id=id;
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

	public Jeu getJeu() {
		return jeu;
	}

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	@Override
	public String toString() {
		return "Exemplaire [jeu=" + jeu + "]";
	}

}
