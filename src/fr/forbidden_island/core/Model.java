package fr.forbidden_island.core;

import fr.forbidden_island.Controls.Direction;
import fr.forbidden_island.data.Cellule;
import fr.forbidden_island.data.Joueur;

import java.util.Random;

public class Model extends Observable {
	private final int dimGrilleAbsc = 35;
	private final int dimGrilleOrd = 21;
	private Cellule[][] grille;
	public Cellule currentPlayer;
	Random r = new Random();


	public Model() {

		this.grille = new Cellule[dimGrilleAbsc][dimGrilleOrd];
		init();
		// System.out.println(grille[0][0].getAbsc());
		// System.out.println(grille[0][0].getOrd());
	}

	private void init() {

		for (int x = 0; x < dimGrilleAbsc; x++) {
			for (int y = 0; y < dimGrilleOrd; y++) {
				// donne des coords aux cellules
				grille[x][y] = new Cellule(this, x * Cellule.size, y * Cellule.size);
				// donne un état terre au cellules "centrales"
				if (x > dimGrilleAbsc / 4 && x < (dimGrilleAbsc * 3) / 4 && y > dimGrilleOrd / 4
						&& y < (dimGrilleOrd * 3) / 4) {
					grille[x][y].setMer(false);


				}
			}
		}
		currentPlayer=grille[(dimGrilleAbsc/2)][dimGrilleOrd/2];
		grille[(dimGrilleAbsc/2)][dimGrilleOrd/2].setJoueur(true);
		
	}
	
	public void inonde3() {
		for (int x = (dimGrilleAbsc / 4)-1; x <= ((dimGrilleAbsc * 3) / 4); x++) {
			for (int y = (dimGrilleOrd / 4)-1; y <= ((dimGrilleOrd * 3) / 4); y++) {
				float t = r.nextFloat();
				int cpt = 0;
				if(grille[x][y].getEstInonde() )
					grille[x][y].setMer(true);
				for (Cellule c : grille[x][y].voisines(grille)) {
					if (c.getMer())
						cpt++;
				}
				if(!grille[x][y].getMer() && cpt>0)
					if (t < 0.09)grille[x][y].setEstInonde(true);
			}
		}
	}
	public void move(Direction d) {
		
		Cellule [] v=currentPlayer.voisines(grille); //on récupère les voisines !!
		currentPlayer.setJoueur(false);
		switch (d) {
		case up:
			this.currentPlayer=v[0];
			break;
		case right:
			this.currentPlayer=v[1];
			break;
		case down:
			this.currentPlayer=v[2];
			break;
		case left:
			this.currentPlayer=v[3];
			break;
		default:
			break;
		}
		this.currentPlayer.setJoueur(true);
	}
	
	public void dry() {
		currentPlayer.setEstInonde(false);
	}

	
	public int getDimGrilleAbsc() {
		return this.dimGrilleAbsc;
	}

	public int getDimGrilleOrd() {
		return this.dimGrilleOrd;
	}

	public Cellule[][] getGrille() {
		return this.grille;
	}
	/*
	 * création d'une ile (tableau de cellules)
	 *
	 * récup toutes les cellules (avec leur états)
	 */

}
