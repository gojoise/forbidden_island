package fr.forbidden_island.data;

import fr.forbidden_island.Controls.Direction;

public class Joueur {
	private Cellule cellule;
	
	public Joueur(Cellule c) {
		this.cellule=c;
		c.setJoueur(true);
	}
	public void move(Direction d,Cellule [][] grille) {
		Cellule [] v=cellule.voisines(grille); //on récupère les voisines !!
		cellule.setJoueur(false);
		switch (d) {
		case up:
			this.cellule=v[0];
			break;
		case right:
			this.cellule=v[1];
			break;
		case down:
			this.cellule=v[2];
			break;
		case left:
			this.cellule=v[3];
			break;
		default:
			break;
		}
		this.cellule.setJoueur(true);
	}
	
}
