package fr.forbidden_island.data;


public class Joueur {
	private Cellule cellule;
	
	public Joueur(Cellule c) {
		this.cellule=c;
		c.setJoueur(true);
	}

	
}
