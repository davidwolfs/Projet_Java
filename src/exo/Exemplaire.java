package exo;

public class Exemplaire {
	private Jeu jeu;

	public Exemplaire() {
		
	}
	
	public Exemplaire(Jeu jeu) {
		this.jeu = jeu;
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
