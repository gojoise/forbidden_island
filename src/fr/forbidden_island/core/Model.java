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
		init();//Lance l'initialisation
	}
	/**
	 * Fonction utilitaire pour le constructeur:
	 * initialise la grille du modèle plus particulièrement le modèle entier
	 */
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
			    float t = r.nextFloat();
                for (float i = 2; i >= 0; i--) {
                    if (!(x > ((dimGrilleAbsc / 4) + i) && x < (((dimGrilleAbsc * 3) / 4) - i) && y > ((dimGrilleOrd / 4) + i) && y < (((dimGrilleOrd * 3) / 4) - i))) {
//                        int cpt = 0;
//                        Cellule[] tabvoisines = grille[x][y].voisines(grille);
//                        for(int v=0;v<5;v++) {
//                            if (tabvoisines[v].getMer())
//                                cpt++;
//                        }
//                        if (cpt ==0) {
//                            grille[x][y].setMer(true);
//                        }
//                         else {
                            if (t < (0.2 + (1 / (i + 1))))
                                grille[x][y].setMer(true);
                        //}
                    }
                }
			}
		}
		currentPlayer=grille[(dimGrilleAbsc/2)][dimGrilleOrd/2];
		grille[(dimGrilleAbsc/2)][dimGrilleOrd/2].setJoueur(true);
		
	}
	/**
	 * Innonde plusieurs cellules aléatoirement après clic sur le bouton fin de tour
	 */
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
	/**
	 * Change la case du currentplayer on récupère les vosines et on change la case selon la driection
	 * @param d la direction récupérée dans le controler
	 */
	public void move(Direction d) {
		
		Cellule [] v=currentPlayer.voisines(grille); //on récupère les voisines !!
		currentPlayer.setJoueur(false);
		switch (d) {
		case up:
			if(!v[0].getMer())this.currentPlayer=v[0];
			break;
		case right:
			if(!v[1].getMer())this.currentPlayer=v[1];
			break;
		case down:
			if(!v[2].getMer())this.currentPlayer=v[2];
			break;
		case left:
			if(!v[3].getMer())this.currentPlayer=v[3];
			break;
		default:
			break;
		}
		this.currentPlayer.setJoueur(true);
	}
	/**
	 * Asseche la cellule du currentPlayer
	 */
	public void dry() {
		currentPlayer.setEstInonde(false);
	}
	/**
	 * Change le joueur en cours, i.e change le current player avec le nouveau joueur
	 * si on est au joueur 1 on passe au joueur 2 etc , dans l'ordre croissant en revenant au premier une fois au dernier
	 */
	public void ChangePlayer() {
		//TODO
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
